package com.example.demobot.service;


import com.example.demobot.model.BotUser;
import com.example.demobot.repository.BotUsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BotUserCrudService {

    private final BotUsersRepository botUsersRepository;

    public BotUser save(BotUser botUser) {
        return botUsersRepository.save(botUser);

    }

    public BotUser update(BotUser botUser){
        //todo: implement
        return new BotUser();
    }

    public void deleteById(String id){
        botUsersRepository.deleteById(id);

    }

    public BotUser getById(String id) {
        return botUsersRepository.getById(id);
    }


}
