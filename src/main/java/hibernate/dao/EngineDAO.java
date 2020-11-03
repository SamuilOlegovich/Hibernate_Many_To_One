package hibernate.dao;


//import lombok.NonNull;
import lombok.NonNull;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import hibernate.model.Engine;



/**
 * данный класс потокобезопасен и нет смысла его во что-то оборачивать (атомик референси)   */
public class EngineDAO implements DAO<Engine, String> {
    /**
     * Connection factory to database.
     * Фабрика подключения к базе данных.   */
    private final SessionFactory factory;

    public EngineDAO(@NonNull final SessionFactory factory) {
        this.factory = factory;

    }


    /**
     * Create new engine in engines table.
     * Создайте новый движок в таблице двигателей.
     *
     * @param engine for add.   */
    @Override
    public void create(@NonNull final Engine engine) {
        /**
         * создать сессию (открываем ворота для работы..)
         * сессия сама не потокобезопасная  */
        try (final Session session = factory.openSession()) {
            /**
             * начать транзакцию (либо записали что-то либо нет)
             * если что-то произойдет в этот мометн - то все изменения откатятся    */
            session.beginTransaction();
            /**
             *  создаем новый объект    */
//            session.save(engine);
            /**
             * метод может создать если такого айди еще нет
             * или же обновит информацию если уже есть такой айди   */
            session.saveOrUpdate(engine);
            /**
             * получаем транзакцию и делаем коммит  */
            session.getTransaction().commit();

        }
    }


    /**
     * Get engine by model.
     * Получите двигатель по модели.
     *
     * @param model for select.
     * @return engine with param model. */
    @Override
    public Engine read(@NonNull final String model) {
        try (final Session session = factory.openSession()) {
            final Engine result = session.get(Engine.class, model);
            return result != null ? result : new Engine();
        }
    }


    /**
     * Update engine state.
     * Обновить состояние движка.
     *
     * @param engine new state. */
    @Override
    public void update(@NonNull final Engine engine) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(engine);
            session.getTransaction().commit();
        }
    }


    /**
     * Delete engine.
     * Удалить двигатель.
     *
     * @param engine for delete.    */
    @Override
    public void delete(@NonNull final Engine engine) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(engine);
            session.getTransaction().commit();
        }
    }
}