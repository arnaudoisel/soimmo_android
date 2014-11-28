package fr.soat.soimmo.activities.presenters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import fr.soat.soimmo.activities.ActivityView;
import fr.soat.soimmo.events.EventBus;
import fr.soat.soimmo.events.RestartCurrentAndTerminateOthersActivitiesEvent;
import fr.soat.soimmo.models.User;
import fr.soat.soimmo.modules.ForApplication;

import javax.inject.Inject;

public abstract class BaseActivityPresenter implements ActivityPresenter {

	@Inject
	ActivityView view;

	@Inject
	User user;

	@Inject @ForApplication
	EventBus appEventBus;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		view.setView(savedInstanceState);
		
		appEventBus.register(this);
	}

	@Override
	public void onRestart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDestroy() {
		appEventBus.unregister(this);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		
	}

	public void onEventMainThread(RestartCurrentAndTerminateOthersActivitiesEvent event) {  
		// On session change (login, switch profile or logout) we want the user to stay
		// where he is, so if this is the active activity, we relaunch it.
		if (view.isVisible())
			relaunchActivity();
		else
			terminateActivity();
	}
	
	private void relaunchActivity() {
		Activity activity = view.getActivity();
		Intent i = new Intent(activity, view.getClass());
		activity.finish();
		activity.startActivity(i);
	}
	
	private void terminateActivity() {
		view.getActivity().finish();
	}
}
