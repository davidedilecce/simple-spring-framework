package base;

import java.util.List;

public interface BaseRepository<T> {

    void save(T obj);
    void delete(T obj);
    T get(Object id);
    List<T> find(LoadQuery q);

}
