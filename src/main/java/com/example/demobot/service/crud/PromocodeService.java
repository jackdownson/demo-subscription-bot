package com.example.demobot.service.crud;

import com.example.demobot.model.Promocode;
import com.example.demobot.repository.PromocodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromocodeService {

    private final PromocodeRepository promocodeRepository;

    public Promocode getNextValidPromocode() {
        return promocodeRepository.findTopByRedeemedFalse();
    }



}
