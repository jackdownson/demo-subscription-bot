package com.example.demobot.service;

import com.example.demobot.model.Promocode;
import com.example.demobot.service.crud.PromocodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class RedeemProcessService {

    private final PromocodeService promocodeService;
    private final RedeemingService redeemingService;


    public Promocode giveCouponAndRedeem(Update update){
        Promocode nextValidPromocode = promocodeService.getNextValidPromocode();
        redeemingService.redeemToUser(nextValidPromocode, update);

        return nextValidPromocode;
    }
}
