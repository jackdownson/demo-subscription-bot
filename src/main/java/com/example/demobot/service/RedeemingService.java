package com.example.demobot.service;

import com.example.demobot.model.Promocode;
import com.example.demobot.repository.PromocodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RedeemingService {

    private final PromocodeRepository promocodeRepository;

    @Transactional
    public Promocode redeem(Promocode promocode) {
        promocode.setRedeemed(true);
        return promocodeRepository.save(promocode);
    }
}
