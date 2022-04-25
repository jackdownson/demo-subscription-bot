package com.example.demobot.mapper;


import com.example.demobot.dto.BotUserDto;
import com.example.demobot.mapper.MapperToDto;
import com.example.demobot.mapper.MapperToEntity;
import com.example.demobot.model.BotUser;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BotUserDtoMapper extends MapperToDto<BotUserDto, BotUser>, MapperToEntity<BotUser, BotUserDto> {

        @Override
        BotUser toDto(BotUserDto v);

        @Override
        List<BotUser> toListDto(List<BotUserDto> v);


        @Override
        BotUserDto toEntity(BotUser botUser);

        @Override
        List<BotUserDto> toListEntity(List<BotUser> list);
}
