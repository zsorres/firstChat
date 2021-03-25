package com.chat.TrialChat.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int message_id;

    @Column
    private String message;

    @Column
    private Date sendTime;

    @JoinColumn
    @ManyToOne
    private User user;

    @PrePersist
    public void prePersist() {
        this.sendTime = new Date();
    }

//    public Message(User user, String text) {
//        this.user = user;
//        this.message = text;
//        this.sendTime = new Date();
//    }

}
