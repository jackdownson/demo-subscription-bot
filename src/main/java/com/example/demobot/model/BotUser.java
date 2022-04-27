package com.example.demobot.model;

import com.example.demobot.validation.UUID;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity(name = "bot_users")
@ToString
public class BotUser {

    @Id
    @UUID
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "telegram_id")
    private String telegramId;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "is_bot")
    private Boolean isBot;

    @Column(name = "lang")
    private String languageCode;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;


}
