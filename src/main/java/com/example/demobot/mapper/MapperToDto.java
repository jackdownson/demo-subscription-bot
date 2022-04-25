package com.example.demobot.mapper;

import java.util.List;

public interface MapperToDto<T,V> {

    V toDto(T v);

    List<V> toListDto(List<T> v);

}
