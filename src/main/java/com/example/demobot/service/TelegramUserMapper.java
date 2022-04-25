package com.example.demobot.service;

import com.example.demobot.dto.BotUserDto;
import com.example.demobot.mapper.MapperToDto;
import com.example.demobot.mapper.MapperToEntity;
import com.example.demobot.model.BotUser;
import org.mapstruct.Mapper;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.List;

@Mapper
public interface TelegramUserMapper extends MapperToDto<BotUser, User>, MapperToEntity<User, BotUser>
//                                            MapperToDto<BotUserDto, BotUser>, MapperToEntity<BotUser, BotUserDto>
{
    @Override
    User toDto(BotUser v);

    @Override
    List<User> toListDto(List<BotUser> v);

    @Override
    BotUser toEntity(User user);

    @Override
    List<BotUser> toListEntity(List<User> list);
}
