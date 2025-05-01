package com.shilov.sobes_bot.model;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.math3.analysis.function.Identity;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @OneToOne (cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn (name = "answer_id")
    private Answer answer;
}
