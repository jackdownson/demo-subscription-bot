package com.example.demobot.mapper;

import java.util.List;

public interface MapperToEntity<T,V> {

    V toEntity (T t);

    List<V> toListEntity(List<T> list);
}
