package com.example.demobot.service.feedback;

import com.example.demobot.repository.PromocodeRepository;
import com.example.demobot.service.RedeemProcessService;
import com.example.demobot.service.validation.SubscribeValidator;
import com.example.demobot.util.FeedbackType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;

@Slf4j
@Service("SubscribedFeedback")
@PropertySource("classpath:application.yml")
@RequiredArgsConstructor
public class SubscribedFeedback implements FeedbackService {


    @Value("${telegram.chat.name}")
    private String chatToSubscribe;

    private final SubscribeValidator subscribeValidator;
    private final MessageSource messageSource;
    private final RedeemProcessService redeemProcessService;
    private final PromocodeRepository promocodeRepository;



    //todo: конечный автомат
    public SendMessage giveFeedback(Update update) {
        Boolean isSubscribed = subscribeValidator.validateIfSubscribed(update.getMessage());
        Boolean hasPromocode = subscribeValidator.isUserAlreadyHasPromocode(update.getMessage());

        if (isSubscribed && !hasPromocode) {
            return generateSendMessage(update, FeedbackType.POSITIVE);

        } else if (isSubscribed && hasPromocode) {
            return generateSendMessage(update, FeedbackType.ALREADY_HAS_PROM);

        } else if (!isSubscribed) {
            return generateSendMessage(update, FeedbackType.NEGATIVE);

        }
        return new SendMessage();
    }


    private String generateStringMessage(FeedbackType feedbackType, Update update) {
        if (feedbackType.equals(FeedbackType.POSITIVE)) {
            String promocode = redeemProcessService.giveCouponAndRedeem(update).getValue();
            return messageSource.getMessage("positive-feedback", new Object[]{promocode}, Locale.ENGLISH);

        } else if (feedbackType.equals(FeedbackType.ALREADY_HAS_PROM)) {
            String promocode = promocodeRepository.findPromocodeByTelegramId(String.valueOf(update.getMessage().getChatId())).getValue();
            return messageSource.getMessage("already-has-prom", new Object[]{promocode}, Locale.ENGLISH);
        }
        else {
            return messageSource.getMessage("negative-feedback", new Object[]{chatToSubscribe}, Locale.ENGLISH);
        }
    }

    private SendMessage generateSendMessage(Update update, FeedbackType feedbackType){
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText(generateStringMessage(feedbackType, update));
        message.enableMarkdown(true);

        return message;
    }

}
