package fr.soat.soimmo.loaders;

import android.content.Context;

import java.util.List;

import fr.soat.soimmo.persistence.local.DataSource;

public class SQLiteTestDataLoader<T> extends AbstractDataLoader<List<T>> {


    private DataSource<T> mDataSource;
    private String mSelection;
    private String[] mSelectionArgs;
    private String mGroupBy;
    private String mHaving;
    private String mOrderBy;

    public SQLiteTestDataLoader(Context context, DataSource<T> dataSource, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        super(context);
        mDataSource = dataSource;
        mSelection = selection;
        mSelectionArgs = selectionArgs;
        mGroupBy = groupBy;
        mHaving = having;
        mOrderBy = orderBy;
        // TEST
    }

    @Override
    protected List<T> buildList() {
        List<T> testList = mDataSource.read(mSelection, mSelectionArgs, mGroupBy, mHaving, mOrderBy);
        return testList;
    }

    public void insert(T entity) {
        new InsertTask(this).execute(entity);
    }

    public void update(T entity) {
        new UpdateTask(this).execute(entity);
    }

    public void delete(T entity) {
        new DeleteTask(this).execute(entity);
    }

    private class InsertTask extends ContentChangingTask<T, Void, Void> {

        InsertTask(SQLiteTestDataLoader loader) {
            super(loader);
        }

        @Override
        protected Void doInBackground(T... params) {
            mDataSource.insert(params[0]);
            return (null);
        }
    }

    private class UpdateTask extends ContentChangingTask<T, Void, Void> {

        UpdateTask(SQLiteTestDataLoader loader) {
            super(loader);
        }

        @Override
        protected Void doInBackground(T... params) {
            mDataSource.update(params[0]);
            return (null);
        }
    }

    private class DeleteTask extends ContentChangingTask<T, Void, Void> {

        DeleteTask(SQLiteTestDataLoader loader) {
            super(loader);
        }

        @Override
        protected Void doInBackground(T... params) {
            mDataSource.delete(params[0]);
            return (null);
        }
    }
}
