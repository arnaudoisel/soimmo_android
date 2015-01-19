package fr.soat.soimmo.persistence.local;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;
import java.util.logging.Logger;

import fr.soat.soimmo.persistence.SqlParser;
import fr.soat.soimmo.utils.AssetUtils;

/**
 * SQL File naming:

 App version	App version code	DB version	Sql script file
 1.00 (first version)	100	100	create.sql
 1.01	101	101	upgrade-0101.sql
 1.10	110	110	upgrade-0110.sql
 2.00	200	200	upgrade-0200.sql

 * How to instantiate this class:

 this.helper = new SQLiteDatabaseHelper(context, "database", null,
 VersionUtils.getVersionCode(context));

 **/
public class DatabaseHelper extends SQLiteOpenHelper {

    private final static Logger log = Logger.getLogger(DatabaseHelper.class.getName());

    private static final String SQL_DIR = "sql" ;

    private static final String CREATE_FILE = "create.sql";

    private static final String UPGRADE_FILE_PREFIX = "upgrade-";

    private static final String UPGRADE_FILE_SUFFIX = ".sql";

    private Context context ;

    public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context ;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            log.info("create database");
            execSqlFile(CREATE_FILE, db );
        } catch( IOException exception ) {
            throw new RuntimeException("Database creation failed", exception );
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            log.info("upgrade database from " + oldVersion + " to " + newVersion);
            for( String sqlFile : AssetUtils.list(SQL_DIR, this.context.getAssets())) {
                if ( sqlFile.startsWith(UPGRADE_FILE_PREFIX)) {
                    int fileVersion = Integer.parseInt(sqlFile.substring(UPGRADE_FILE_PREFIX.length(), sqlFile.length() - UPGRADE_FILE_SUFFIX.length()));
                    if ( fileVersion > oldVersion && fileVersion <= newVersion ) {
                        execSqlFile( sqlFile, db );
                    }
                }
            }
        } catch( IOException exception ) {
            throw new RuntimeException("Database upgrade failed", exception );
        }
    }

    protected void execSqlFile(String sqlFile, SQLiteDatabase db ) throws SQLException, IOException {
        log.info("  exec sql file: " + sqlFile );
        for( String sqlInstruction : SqlParser.parseSqlFile(SQL_DIR + "/" + sqlFile, this.context.getAssets())) {
            log.info("    sql: " + sqlInstruction);
            db.execSQL(sqlInstruction);
        }
    }

}
