package com.example.demobot.service;

import com.example.demobot.model.Promocode;
import com.example.demobot.service.crud.PromocodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class PromocodeServiceTest {
    @Autowired
    private PromocodeService promocodeService;


    @Test
    void getNextPromocodeTest() {

        Promocode nextPromocode = promocodeService.getNextValidPromocode();
        assert true;

    }

}