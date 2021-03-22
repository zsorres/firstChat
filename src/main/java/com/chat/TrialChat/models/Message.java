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
public class Message {

    @Id
    @GeneratedValue
    private int message_id;

    @Column
    private String message;

    @Column
    private Date sendTime;

    @Column
    @OneToOne
    private User user_To;

    @Column
    @OneToOne
    private User user_From;

}
