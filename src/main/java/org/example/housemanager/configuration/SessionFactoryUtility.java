package org.example.housemanager.configuration;

import org.example.housemanager.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryUtility {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Apartment.class);
            configuration.addAnnotatedClass(Building.class);
            configuration.addAnnotatedClass(Company.class);
            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(Pet.class);
            configuration.addAnnotatedClass(Resident.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }
}
