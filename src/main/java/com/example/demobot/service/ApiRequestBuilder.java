package com.example.demobot.service;

import com.example.demobot.dto.BotUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.objects.ChatMember;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Service
@RequiredArgsConstructor
@Slf4j

//TODO: resolve why @Value doesn't inject properly at start of service job
public class ApiRequestBuilder {

    @Value("${telegram.api-request}")
    private static String telegramApi;
    @Value("${telegram.botToken}")
    private static String botToken;

    private final String GET_CHAT_MEMBER = telegramApi + botToken + "/getChatMember";

    public ChatMember getChatMember(User user, String chatId) {

        GetChatMember getChatMember = new GetChatMember();
        getChatMember.setChatId(Long.parseLong(chatId));
        getChatMember.setUserId(user.getId());

        RestTemplate template = new RestTemplate();

        HttpEntity<BotUserDto> entity = new HttpEntity<>(new BotUserDto(chatId, user.getId().toString()));
        log.info("getchatMember request: {}", telegramApi + botToken + "/getChatMember");
        ResponseEntity<String> response = template.exchange("https://api.telegram.org/bot" + "5175383932:AAFkBSFTUQSU4o1o9Q4nIspzgOTMhJqIG1k" + "/getChatMember", HttpMethod.POST, entity, String.class);
        try {
            return getChatMember.deserializeResponse(response.getBody());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
            return new ChatMember();
        }
    }

}
