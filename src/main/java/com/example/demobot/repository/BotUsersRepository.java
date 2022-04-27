package com.example.demobot.repository;

import com.example.demobot.model.BotUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotUsersRepository extends JpaRepository<BotUser, String> {

    BotUser findBotUserByTelegramId(String telegramId);


}
