package com.chat.TrialChat.services;


import com.chat.TrialChat.models.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class UserService {

    @PersistenceContext
    EntityManager entityManager;

    public UserService() {

    }

    public UserService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(User user) {
        entityManager.persist(user);
    }

    public User getUser(int id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            return user;
        }else throw new Error("This user is not exist");
    }

    public User getUserByUsername(String username) {
        Object obj = new User();
        try {
            obj = entityManager.createQuery("select c from User c where c.username like :username")
                    .setParameter("username", username)
                    .getSingleResult();
        }catch (NoResultException nre){

        }
        User user = (User) obj;
        return user;
    }

    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    public List<User> getUsers() {
        return entityManager.createQuery("select c from User c").getResultList();
    }

    @Transactional
    public void update(User user) {
        entityManager.merge(user);
    }
}
