package com.testapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    private int id;
    private String answerText;
    private boolean isCorrect;
    private int questionId;
}
