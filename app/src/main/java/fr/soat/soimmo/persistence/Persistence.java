package fr.soat.soimmo.persistence;

import java.util.List;

/**
 * Created by Formation on 01/12/2014.
 */
public interface Persistence<T> {

    void save(T object);

    T get(int id);

    List<T> getAll();

    List<T> get(int startIndex, int quantity);

}
