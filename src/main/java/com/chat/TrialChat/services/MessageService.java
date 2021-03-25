package com.chat.TrialChat.services;

import com.chat.TrialChat.models.Conversation;
import com.chat.TrialChat.models.Message;
import com.chat.TrialChat.models.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


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

    public Message getMessage(int id) {
        Message message = entityManager.find(Message.class, id);
        if (message != null) {
            return message;
        }else throw new Error("This message is not exist");
    }

    public Message create(Conversation conversation,User currentUser, String text) {
        Message message = new Message();
        message.setConversation(conversation);
        message.setUser(currentUser);
        message.setMessage(text);
        entityManager.persist(message);
        return message;
    }
//
//    public List<Message> getByConv(Conversation conversation) {
//        Conversation conv= entityManager.find(Conversation.class, conversation.getId());
//        for (Message )
//        return messages;
//    }

    public List<Message> getMessages(Conversation conversation) {
        return entityManager.createQuery("select c from Message c where c.conversation like :id")
                .setParameter("id", conversation)
                .getResultList();
    }


    public Message update(Message currentMessageEntity) {
        return entityManager.merge(currentMessageEntity);
    }

}
