package fr.soat.soimmo.fragments.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fr.soat.soimmo.activities.modules.MainActivityModule;
import fr.soat.soimmo.events.EventBus;
import fr.soat.soimmo.events.EventBusImpl;
import fr.soat.soimmo.fragments.FragmentView;
import fr.soat.soimmo.fragments.MainFragment;
import fr.soat.soimmo.fragments.MainLoggedInFragment;
import fr.soat.soimmo.fragments.presenters.FragmentPresenter;
import fr.soat.soimmo.fragments.presenters.MainFragmentPresenter;
import fr.soat.soimmo.fragments.presenters.MainLoggedInFragmentPresenter;
import fr.soat.soimmo.modules.ForFragment;

@Module (
		injects = {
				MainLoggedInFragment.class
		},
		addsTo = MainActivityModule.class
		)
public class MainLoggedInFragmentModule {

	private MainFragment view;
	
	private MainFragmentPresenter presenter;

	public MainLoggedInFragmentModule(MainFragment view) {
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
	MainFragment provideMainFragmentView() {
		return view;
	}

	@Provides @Singleton
	FragmentPresenter provideFragmentPresenter(MainLoggedInFragmentPresenter presenter) {
		return initPresenter(presenter);
	}

	@Provides @Singleton
	MainFragmentPresenter provideMainFragmentPresenter(MainLoggedInFragmentPresenter presenter) {
		return initPresenter(presenter);
	}
	
	MainLoggedInFragmentPresenter initPresenter(MainLoggedInFragmentPresenter presenter) {
		if (this.presenter == null)
			this.presenter = presenter;
		return presenter;		
	}

}
