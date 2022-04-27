package com.example.demobot.model;

import com.example.demobot.validation.UUID;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity(name = "promocodes_table")
@ToString
public class Promocode {

    @Id
    @UUID
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name ="value")
    private String value;

    @Column(name ="redeem")
    private Boolean redeemed;

    @Column(name ="redeem_date")
    private LocalDateTime redeemDate;

    @Column(name = "telegram_id")
    private String telegramId;

}
