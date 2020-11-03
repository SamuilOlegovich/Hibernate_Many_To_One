package hibernate.dao;


import org.hibernate.SessionFactory;
import org.hibernate.Hibernate;
import com.sun.istack.NotNull;
import org.hibernate.Session;
import hibernate.model.Car;




public class CarDAO implements DAO<Car, Integer> {
    /**
     * Connection factory to database
     * Фабрика подключения к базе данных  */
    private final SessionFactory factory;



    public CarDAO(@NotNull final SessionFactory factory) {
        this.factory = factory;
    }



    @Override
    public void create(@NotNull final Car car) {
        /**
         * создать сессию (открываем ворота для работы..)
         * сессия сама не потокобезопасная  */
        try (final Session session = factory.openSession()) {
            /**
             * начать транзакцию (либо записали что-то либо нет)
             * если что-то произойдет в этот мометн - то все изменения откатятся    */
            session.beginTransaction();
            /**
             * создаем новый объект    */
            session.save(car);
            /**
             * получаем транзакцию и делаем коммит  */
            session.getTransaction().commit();
        }
    }



    @Override
    public Car read(@NotNull final Integer id) {
        try (final Session session = factory.openSession()) {
            final Car result = session.get(Car.class, id);
            if (result != null) {
                /**
                 * Когда читаем данные, данные не достаются каскадно.
                 * В итоге экономя ресурс нам выдаст только тот объект который мы запрашивали.
                 * При попытке взять и другой, в нашем случаи мотор ролучим ошибкую
                 * для того что бы все же забрать нам нужные поля мы используем следующий метод.
                 * И так нужно проделать и дальше если о объекта мотор есть еще
                 * какие-то поля с объектам которые надо загрузить.
                 * Одним словом проделываем ко всем полям которые надо загружать во всех вложеных обектах.  */
                Hibernate.initialize(result.getEngine());
            }
            return result;
        }
    }



    @Override
    public void update(@NotNull final Car car) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(car);
            session.getTransaction().commit();
        }
    }



    @Override
    public void delete(@NotNull final Car car) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(car);
            session.getTransaction().commit();
        }
    }
}
