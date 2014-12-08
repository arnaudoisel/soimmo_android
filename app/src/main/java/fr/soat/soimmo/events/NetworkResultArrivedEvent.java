package fr.soat.soimmo.events;

import java.util.List;

import fr.soat.soimmo.models.Accommodation;

/**
 * Created by Formation on 01/12/2014.
 */
public class NetworkResultArrivedEvent {

    private List<Accommodation> accommodations;

    public NetworkResultArrivedEvent(List<Accommodation> accommodations) {
        this.accommodations = accommodations;
    }

    public List<Accommodation> getAccommodations() {
        return accommodations;
    }
}
