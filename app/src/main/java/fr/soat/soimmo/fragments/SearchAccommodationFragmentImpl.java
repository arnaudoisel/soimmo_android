package fr.soat.soimmo.fragments;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import fr.soat.soimmo.R;
import fr.soat.soimmo.fragments.modules.SearchAccommodationFragmentModule;
import fr.soat.soimmo.fragments.presenters.SearchAccommodationFragmentPresenter;

public class SearchAccommodationFragmentImpl extends BaseFragment implements SearchAccommodationFragment {

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

}