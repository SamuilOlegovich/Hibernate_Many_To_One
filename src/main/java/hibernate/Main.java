package hibernate;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import hibernate.model.Engine;
import hibernate.dao.CarDAO;
import hibernate.model.Car;
import hibernate.dao.DAO;



public class Main {
    private static Integer idNewCars;

    public static void main(String[] args) {
        SessionFactory factory = null;
        try {
            factory = new Configuration().configure().buildSessionFactory();
            DAO<Car, Integer> dao = new CarDAO(factory);
            /**
             * Раскоментируя интересующий метод помните что обращение к данным происходит по id.
             * Убедитесь что данные для методов create update и delete существуют.  */
            read(dao);
            update(dao);
            create(dao);
            delete(dao);
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }




    private static void read(DAO<Car, Integer> carDao) {
        final Car result = carDao.read(1);
        System.out.println("\nRead: " + result + "\n");
    }



    private static void update(DAO<Car, Integer> carDao) {
        final Car result = carDao.read(1);
        result.setModel("AUDI");
        result.getEngine().setPower(500);
        result.getEngine().setModel("V8 TSI");
        carDao.update(result);
        System.out.println("\nUpdated: " + carDao.read(1) + "\n");
    }



    private static void create(DAO<Car, Integer> carDao) {
        Car car = new Car();
        car.setModel("Q8");
        car.setMark("AUDI");
        Engine engine = new Engine();
        engine.setModel("W12 Disel");
        engine.setPower(900);
        car.setEngine(engine);
        carDao.create(car);
        idNewCars = car.getId();
        System.out.println("\nCreated: " + carDao.read(idNewCars) + "\n");
    }



    private static void delete(DAO<Car, Integer> carDao) {
        Car car = new Car();
        car.setModel("Q8");
        car.setMark("AUDI");
        /**
         * Можно без перечислений, удалить просто по айди --> car.setId(idNewCars);  */
        car.setId(idNewCars);
        Engine engine = new Engine();
        engine.setModel("W12 Disel");
        engine.setPower(900);
        engine.setId(idNewCars);
        car.setEngine(engine);
        carDao.delete(car);
    }
}