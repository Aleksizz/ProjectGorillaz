package com.javarush.balykova.repository;

import com.javarush.balykova.entity.AbstractEntity;

public class TestEntity implements AbstractEntity {
    private String name;

    public TestEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return 0L;
    }

    @Override
    public void setId(Long id) {

    }
}