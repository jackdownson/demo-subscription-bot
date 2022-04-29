package com.example.demobot.service;

import com.example.demobot.model.Promocode;
import com.example.demobot.repository.PromocodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RedeemingService {

    private final PromocodeRepository promocodeRepository;

    @Transactional
    public Promocode redeemToUser(Promocode promocode, Update update) {
        String userIdToReedeem = String.valueOf(update.getMessage().getFrom().getId());
        promocode.setTelegramId(userIdToReedeem);
        promocode.setRedeemDate(LocalDateTime.now());
        promocode.setRedeemed(true);

        return promocodeRepository.save(promocode);
    }
}
