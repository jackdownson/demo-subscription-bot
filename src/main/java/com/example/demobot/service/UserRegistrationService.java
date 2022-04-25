package com.example.demobot.service;

import org.telegram.telegrambots.meta.api.objects.User;

public interface UserRegistrationService {

    void register(User user);
}
