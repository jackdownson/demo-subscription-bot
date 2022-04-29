package com.example.demobot.service;

import com.example.demobot.mapper.TelegramUserMapper;
import com.example.demobot.model.BotUser;
import com.example.demobot.repository.BotUsersRepository;
import com.example.demobot.service.crud.BotUserCrudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final BotUserCrudService botUserCrudService;
    private final TelegramUserMapper userMapper;

    @Override
    public void register(User user) {
        if (botUserCrudService.getByTelegramId(user.getId()) == null) {
            BotUser botUser = userMapper.toEntity(user);
            botUser.setRegistrationDate(LocalDateTime.now());
            botUserCrudService.save(botUser);
            log.info("register - OK!");
        }


    }


}
