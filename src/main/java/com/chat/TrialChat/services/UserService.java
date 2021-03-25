package com.chat.TrialChat.services;

import com.chat.TrialChat.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class UserService {

    @PersistenceContext
    EntityManager entityManager;

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
