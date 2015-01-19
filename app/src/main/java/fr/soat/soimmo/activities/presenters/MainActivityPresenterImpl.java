package fr.soat.soimmo.activities.presenters;


import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;

import fr.soat.soimmo.models.Accommodation;
import fr.soat.soimmo.models.AccommodationType;
import fr.soat.soimmo.models.Address;
import fr.soat.soimmo.models.City;
import fr.soat.soimmo.persistence.local.AccommodationDataSource;
import fr.soat.soimmo.persistence.local.DatabaseHelper;
import fr.soat.soimmo.utils.Constant;
import fr.soat.soimmo.utils.VersionUtils;

public class MainActivityPresenterImpl extends BaseActivityPresenter implements MainActivityPresenter {

    @Inject
    public MainActivityPresenterImpl() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // temporary
        populateDatabase(view.getActivity());
    }

    /**
     * Temporary method to populate database at start of main activity
     */
    private void populateDatabase(Context context) {

        try {
            DatabaseHelper helper = new DatabaseHelper(context, Constant.SQLITE_DB_NAME, null,
                    VersionUtils.getVersionCode(context));

            SQLiteDatabase database = helper.getWritableDatabase();
            AccommodationDataSource dataSource = new AccommodationDataSource(database);

            List<Accommodation> list = dataSource.read();
            if(list == null || list.size() == 0){
                dataSource.insert(createAccommodation(1, new AccommodationType(1L, "APARTMENT"), 1, "Boulogne-Billancourt", 93d, 2300d, 4));
                dataSource.insert(createAccommodation(2, new AccommodationType(1L, "APARTMENT"), 2, "Paris", 34d, 1100d, 2));
                dataSource.insert(createAccommodation(3, new AccommodationType(2L, "HOUSE"), 3, "Plaisir", 101d, 1800d, 5));
                dataSource.insert(createAccommodation(4, new AccommodationType(1L, "APARTMENT"), 4, "Paris", 18d, 700d, 1));
            }
            helper.close();
            database.close();

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Accommodation createAccommodation(long id, AccommodationType type, long addressId, String city, Double surface, Double rent, int rooms) {
        Accommodation accommodation = new Accommodation();
        accommodation.setId(id);
        Address address = new Address();
        address.setId(addressId);
        address.setCity(new City(addressId, city));
        accommodation.setType(type);
        accommodation.setAddress(address);
        accommodation.setSurface(surface);
        accommodation.setRent(rent);
        accommodation.setRooms(rooms);
        return accommodation;
    }
}
