package fr.soat.soimmo.persistence.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import fr.soat.soimmo.models.Accommodation;
import fr.soat.soimmo.persistence.AccommodationPersistence;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by Formation on 01/12/2014.
 */
public class AccommodationPersistenceRest implements AccommodationPersistence {

    private final AccommodationPersistenceRestService service;

    public AccommodationPersistenceRest() {

        // Read date as timestamp
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        }).create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://10.0.2.2:8080")
                .setConverter(new GsonConverter(gson))
                .build();

        this.service = restAdapter.create(AccommodationPersistenceRestService.class);
    }

    @Override
    public void save(Accommodation object) {

    }

    @Override
    public Accommodation get(int id) {
        return null;
    }

    @Override
    public List<Accommodation> getAll() {
        return service.getAll();
    }

    @Override
    public List<Accommodation> get(int startIndex, int quantity) {
        return service.get(startIndex, quantity);
    }
}
