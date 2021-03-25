package com.chat.TrialChat.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "conversation")
public class Conversation implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JoinColumn
    private int id;

    @Column
    private Date createTime;

    @JoinColumn
    @OneToOne
    private User creator;

    @PrePersist
    public void prePersist() {
        this.createTime = new Date();
    }

}
