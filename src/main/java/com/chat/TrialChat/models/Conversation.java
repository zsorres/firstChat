package com.chat.TrialChat.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Conversation {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @OneToOne
    private User userFrom;

    @Column
    @OneToOne
    private User userTo;

    @Column
    @OneToMany
    private List<Message> messageList;
}
