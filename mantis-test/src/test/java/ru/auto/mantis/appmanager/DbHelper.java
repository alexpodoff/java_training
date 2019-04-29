package ru.auto.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.auto.mantis.model.MantisUser;

import java.util.HashSet;
import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public HashSet<MantisUser> users() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<MantisUser> result = session.createQuery("from MantisUser").list();
        session.getTransaction().commit();
        session.close();
        return new HashSet<>(result);
    }
}