package com.shilov.sobes_bot.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity (name = "app_user")
public class User {
    @Id
    private Long id;
    private String state;
    @OneToOne
    @JoinColumn(name = "last_question_id")
    private Question lastQuestion;
}
