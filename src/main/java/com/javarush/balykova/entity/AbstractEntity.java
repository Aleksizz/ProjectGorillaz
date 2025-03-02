package com.javarush.balykova.entity;

/**
 * Parent any entity. Use as parent in wildcard
 */
public interface AbstractEntity {

    Long getId();

    void setId(Long id);

}