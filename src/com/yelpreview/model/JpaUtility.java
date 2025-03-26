package com.yelpreview.model;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class JpaUtility
{
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("userPU");

    public static EntityManager getEntityManager()
    {
        return entityManagerFactory.createEntityManager();
    }

    public static void shutdown()
    {
        if (entityManagerFactory != null)
        {
            entityManagerFactory.close();
        }
    }
}
