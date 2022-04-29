package com.example.demobot.service.validation;


import com.example.demobot.model.Promocode;
import com.example.demobot.repository.PromocodeRepository;
import com.example.demobot.service.http.ApiRequestBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.ChatMember;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscribeValidator {

    @Value("${telegram.chat.id}")
    private String chatId;

    private final ApiRequestBuilder apiRequest;
    private final PromocodeRepository promocodeRepository;

    public boolean validateIfSubscribed(Message message) {

        ChatMember chatMember = apiRequest.getChatMember(message.getFrom(), chatId);
        String status = chatMember.getStatus();
        return !status.equals("left");
    }

    public boolean isUserAlreadyHasPromocode(Message message) {
        Promocode promocodeByTelegramId = promocodeRepository.findPromocodeByTelegramId(
                String.valueOf(message.getChatId()));
        return promocodeByTelegramId != null;
    }


}
