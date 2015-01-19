package fr.soat.soimmo.fragments;

import android.app.LoaderManager;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import fr.soat.soimmo.R;
import fr.soat.soimmo.adapters.AccommodationAdapter;
import fr.soat.soimmo.fragments.modules.SearchAccommodationFragmentModule;
import fr.soat.soimmo.fragments.presenters.SearchAccommodationFragmentPresenter;
import fr.soat.soimmo.loaders.SQLiteTestDataLoader;
import fr.soat.soimmo.models.Accommodation;
import fr.soat.soimmo.persistence.local.AccommodationDataSource;
import fr.soat.soimmo.persistence.local.DatabaseHelper;
import fr.soat.soimmo.utils.Constant;
import fr.soat.soimmo.utils.VersionUtils;

public class SearchAccommodationFragmentImpl extends BaseFragment
        implements SearchAccommodationFragment,
        LoaderManager.LoaderCallbacks<List<Accommodation>> {

    /**
     * LOADER DATABASE STUFF *
     */
    // The Loader's id (this id is specific to the ListFragment's LoaderManager)
    private static final int LOADER_ID = 1;
    private static final boolean DEBUG = true;
    private static final String TAG = "CustomLoaderExampleListFragment";
    /**
     * LOADER DATABASE STUFF *
     */

    @InjectView(R.id.test_button)
    Button testNetworkButton;
    @InjectView(R.id.accommodation_list)
    RecyclerView accommodationListView;
    @Inject
    SearchAccommodationFragmentPresenter presenter;

    /**
     * LOADER DATABASE STUFF *
     */
    private ArrayAdapter mAdapter;
    private SQLiteDatabase mDatabase;
    private AccommodationDataSource mDataSource;
    private DatabaseHelper mDbHelper;

    /**
     * LOADER DATABASE STUFF *
     */

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new SearchAccommodationFragmentModule(this));
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_search_accommodation;
    }

    @Override
    protected int getOptionsMenuId() {
        return 0;
    }

    @Override
    public void setTestNetworkClickListener(View.OnClickListener listener) {
        testNetworkButton.setOnClickListener(listener);
    }

    @Override
    public void setView(Bundle savedInstanceState) {
        super.setView(savedInstanceState);


        accommodationListView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(activityContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        accommodationListView.setLayoutManager(layoutManager);

        AccommodationAdapter adapter = new AccommodationAdapter();
        accommodationListView.setAdapter(adapter);

        /*** LOADER STUFF ***/
        try {

            mDbHelper = new DatabaseHelper(activityContext, Constant.SQLITE_DB_NAME, null,
                    VersionUtils.getVersionCode(activityContext));
            mDatabase = mDbHelper.getWritableDatabase();
            mDataSource = new AccommodationDataSource(mDatabase);
            mAdapter = new ArrayAdapter(getActivity(),
                    android.R.layout.simple_list_item_1);

            if (DEBUG) {
                Log.i(TAG, "+++ Calling initLoader()! +++");
                if (getLoaderManager().getLoader(LOADER_ID) == null) {
                    Log.i(TAG, "+++ Initializing the new Loader... +++");
                } else {
                    Log.i(TAG, "+++ Reconnecting with existing Loader (id '1')... +++");
                }
            }
            // Initialize a Loader with id '1'. If the Loader with this id already
            // exists, then the LoaderManager will reuse the existing Loader.
            getLoaderManager().initLoader(LOADER_ID, null, this);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        /*** LOADER STUFF ***/

    }

    @Override
    public Loader<List<Accommodation>> onCreateLoader(int id, Bundle args) {
        SQLiteTestDataLoader<Accommodation> loader = new SQLiteTestDataLoader<Accommodation>(getActivity(), mDataSource, null, null, null, null, null);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<List<Accommodation>> loader, List<Accommodation> data) {
        if (DEBUG) Log.i(TAG, "+++ onLoadFinished() called! +++");
        mAdapter.clear();
        mAdapter.addAll(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Accommodation>> loader) {
        mAdapter.clear();
    }

    @Override
    public void closeResourcesOnDestroy() {
        mDbHelper.close();
        mDatabase.close();
        mDataSource = null;
        mDbHelper = null;
        mDatabase = null;
    }
}