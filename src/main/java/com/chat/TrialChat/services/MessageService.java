package com.chat.TrialChat.services;

import com.chat.TrialChat.models.Message;
import com.chat.TrialChat.models.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class MessageService {

    @PersistenceContext
    EntityManager entityManager;

    public MessageService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void delete(Long id) {
        Message message = entityManager.find(Message.class, id);
        if (message != null) {
            entityManager.remove(message);
        }
    }

    public Message createNew(User currentUser, String text) {
        Message message = new Message();
        message.setUser(currentUser);
        message.setMessage(text);
        entityManager.persist(message);
        return message;
    }

    public Message update(Message currentMessageEntity) {
        return entityManager.merge(currentMessageEntity);
    }

    public void commit() {
        entityManager.getTransaction().commit();
    }
}
