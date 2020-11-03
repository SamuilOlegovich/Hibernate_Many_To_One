package hibernate;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import hibernate.model.Engine;
import hibernate.dao.CarDAO;
import hibernate.model.Car;
import hibernate.dao.DAO;
import org.junit.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


/**
 * Класс не доделан, пежде чем тестить - надо немного исправить его */
public class CarDAOTest {
    private final Car testCar = new Car();
    private DAO<Car, Integer> carDAO;
    private SessionFactory factory;


    @Before
    public void before() {
        factory = new Configuration().configure().buildSessionFactory();
        carDAO = new CarDAO(factory);
        testCar.setModel("test");
        testCar.setPower();
    }

    @After
    public void after() {
        if (carDAO.read("test") != null) {
            carDAO.delete(testCar);
        }
        factory.close();
    }

    /**
     * @see hibernate.dao.CarDAO#create(Car) .
     * @see hibernate.dao.CarDAO#read(Integer).   */
    @Test
    public void whenCreateEngineThenEngineIsExist() {
        carDAO.create(testCar);
        final Engine result = carDAO.read("test");
        assertThat(testCar, is(result));
    }

    /**
     * @see hibernate.dao.CarDAO#update(Car). */
    @Test
    public void whenEngineUpdateThenPowerHasNewValue() {
        carDAO.create(testCar);
        testCar.setPower(2);
        carDAO.update(testCar);
        final Engine result = carDAO.read("test");
        assertThat(result.getPower(), is(2));
    }

    /**
     * @see hibernate.dao.CarDAO#delete(Car). */
    @Test
    public void whenEngineDeleteThenEngineNotExist() {
        carDAO.create(testCar);
        final Engine before = carDAO.read("test");
        carDAO.delete(testCar);
        final Engine after = carDAO.read("test");
        assertNotNull(before.getModel());
        assertNull(after.getModel());
    }
}
