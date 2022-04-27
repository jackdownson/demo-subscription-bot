package com.example.demobot.service;

import com.example.demobot.mapper.TelegramUserMapper;
import com.example.demobot.model.BotUser;
import com.example.demobot.repository.BotUsersRepository;
import com.example.demobot.service.crud.BotUserCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final BotUserCrudService botUserCrudService;
    private final TelegramUserMapper userMapper;

    @Override
    public void register(User user) {
        BotUser botUser = userMapper.toEntity(user);
        botUser.setRegistrationDate(LocalDateTime.now());
        botUserCrudService.save(botUser);

    }



}
