package fr.soat.soimmo.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import fr.soat.soimmo.R;
import fr.soat.soimmo.adapters.AccommodationAdapter;
import fr.soat.soimmo.fragments.modules.SearchAccommodationFragmentModule;
import fr.soat.soimmo.fragments.presenters.SearchAccommodationFragmentPresenter;
import fr.soat.soimmo.models.Accommodation;
import fr.soat.soimmo.models.AccommodationType;
import fr.soat.soimmo.models.Address;

public class SearchAccommodationFragmentImpl extends BaseFragment implements SearchAccommodationFragment {

    @InjectView(R.id.test_button)
    Button testNetworkButton;

    @InjectView(R.id.accommodation_list)
    RecyclerView accommodationListView;

    @Inject
    SearchAccommodationFragmentPresenter presenter;

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

        AccommodationAdapter adapter = new AccommodationAdapter(createList());
        accommodationListView.setAdapter(adapter);
    }

    private List<Accommodation> createList() {

        List<Accommodation> result = new ArrayList<Accommodation>();

        result.add(createAccommodation(AccommodationType.APARTMENT, "Boulogne-Billancourt", 93d, 2300d, 4));
        result.add(createAccommodation(AccommodationType.APARTMENT, "Paris", 34d, 1100d, 2));
        result.add(createAccommodation(AccommodationType.HOUSE, "Plaisir", 101d, 1800d, 5));
        result.add(createAccommodation(AccommodationType.APARTMENT, "Paris", 18d, 700d, 1));
        result.add(createAccommodation(AccommodationType.APARTMENT, "Paris", 18d, 700d, 1));
        result.add(createAccommodation(AccommodationType.APARTMENT, "Paris", 18d, 700d, 1));
        result.add(createAccommodation(AccommodationType.APARTMENT, "Paris", 18d, 700d, 1));
        result.add(createAccommodation(AccommodationType.APARTMENT, "Paris", 18d, 700d, 1));
        result.add(createAccommodation(AccommodationType.APARTMENT, "Paris", 18d, 700d, 1));
        result.add(createAccommodation(AccommodationType.APARTMENT, "Paris", 18d, 700d, 1));
        result.add(createAccommodation(AccommodationType.APARTMENT, "Paris", 18d, 700d, 1));
        result.add(createAccommodation(AccommodationType.APARTMENT, "Paris", 18d, 700d, 1));
        result.add(createAccommodation(AccommodationType.APARTMENT, "Paris", 18d, 700d, 1));
        result.add(createAccommodation(AccommodationType.APARTMENT, "Paris", 18d, 700d, 1));
        result.add(createAccommodation(AccommodationType.APARTMENT, "Paris", 18d, 700d, 1));
        result.add(createAccommodation(AccommodationType.APARTMENT, "Paris", 18d, 700d, 1));
        result.add(createAccommodation(AccommodationType.APARTMENT, "Paris", 18d, 700d, 1));
        result.add(createAccommodation(AccommodationType.APARTMENT, "Paris", 18d, 700d, 1));


        return result;
    }

    private Accommodation createAccommodation(AccommodationType type, String city, Double surface, Double rent, int rooms) {
        Accommodation accommodation = new Accommodation();
        Address address = new Address();
        address.setCity(city);
        accommodation.setType(type);
        accommodation.setAddress(address);
        accommodation.setSurface(surface);
        accommodation.setRent(rent);
        accommodation.setRooms(rooms);
        return accommodation;
    }

}