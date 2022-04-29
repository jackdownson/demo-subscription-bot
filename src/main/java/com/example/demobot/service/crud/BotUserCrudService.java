package com.example.demobot.service.crud;


import com.example.demobot.model.BotUser;
import com.example.demobot.repository.BotUsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BotUserCrudService {

    private final BotUsersRepository botUsersRepository;

    @Transactional
    public BotUser save(BotUser botUser) {
        return botUsersRepository.save(botUser);
    }


    public BotUser getByTelegramId(Integer id) {
        return botUsersRepository.findBotUserByTelegramId(String.valueOf(id));
    }

}
