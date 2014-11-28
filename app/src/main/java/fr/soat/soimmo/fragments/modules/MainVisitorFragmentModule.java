package fr.soat.soimmo.fragments.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fr.soat.soimmo.activities.modules.MainActivityModule;
import fr.soat.soimmo.fragments.FragmentView;
import fr.soat.soimmo.fragments.MainFragment;
import fr.soat.soimmo.fragments.MainVisitorFragment;
import fr.soat.soimmo.fragments.presenters.FragmentPresenter;
import fr.soat.soimmo.fragments.presenters.MainFragmentPresenter;
import fr.soat.soimmo.fragments.presenters.MainVisitorFragmentPresenter;

@Module(
        injects = {
                MainVisitorFragment.class
        },
        addsTo = MainActivityModule.class
)
public class MainVisitorFragmentModule {

    private MainFragment view;

    private MainFragmentPresenter presenter;

    public MainVisitorFragmentModule(MainFragment view) {
        this.view = view;
    }

    @Provides
    @Singleton
    FragmentView provideFragmentView() {
        return view;
    }

    @Provides @Singleton
    MainFragment provideView() {
        return view;
    }

    @Provides @Singleton
    FragmentPresenter provideFragmentPresenter(MainVisitorFragmentPresenter presenter) {
        return initPresenter(presenter);
    }

    @Provides @Singleton
    MainFragmentPresenter provideMainFragmentPresenter(MainVisitorFragmentPresenter presenter) {
        return initPresenter(presenter);
    }

    MainVisitorFragmentPresenter initPresenter(MainVisitorFragmentPresenter presenter) {
        if (this.presenter == null)
            this.presenter = presenter;
        return presenter;
    }
}