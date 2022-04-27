package com.example.demobot.mapper;

import com.example.demobot.mapper.MapperToDto;
import com.example.demobot.mapper.MapperToEntity;
import com.example.demobot.model.BotUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TelegramUserMapper extends MapperToDto<BotUser, User>, MapperToEntity<User, BotUser> {
    @Override
    User toDto(BotUser v);

    @Override
    List<User> toListDto(List<BotUser> v);

    @Override
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "telegramId", source = "id")
})
    BotUser toEntity(User user);

    @Override
    List<BotUser> toListEntity(List<User> list);
}
