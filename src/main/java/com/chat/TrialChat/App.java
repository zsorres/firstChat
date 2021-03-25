package com.chat.TrialChat;

import com.chat.TrialChat.models.Message;
import com.chat.TrialChat.models.User;
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

        JPAUtil util = new JPAUtil();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UserService userService = new UserService(entityManager);
        MessageService messageService = new MessageService(entityManager);

        System.out.println("Starting Transaction");
        entityManager.getTransaction().begin();
        User user = userService.getUser(40);
        String text = "Hogy a faszomba kapd be";
        Message message = messageService.createNew(user,text);
        System.out.println(message.getMessage());
        System.out.println(message.getUser());
        System.out.println(message.getSendTime());
        messageService.commit();
        System.out.println("Saving Employee to Database");

    }
}