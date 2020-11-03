package hiber;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import hiber.dao.EngineDAO;
import hiber.model.Engine;
import hiber.dao.DAO;
import org.junit.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;




public class EngineDAOTest {
    private final Engine testEngine = new Engine();
    private DAO<Engine, String> engineDAO;
    private SessionFactory factory;


    @Before
    public void before() {
        factory = new Configuration().configure().buildSessionFactory();
        engineDAO = new EngineDAO(factory);
        testEngine.setModel("test");
        testEngine.setPower(1);
    }

    @After
    public void after() {
        if (engineDAO.read("test") != null) {
            engineDAO.delete(testEngine);
        }
        factory.close();
    }

    /**
     * @see hiber.dao.EngineDAO#create(Engine).
     * @see hiber.dao.EngineDAO#read(String).   */
    @Test
    public void whenCreateEngineThenEngineIsExist() {
        engineDAO.create(testEngine);
        final Engine result = engineDAO.read("test");
        assertThat(testEngine, is(result));
    }

    /**
     * @see hiber.dao.EngineDAO#update(Engine). */
    @Test
    public void whenEngineUpdateThenPowerHasNewValue() {
        engineDAO.create(testEngine);
        testEngine.setPower(2);
        engineDAO.update(testEngine);
        final Engine result = engineDAO.read("test");
        assertThat(result.getPower(), is(2));
    }

    /**
     * @see hiber.dao.EngineDAO#delete(Engine). */
    @Test
    public void whenEngineDeleteThenEngineNotExist() {
        engineDAO.create(testEngine);
        final Engine before = engineDAO.read("test");
        engineDAO.delete(testEngine);
        final Engine after = engineDAO.read("test");
        assertNotNull(before.getModel());
        assertNull(after.getModel());
    }
}
