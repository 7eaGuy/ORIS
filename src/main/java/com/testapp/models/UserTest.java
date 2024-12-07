package com.testapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTest {
    private int id;
    private String TestTitle;
    private int userId;
    private int quizId;
    private int score;
}
