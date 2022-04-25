package com.example.demobot.util.initservice;

import com.example.demobot.model.Promocode;
import com.example.demobot.repository.PromocodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DatabaseInitService {

    private final PromocodeRepository promocodeRepository;


    @Transactional
    public void initPromocodes() {
        getPromocodeValues()
                .forEach(this::enrichAndSavePromocodeEntity);
    }

    @SneakyThrows
    private Set<String> getPromocodeValues() {
        Set<String> promocodeValues = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(
                        "G:\\O_backup\\Java\\Projects\\sogresh-bot\\monbon_promocodes-telegram-20220407_2000.csv"));
        ) {
            String promocode = reader.readLine();
            while (promocode != null) {
                promocodeValues.add(promocode);
                promocode = reader.readLine();
            }

        }
        return promocodeValues;
    }

    private Promocode enrichAndSavePromocodeEntity(String promocodeValue) {
        Promocode prom = new Promocode();
        prom.setId(UUID.randomUUID().toString());
        prom.setRedeemed(false);
        prom.setValue(promocodeValue);
        return promocodeRepository.save(prom);

    }

}
