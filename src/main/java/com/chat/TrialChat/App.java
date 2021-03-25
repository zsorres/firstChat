package com.chat.TrialChat;

import com.chat.TrialChat.models.Conversation;
import com.chat.TrialChat.models.Message;
import com.chat.TrialChat.models.User;
import com.chat.TrialChat.services.ConversationService;
import com.chat.TrialChat.services.MessageService;
import com.chat.TrialChat.services.UserService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.ApplicationPath;
import java.util.List;

@ApplicationPath("/api")
public class App {
    public static void main(String[] args) throws Exception {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UserService userService = new UserService(entityManager);
        MessageService messageService = new MessageService(entityManager);
        ConversationService conversationService = new ConversationService(entityManager);


        System.out.println("Starting Transaction");
        entityManager.getTransaction().begin();
        User zsolti = new User("Zsolti");
        User szabina = new User("szabina");
        userService.create(zsolti);
        userService.create(szabina);
        Conversation conversation = conversationService.create();
        Message zsoltiM = messageService.create(conversation,zsolti,"Anya.");
        Message SzabinaM = messageService.create(conversation,szabina,"Apa.");
        entityManager.getTransaction().commit();
        List<Message> messages = messageService.getMessages(conversation);
        for (Message message : messages) {
            System.out.println(message.getMessage());
        }

        System.out.println("Saving Employee to Database");

    }
}