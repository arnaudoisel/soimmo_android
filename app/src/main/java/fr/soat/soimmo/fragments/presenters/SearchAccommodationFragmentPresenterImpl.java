package fr.soat.soimmo.fragments.presenters;

import javax.inject.Inject;

public class SearchAccommodationFragmentPresenterImpl
        extends BaseFragmentPresenter
        implements SearchAccommodationFragmentPresenter {

    @Inject
    public SearchAccommodationFragmentPresenterImpl(){
        super();
    }

    @Override
    protected boolean hasOptionsMenu() {
        return false;
    }
}
