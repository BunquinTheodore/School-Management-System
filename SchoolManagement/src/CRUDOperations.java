import java.util.List;

public interface CRUDOperations<T> {
    boolean add(T entity);
    T get(int id);
    List<T> getAll();
    boolean update(T entity);
    boolean delete(int id);
}
