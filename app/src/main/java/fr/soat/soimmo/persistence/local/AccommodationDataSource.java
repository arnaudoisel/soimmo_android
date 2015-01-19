package fr.soat.soimmo.persistence.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import fr.soat.soimmo.models.Accommodation;

import static fr.soat.soimmo.utils.Constant.DB_DATE_FORMAT;

public class AccommodationDataSource extends BaseDataSource<Accommodation> {

    public static final String TABLE_ACCOMMODATION = "ACCOMMODATION";
    public static final String COLUMN_ID = "ACCOMMODATION_ID";
    public static final String COLUMN_SURFACE = "SURFACE";
    public static final String COLUMN_ROOMS = "ROOMS";
    public static final String COLUMN_FLOOR = "FLOOR";
    public static final String COLUMN_ELEVATOR = "ELEVATOR";
    public static final String COLUMN_RENT = "RENT";
    public static final String COLUMN_CHARGE = "CHARGE";
    public static final String COLUMN_AVAILABILITY = "AVAILABILITY";

    private String[] allColumns = {
            COLUMN_ID,
            COLUMN_SURFACE,
            COLUMN_ROOMS,
            COLUMN_FLOOR,
            COLUMN_ELEVATOR,
            COLUMN_RENT,
            COLUMN_CHARGE,
            COLUMN_AVAILABILITY
    };

    private final static Logger log = Logger.getLogger(AccommodationDataSource.class.getName());


    public AccommodationDataSource(SQLiteDatabase database) {
        super(database);
/*
        try {
            dbHelper = new LocalSQLiteOpenHelper(context,
                    SQLITE_DB_NAME, null,
                    VersionUtils.getVersionCode(context));
        } catch (PackageManager.NameNotFoundException e) {
            dbHelper = null;
            e.printStackTrace();
        }
        */
    }

    /*
        public void open() throws SQLException {
            database = dbHelper.getWritableDatabase();
        }

        public void close() {
            dbHelper.close();
        }
    */
    @Override
    public boolean insert(Accommodation accommodation) {

        if (accommodation == null)
            return false;

        long result = database.insert(
                TABLE_ACCOMMODATION,
                null,
                objectToContentValues(accommodation));

        return result != -1;
    }

    @Override
    public boolean delete(Accommodation accommodation) {

        if (accommodation == null)
            return false;

        int result = database.delete(
                TABLE_ACCOMMODATION,
                COLUMN_ID + " = " + accommodation.getId(),
                null);

        if (result != 0)
            log.info("Accommodation deleted with id: " + accommodation.getId());

        return result != 0;
    }

    @Override
    public boolean update(Accommodation accommodation) {

        if (accommodation == null)
            return false;

        int result = database.update(
                TABLE_ACCOMMODATION,
                objectToContentValues(accommodation),
                COLUMN_ID + " = " + accommodation.getId(),
                null);

        return result != 0;
    }

    @Override
    public Accommodation read(int id) {

        Cursor cursor = database.query(
                TABLE_ACCOMMODATION,
                allColumns,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null);

        Accommodation accommodation = null;

        if (cursor != null && cursor.moveToFirst()) {
            accommodation = cursorToObject(cursor);
            cursor.close();
        }

        return accommodation;
    }

    @Override
    public List<Accommodation> read() {

        List<Accommodation> accommodations = new ArrayList<Accommodation>();

        Cursor cursor = database.query(
                TABLE_ACCOMMODATION,
                allColumns,
                null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {

            while (!cursor.isAfterLast()) {
                accommodations.add(cursorToObject(cursor));
                cursor.moveToNext();
            }

            cursor.close();
        }

        return accommodations;
    }

    @Override
    public List<Accommodation> read(String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {

        List<Accommodation> accommodations = new ArrayList<Accommodation>();

        Cursor cursor = database.query(
                TABLE_ACCOMMODATION,
                allColumns,
                selection, selectionArgs, groupBy, having, orderBy);

        if (cursor != null && cursor.moveToFirst()) {

            while (!cursor.isAfterLast()) {
                accommodations.add(cursorToObject(cursor));
                cursor.moveToNext();
            }

            cursor.close();
        }

        return accommodations;
    }

    @Override
    protected Accommodation cursorToObject(Cursor cursor) {

        if (cursor == null)
            return null;

        Accommodation accommodation = new Accommodation();
        long id = cursor.getLong(0);
        accommodation.setId(id);
        accommodation.setSurface(cursor.getDouble(1));
        accommodation.setRooms(cursor.getInt(2));
        accommodation.setFloor(cursor.getInt(3));
        accommodation.setElevator(cursor.getInt(4) != 0);
        accommodation.setRent(cursor.getDouble(5));
        accommodation.setCharge(cursor.getDouble(6));

        Date availability = null;
        try {
            availability = DB_DATE_FORMAT.parse(cursor.getString(7));
        } catch (ParseException e) {
            log.severe("Failed to parse date for accommodation : " + id);
            e.printStackTrace();
        }
        accommodation.setAvailableFrom(availability);

        return accommodation;
    }

    @Override
    protected ContentValues objectToContentValues(Accommodation accommodation) {

        if (accommodation == null)
            return null;

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, accommodation.getId());
        values.put(COLUMN_SURFACE, accommodation.getSurface());
        values.put(COLUMN_ROOMS, accommodation.getRooms());
        values.put(COLUMN_FLOOR, accommodation.getFloor());
        values.put(COLUMN_ELEVATOR, accommodation.getElevator());
        values.put(COLUMN_RENT, accommodation.getRent());
        values.put(COLUMN_CHARGE, accommodation.getCharge());
        values.put(COLUMN_AVAILABILITY, accommodation.getAvailableFrom() == null ? null : DB_DATE_FORMAT.format(accommodation.getAvailableFrom()));

        return values;
    }

}
