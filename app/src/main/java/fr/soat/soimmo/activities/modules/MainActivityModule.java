package fr.soat.soimmo.activities.modules;

import android.app.Fragment;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fr.soat.soimmo.activities.ActivityView;
import fr.soat.soimmo.activities.MainActivity;
import fr.soat.soimmo.activities.MainActivityImpl;
import fr.soat.soimmo.activities.presenters.ActivityPresenter;
import fr.soat.soimmo.activities.presenters.MainActivityPresenter;
import fr.soat.soimmo.activities.presenters.MainActivityPresenterImpl;
import fr.soat.soimmo.fragments.MainLoggedInFragment;
import fr.soat.soimmo.fragments.MainVisitorFragment;
import fr.soat.soimmo.models.User;
import fr.soat.soimmo.modules.AppModule;
import fr.soat.soimmo.modules.ForActivity;

@Module (
		injects = MainActivityImpl.class,
        library = true,
		addsTo = AppModule.class
		)
public class MainActivityModule {

	private MainActivityImpl view;

	public MainActivityModule(MainActivityImpl view) {
		this.view = view;
	}

	@Provides @Singleton @ForActivity Context provideActivityContext() {
		return view;
	}

	@Provides
	ActivityView provideActivityView() {
		return view;
	}
	
	@Provides @Singleton
	MainActivity provideView() {
		return view;
	}

	@Provides @Singleton
	ActivityPresenter providePresenter(MainActivityPresenter presenter) {
		return presenter;
	}

	@Provides @Singleton
	MainActivityPresenter providePresenter(MainActivityPresenterImpl presenter) {
		return presenter;
	}

	@Provides
	Fragment provideContentFragment(User user) {
		if (user == null)
			return new MainVisitorFragment();
		else
			return new MainLoggedInFragment();
	}

}
