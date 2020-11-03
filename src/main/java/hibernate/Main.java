package hibernate;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import hibernate.dao.EngineDAO;
import hibernate.model.Engine;
import hibernate.dao.DAO;



public class Main {

    public static void main(String[] args) {
        SessionFactory factory = null;

        try {
//            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); // если не находит файл
            factory = new Configuration().configure().buildSessionFactory();
            DAO<Engine, String> engineDAO = new EngineDAO(factory);

            /**
             * записываем в базу */
            final Engine engine = new Engine();
            engine.setModel("engine_model_03");
            engine.setPower(12345);
            engineDAO.create(engine);

            /**
             *  читаем из базы  */
            final Engine result = engineDAO.read("engine_model_03");
            System.out.println("\n  Created : " + result + "\n");

            /**
             * обновляем данные в базе (прежде надо достать обновляемые данные) */
            result.setPower(54321);
            engineDAO.update(result);
            /**
             * и выводим результат обновления   */
            final Engine update = engineDAO.read("engine_model_03");
            System.out.println("\n  Updated : " + update + "\n");

            /**
             * удаляем из базы данных   */
            Engine engineDelete = new Engine();
            engineDelete.setModel("engine_model_03");
            engineDAO.delete(engineDelete);
            // теперь пытаемся вытащить что-то по удаленному ключу, будет - Engine(model=null, power=null)
            System.out.println("\n  Deleted : " + engineDAO.read("engine_model_03") + "\n");
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }
}