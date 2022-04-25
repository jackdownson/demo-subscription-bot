package com.example.demobot.service;

import com.example.demobot.repository.BotUsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;


@Service
@RequiredArgsConstructor

public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final BotUsersRepository usersRepository;
    private final TelegramUserMapper userMapper;

    @Override
    public void register(User user) {
        usersRepository.save(userMapper.toEntity(user));

    }



}
