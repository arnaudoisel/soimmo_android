package fr.soat.soimmo.fragments.presenters;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import fr.soat.soimmo.events.CallNetworkEvent;
import fr.soat.soimmo.events.NetworkResultArrivedEvent;
import fr.soat.soimmo.fragments.SearchAccommodationFragment;
import fr.soat.soimmo.models.Accommodation;
import fr.soat.soimmo.persistence.AccommodationPersistence;
import fr.soat.soimmo.persistence.rest.AccommodationPersistenceRest;

public class SearchAccommodationFragmentPresenterImpl
        extends BaseFragmentPresenter
        implements SearchAccommodationFragmentPresenter {

    @Inject
    SearchAccommodationFragment view;

    @Inject
    public SearchAccommodationFragmentPresenterImpl(){
        super();
    }

    @Override
    protected boolean hasOptionsMenu() {
        return false;
    }

    @Override
    protected boolean needRegistrationToFragmentEventBus() {
        return true;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        view.setTestNetworkClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentEventBus.post(new CallNetworkEvent());
            }
        });
    }

    public void onEventAsync(CallNetworkEvent event) {
        AccommodationPersistence persistence = new AccommodationPersistenceRest();
        List<Accommodation> accommodations = persistence.getAll();

        fragmentEventBus.post(new NetworkResultArrivedEvent(accommodations));
    }

    public void onEventMainThread(NetworkResultArrivedEvent event){

        List<Accommodation> accommodations = event.getAccommodations();

        Toast toast = Toast.makeText(activityContext, "Result message passed ! " + accommodations.toString(), Toast.LENGTH_LONG);
        toast.show();
    }
}
