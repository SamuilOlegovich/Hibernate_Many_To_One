package hibernate;

import hibernate.dao.CarDAO;
import hibernate.model.Car;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import hibernate.model.Engine;
import hibernate.dao.DAO;
import org.junit.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;




public class EngineDAOTest {
//    private final Car testCar = new Car();
//    private DAO<Car, Integer> carDAO;
//    private SessionFactory factory;
//
//
//    @Before
//    public void before() {
//        factory = new Configuration().configure().buildSessionFactory();
////        carDAO = new CarDAO(factory);
//        testCar.setModel("test");
//        testCar.setPower();
//    }
//
//    @After
//    public void after() {
//        if (engineDAO.read("test") != null) {
//            engineDAO.delete(testCar);
//        }
//        factory.close();
//    }
//
//    /**
//     * @see hibernate.dao.CarDAO#create(Car) .
//     * @see hibernate.dao.CarDAO#read(Integer).   */
//    @Test
//    public void whenCreateEngineThenEngineIsExist() {
//        engineDAO.create(testCar);
//        final Engine result = engineDAO.read("test");
//        assertThat(testCar, is(result));
//    }
//
//    /**
//     * @see hibernate.dao.CarDAO#update(Car). */
//    @Test
//    public void whenEngineUpdateThenPowerHasNewValue() {
//        engineDAO.create(testCar);
//        testCar.setPower(2);
//        engineDAO.update(testCar);
//        final Engine result = engineDAO.read("test");
//        assertThat(result.getPower(), is(2));
//    }
//
//    /**
//     * @see hibernate.dao.CarDAO#delete(Car). */
//    @Test
//    public void whenEngineDeleteThenEngineNotExist() {
//        engineDAO.create(testCar);
//        final Engine before = engineDAO.read("test");
//        engineDAO.delete(testCar);
//        final Engine after = engineDAO.read("test");
//        assertNotNull(before.getModel());
//        assertNull(after.getModel());
//    }
}
