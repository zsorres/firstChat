package com.chat.TrialChat.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message")
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @JoinColumn
    @OneToOne
    private Conversation conversation;

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


}
