package fr.soat.soimmo.persistence.rest;

import java.util.List;

import fr.soat.soimmo.models.Accommodation;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Formation on 01/12/2014.
 */
public interface AccommodationPersistenceRestService {

    @GET("/searchAll")
    List<Accommodation> getAll();

    @GET("/search")
    List<Accommodation> get(@Query("startIndex") int startIndex, @Query("quantity") int quantity);

}
