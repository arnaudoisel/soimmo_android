package fr.soat.soimmo.events;

import fr.soat.soimmo.models.User;

public class SessionUpdatedEvent {

	private User user;
	
	public SessionUpdatedEvent(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
}
