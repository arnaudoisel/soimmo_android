package fr.soat.soimmo.fragments;

import android.view.View;

public interface SearchAccommodationFragment extends FragmentView {

    void closeResourcesOnDestroy();

    void setTestNetworkClickListener(View.OnClickListener listener);

}
