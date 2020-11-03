package hibernate.dao;

public interface DAO<Entity, Key> {
    void create(Entity entity);
    void update(Entity entity);
    void delete(Entity entity);
    Entity read(Key key);
}
