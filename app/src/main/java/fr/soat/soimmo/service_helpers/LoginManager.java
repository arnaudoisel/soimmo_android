package fr.soat.soimmo.service_helpers;

import android.content.Context;
import android.content.Intent;

import fr.soat.soimmo.models.AuthDetails;
import fr.soat.soimmo.models.User;
import fr.soat.soimmo.services.AuthenticationService;

public class LoginManager {
	
	public static final String INTENT_EXTRA_AUTH_DETAILS = "auth_details";

	private final Context context;
	private final SessionManager sessionManager;
	
	public LoginManager(Context context,
			SessionManager sessionManager) {
		this.context = context;
		this.sessionManager = sessionManager;
	}
	
	public synchronized void login(AuthDetails authDetails) {

		// TODO Check if this user is not already logged in
		
        Intent intent = new Intent(context, AuthenticationService.class);
        intent.putExtra(INTENT_EXTRA_AUTH_DETAILS, authDetails);
        context.startService(intent);
        
	}
	
	public synchronized void logout() {
		
		// TODO Invalidate session on server

		sessionManager.clearUser();
	}
	
	public synchronized User getUser() {
		return sessionManager.getUser();
	}
	
}
