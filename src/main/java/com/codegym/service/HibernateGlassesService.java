package com.codegym.service;

import com.codegym.model.Glasses;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class HibernateGlassesService implements IGlassesService {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Glasses> findAll() {
        String queryStr = "SELECT c FROM Glasses AS c";
        TypedQuery<Glasses> query = entityManager.createQuery(queryStr, Glasses.class);
        return query.getResultList();
    }

    @Override
    public void save(Glasses glasses) {
        Transaction transaction = null;
        Glasses origin;
        if (glasses.getId() == 0) {
            origin = new Glasses();
        } else {
            origin = findById(glasses.getId());
        }
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            origin.setCode(glasses.getCode());
            origin.setPrice(glasses.getPrice());
            origin.setColor(glasses.getColor());
            origin.setDescription(glasses.getDescription());
            origin.setImg(glasses.getImg());
            session.saveOrUpdate(origin);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Glasses findById(int id) {
        String queryStr = "SELECT c FROM Glasses AS c WHERE c.id = :id";
        TypedQuery<Glasses> query = entityManager.createQuery(queryStr, Glasses.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void remove(int id) {
        Glasses glasses = findById(id);
        if (glasses != null) {
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.remove(glasses);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }
}