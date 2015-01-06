package fr.soat.soimmo.fragments.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fr.soat.soimmo.activities.modules.MainActivityModule;
import fr.soat.soimmo.events.EventBus;
import fr.soat.soimmo.events.EventBusImpl;
import fr.soat.soimmo.fragments.FragmentView;
import fr.soat.soimmo.fragments.LoginFragment;
import fr.soat.soimmo.fragments.LoginFragmentImpl;
import fr.soat.soimmo.fragments.presenters.FragmentPresenter;
import fr.soat.soimmo.fragments.presenters.LoginFragmentPresenter;
import fr.soat.soimmo.modules.ForFragment;

@Module(
        injects = {
                LoginFragmentImpl.class,
        },
        addsTo = MainActivityModule.class
)
public class LoginFragmentModule {

    private LoginFragment view;

    public LoginFragmentModule(LoginFragment view) {
        this.view = view;
    }

    @Provides @Singleton @ForFragment
    EventBus provideEventBus() {
        return new EventBusImpl(de.greenrobot.event.EventBus.getDefault());
    }

    @Provides @Singleton
    FragmentView provideFragmentView() {
        return view;
    }

    @Provides @Singleton
    LoginFragment provideLoginFragmentView() {
        return view;
    }

    @Provides
    @Singleton
    FragmentPresenter provideFragmentPresenter(LoginFragmentPresenter presenter) {
        return presenter;
    }

}
