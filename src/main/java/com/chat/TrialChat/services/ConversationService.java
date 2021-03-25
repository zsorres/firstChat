package com.chat.TrialChat.services;

import com.chat.TrialChat.models.Conversation;
import com.chat.TrialChat.models.Message;
import com.chat.TrialChat.models.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


public class ConversationService {

    @PersistenceContext
    EntityManager entityManager;
    protected List<Message> usedConversation = new ArrayList<>();

    public ConversationService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void delete(int id) {
        Conversation conversation = entityManager.find(Conversation.class, id);
        if (conversation != null) {
            entityManager.remove(conversation);
        }
    }

    public void add(Message message) {
        this.usedConversation.add(message);
    }

    public Conversation findById(int id) {
        Conversation conversation = entityManager.find(Conversation.class, id);
        if (conversation != null) {
            return conversation;
        }else throw new Error("This Conversation is not exist");
    }

    public void addMessage(Message message, int id) {
        entityManager.find(Conversation.class, id);
        this.usedConversation.add(message);
    }

    public Conversation create() {
        Conversation conversation = new Conversation();
        entityManager.persist(conversation);
        return conversation;
    }

    public Conversation update(Conversation currentConversationEntity) {
        return entityManager.merge(currentConversationEntity);
    }
}
