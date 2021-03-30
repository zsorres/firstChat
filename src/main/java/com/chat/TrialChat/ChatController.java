package com.chat.TrialChat;

import com.chat.TrialChat.models.Conversation;
import com.chat.TrialChat.models.Message;
import com.chat.TrialChat.models.User;
import com.chat.TrialChat.services.ConversationService;
import com.chat.TrialChat.services.MessageService;
import com.chat.TrialChat.services.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.enterprise.context.ApplicationScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Named("chatBean")
@ApplicationScoped
@LocalBean
public class ChatController {


    protected EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
    protected EntityManager entityManager = entityManagerFactory.createEntityManager();
    protected UserService userService = new UserService(entityManager);
    protected MessageService messageService = new MessageService(entityManager);
    protected ConversationService conversationService = new ConversationService(entityManager);


    protected User user;
    protected Conversation conv = entityManager.find(Conversation.class,1);
    protected Message message;
    protected String messageText;
    protected List<Conversation> conversationList = conversationService.getConversation();
    protected List<User> users = userService.getUsers();
    protected List<Message> messageList = new ArrayList<>();
    private List<Message> columns = new ArrayList<>();
    protected int poll;

    public int getPoll() {
        return poll;
    }
    public void setPoll(int poll) {
        this.poll = poll;
    }
    public void increment() { poll++; }

    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Conversation> getConversationList() {
        return conversationList;
    }
    public void setConversationList(List<Conversation> conversationList) {
        this.conversationList = conversationList;
    }

    public List<Message> getMessageList() {
        return messageList;
    }
    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public String getMessageText() {
        return messageText;
    }
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Message getMessage() {
        return message;
    }
    public void setMessage(Message message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Conversation getConv() {
        return conv;
    }
    public void setConv(Conversation conv) {
        this.conv = conv;
    }

    public void createUser() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String username = ec.getRequestParameterMap().get("loginForm:username");
        entityManager.getTransaction().begin();
        User userByUsername = userService.getUserByUsername(username);
        if (users.contains(entityManager.find(User.class,userByUsername.getId()))) {
            user = entityManager.find(User.class,userByUsername.getId());
        }else userService.create(new User(username));
        entityManager.getTransaction().commit();
    }

    public void addNewMessage() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String input = ec.getRequestParameterMap().get("chatForm:message");
        entityManager.getTransaction().begin();
        messageText = input;
        System.out.println(FacesContext.getCurrentInstance().getMessages());
        messageService.create(conv,user,messageText);
        entityManager.getTransaction().commit();
    }

    public List<Message> lastTwentyMessages() {
        messageList.addAll(messageService.getLastTwentyMessages());
        return messageList;
    }

    public void redirectChat() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("chat.xhtml");
    }
}