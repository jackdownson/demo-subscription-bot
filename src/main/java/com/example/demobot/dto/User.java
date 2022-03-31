package com.example.demobot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private String chat_id;
    private String user_id;
}
