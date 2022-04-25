package com.example.demobot.service;

import com.example.demobot.dto.BotUserDto;
import lombok.RequiredArgsConstructor;
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
public class ApiRequestBuilder {

    @Value("${telegram.api-request}")
    private String telegramApi;
    @Value("${telegram.botToken}")
    private String botToken;

    private final String GET_CHAT_MEMBER = telegramApi + botToken + "/getChatMember";

    public ChatMember getChatMember(User user, String chatId) {

        GetChatMember getChatMember = new GetChatMember();
        getChatMember.setChatId(Long.parseLong(chatId));
        getChatMember.setUserId(user.getId());

        RestTemplate template = new RestTemplate();

        HttpEntity<BotUserDto> entity = new HttpEntity<>(new BotUserDto(chatId, user.getId().toString()));
        ResponseEntity<String> response = template.exchange(GET_CHAT_MEMBER, HttpMethod.POST, entity, String.class);
        try {
            return getChatMember.deserializeResponse(response.getBody());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
            return new ChatMember();
        }
    }

}
