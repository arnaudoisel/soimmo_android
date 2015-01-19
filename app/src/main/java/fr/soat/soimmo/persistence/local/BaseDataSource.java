package fr.soat.soimmo.persistence.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class BaseDataSource<T> implements DataSource<T> {

    protected SQLiteDatabase database;

    public BaseDataSource(SQLiteDatabase database) {
        this.database = database;
    }

    protected abstract T cursorToObject(Cursor cursor);
    protected abstract ContentValues objectToContentValues(T entity);

}
