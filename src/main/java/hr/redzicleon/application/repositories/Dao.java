package hr.redzicleon.application.repositories;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    
    Optional<T> get(int id);
     
    List<T> findAll();
     
    void save(T t);
     
    void update(T t);
     
    void delete(int id);
}