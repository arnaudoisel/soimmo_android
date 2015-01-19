package fr.soat.soimmo.persistence.local;

import java.util.List;

public interface DataSource<T> {

    boolean insert(T entity);
    boolean delete(T entity);
    boolean update(T entity);
    T read(int i);
    List<T> read();
    List<T> read(String selection, String[] selectionArgs,
              String groupBy, String having, String orderBy);

}
