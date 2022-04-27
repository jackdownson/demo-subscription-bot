package com.example.demobot.repository;

import com.example.demobot.model.Promocode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocodeRepository extends JpaRepository<Promocode, String> {

    Promocode findTopByRedeemedFalse();

    Promocode findPromocodeByTelegramId(String telegramId);



}
