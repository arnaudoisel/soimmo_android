package fr.soat.soimmo.controllers;

import fr.soat.soimmo.events.EventBus;
import fr.soat.soimmo.events.RestartCurrentAndTerminateOthersActivitiesEvent;
import fr.soat.soimmo.events.SessionUpdatedEvent;
import fr.soat.soimmo.modules.ForApplication;

import javax.inject.Inject;

public class AppControllerImpl implements AppController {

	private EventBus eventBus;

	@Inject
	public AppControllerImpl(@ForApplication EventBus eventBus) {
		this.eventBus = eventBus;
		this.eventBus.register(this);
	}
	
	public void onEvent(SessionUpdatedEvent event) {
		doRestartCurrentActivityAndTerminateOthers();
	}
	
	private void doRestartCurrentActivityAndTerminateOthers() {
		eventBus.post(new RestartCurrentAndTerminateOthersActivitiesEvent());
	}
}
