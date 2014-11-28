package fr.soat.soimmo.services.modules;

import fr.soat.soimmo.modules.AppModule;
import fr.soat.soimmo.services.AuthenticationService;

import dagger.Module;

@Module (
		injects = AuthenticationService.class,
		addsTo = AppModule.class)
public class AuthenticationServiceModule {
}
