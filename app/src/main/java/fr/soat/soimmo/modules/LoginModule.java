package fr.soat.soimmo.modules;

import android.content.Context;

import fr.soat.soimmo.events.EventBus;
import fr.soat.soimmo.models.User;
import fr.soat.soimmo.services.LoginManager;
import fr.soat.soimmo.services.SessionManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module (
		complete = false,
		library = true
		)
public class LoginModule {

	@Provides @Singleton
	SessionManager provideSessionManager(@ForApplication Context context, @ForApplication EventBus eventBus) {
		return new SessionManager(context, eventBus);
	}
	
	@Provides @Singleton
	LoginManager provideLoginManager(@ForApplication Context context, SessionManager sessionManager) {
		return new LoginManager(context, sessionManager);
	}
	
	@Provides
	User provideUser(LoginManager loginManager) {
		return loginManager.getUser();
	}

}
