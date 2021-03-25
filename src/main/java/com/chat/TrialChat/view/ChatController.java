package com.chat.TrialChat.view;

import java.io.*;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@ManagedBean
@SessionScoped
//@WebServlet(name = "helloServlet", value = "/chat")
public class ChatController extends HttpServlet implements Serializable{
    private static final long serialVersionUID = 7971718495546510159L;

    public void init() {    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getServletPath();

    }

    public void destroy() {
    }
}