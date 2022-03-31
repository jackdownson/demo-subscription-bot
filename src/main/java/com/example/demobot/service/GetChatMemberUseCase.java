package com.example.demobot.service;

import com.example.demobot.util.FeedbackType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;

import java.util.Locale;

@Service
@PropertySource("classpath:application.yml")
@Slf4j
public class GetChatMemberUseCase {

    @Value("${telegram.chat.id}")
    private String chatId;
    @Value("${telegram.api-request}")
    private String telegramApi;
    @Value("${telegram.botToken}")
    private String botToken;

    private final MessageSource messageSource;

    public GetChatMemberUseCase(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    public boolean validateIfSubscribed(User user) {
        GetChatMember chatMember = new GetChatMember();
        chatMember.setChatId(Long.parseLong(chatId));
        chatMember.setUserId(user.getId());

        try {
            chatMember.validate();
            String status = chatMember.deserializeResponse(getChatMemberRequest(user)).getStatus();
            return !status.equals("left");
        } catch (TelegramApiValidationException
                | TelegramApiRequestException e
        ) {
            e.printStackTrace();
            return false;

        }
    }


    private String getChatMemberRequest(User user) {
        RestTemplate template = new RestTemplate();

        HttpEntity<com.example.demobot.dto.User> entity = new HttpEntity<>(new com.example.demobot.dto.User(chatId, user.getId().toString()));
        ResponseEntity<String> response = template.exchange(telegramApi + botToken + "/getChatMember", HttpMethod.POST, entity, String.class);
        return response.getBody();
    }


    public SendMessage generateFeedbackMessage(Update update) {
        Boolean isValid = validateIfSubscribed(update.getMessage().getFrom());
        if (Boolean.TRUE.equals(isValid)) {
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId());
            message.setText(generateStringMessage(FeedbackType.POSITIVE));

            return message;

        } else {
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId());
            message.setText(generateStringMessage(FeedbackType.NEGATIVE));

            return message;
        }
    }


    private String generateStringMessage(FeedbackType feedbackType)  {
        if (feedbackType.equals(FeedbackType.POSITIVE)) {
            return messageSource.getMessage("positive-feedback", new Object[]{"{coupon}"}, Locale.ENGLISH);
        } else {
            return messageSource.getMessage("negative-feedback", new Object[]{"@sogr_channel"}, Locale.ENGLISH);
        }
    }

}
