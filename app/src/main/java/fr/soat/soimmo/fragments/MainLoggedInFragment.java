package fr.soat.soimmo.fragments;

import android.app.Fragment;

import java.util.Arrays;
import java.util.List;

import fr.soat.soimmo.R;
import fr.soat.soimmo.fragments.modules.MainLoggedInFragmentModule;

public class MainLoggedInFragment extends MainBaseFragment {

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new MainLoggedInFragmentModule(this));
    }

    @Override
    protected Fragment getFragmentForDrawerPosition(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new SearchAccommodationFragmentImpl();
                break;
            case 1:
                // TODO
                // Params fragment
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    protected int getDrawerItemsArrayId() {
        return R.array.logged_in_drawer_items;
    }

    @Override
    protected int getOptionsMenuId() {
        return R.menu.main_logged_in;
    }

}
