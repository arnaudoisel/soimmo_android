package fr.soat.soimmo.fragments.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fr.soat.soimmo.activities.modules.MainActivityModule;
import fr.soat.soimmo.fragments.FragmentView;
import fr.soat.soimmo.fragments.SearchAccommodationFragment;
import fr.soat.soimmo.fragments.SearchAccommodationFragmentImpl;
import fr.soat.soimmo.fragments.presenters.FragmentPresenter;
import fr.soat.soimmo.fragments.presenters.SearchAccommodationFragmentPresenter;
import fr.soat.soimmo.fragments.presenters.SearchAccommodationFragmentPresenterImpl;

@Module(
        injects = {
                SearchAccommodationFragmentImpl.class
        },
        addsTo = MainActivityModule.class
)
public class SearchAccommodationFragmentModule {

    private SearchAccommodationFragment view;

    private SearchAccommodationFragmentPresenter presenter;

    public SearchAccommodationFragmentModule(SearchAccommodationFragment view) {
        this.view = view;
    }

    @Provides
    @Singleton
    FragmentView provideFragmentView() {
        return view;
    }

    @Provides
    @Singleton
    FragmentPresenter provideFragmentPresenter(SearchAccommodationFragmentPresenterImpl presenter) {
        return initPresenter(presenter);
    }

    @Provides
    @Singleton
    SearchAccommodationFragmentPresenter provideSearchAccommodationFragmentPresenter(SearchAccommodationFragmentPresenterImpl presenter) {
        return initPresenter(presenter);
    }

    SearchAccommodationFragmentPresenter initPresenter(SearchAccommodationFragmentPresenter presenter) {
        if (this.presenter == null)
            this.presenter = presenter;
        return presenter;
    }

}
