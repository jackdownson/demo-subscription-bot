package com.example.demobot.service;

import com.example.demobot.model.Promocode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedeemProcessService {

    private final PromocodeService promocodeService;
    private final RedeemingService redeemingService;


    public Promocode giveCouponAndRedeem(){
        //TODO: save User which redeemed and set coupon to him;
        Promocode nextValidPromocode = promocodeService.getNextValidPromocode();
        redeemingService.redeem(nextValidPromocode);
        return nextValidPromocode;
    }
}
