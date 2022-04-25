package com.example.demobot.service.feedback;

import com.example.demobot.service.ApiRequestBuilder;
import com.example.demobot.service.RedeemProcessService;
import com.example.demobot.util.FeedbackType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.ChatMember;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Locale;

@Slf4j
@Service("SubscribedFeedback")
@PropertySource("classpath:application.yml")
@RequiredArgsConstructor
public class SubscribedFeedback implements FeedbackService {

    @Value("${telegram.chat.id}")
    private String chatId;
    @Value("${telegram.chat.name}")
    private String chatToSubscribe;

    private final MessageSource messageSource;
    private final ApiRequestBuilder apiRequest;
    private final RedeemProcessService redeemProcessService;

    public boolean validateIfSubscribed(User user) {

        ChatMember chatMember = apiRequest.getChatMember(user, chatId);
        String status = chatMember.getStatus();
        return !status.equals("left");
    }


    public SendMessage giveFeedback(Update update) {
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


    private String generateStringMessage(FeedbackType feedbackType) {
        if (feedbackType.equals(FeedbackType.POSITIVE)) {
            String promocode = redeemProcessService.giveCouponAndRedeem().getValue();
            return messageSource.getMessage("positive-feedback", new Object[]{promocode}, Locale.ENGLISH);
        } else {
            return messageSource.getMessage("negative-feedback", new Object[]{chatToSubscribe}, Locale.ENGLISH);
        }
    }

}
