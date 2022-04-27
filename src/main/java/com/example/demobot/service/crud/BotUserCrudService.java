package com.example.demobot.service.crud;


import com.example.demobot.model.BotUser;
import com.example.demobot.repository.BotUsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BotUserCrudService {

    private final BotUsersRepository botUsersRepository;

    public BotUser save(BotUser botUser) {
        String telegramIdToSave = botUser.getTelegramId();
        BotUser botUserByTelegramId = botUsersRepository.findBotUserByTelegramId(telegramIdToSave);
        log.info("botUserByTelegramId {}", botUserByTelegramId);

        if (botUserByTelegramId == null) {
            return botUsersRepository.save(botUser);
        } else {
            throw new RuntimeException("User Already exists!");
        }
    }

    public BotUser update(BotUser botUser) {
        //todo: implement
        return new BotUser();
    }

    public void deleteById(String id) {
        botUsersRepository.deleteById(id);

    }

    public BotUser getById(String id) {
        return botUsersRepository.getById(id);
    }


}
