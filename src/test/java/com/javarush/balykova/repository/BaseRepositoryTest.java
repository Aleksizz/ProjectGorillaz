package com.javarush.balykova.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class BaseRepositoryTest {

    private BaseRepository<TestEntity> repository;

    @BeforeEach
    public void setUp() {
        repository = new BaseRepository<>() {
            @Override
            public Stream<TestEntity> find(TestEntity pattern) {
                return Stream.empty();
            }
        };
    }

    @Test
    public void testCreateAndGet() {
        TestEntity entity = new TestEntity("Test Entity");
        repository.create(entity);

        TestEntity result = repository.get(entity.getId());
        assertNotNull(result);
        assertEquals(entity.getName(), result.getName());
    }

    @Test
    public void testUpdate() {
        TestEntity entity = new TestEntity("Old Entity");
        repository.create(entity);

        entity.setName("Updated Entity");
        repository.update(entity);

        TestEntity result = repository.get(entity.getId());
        assertEquals("Updated Entity", result.getName());
    }

    @Test
    public void testDelete() {
        TestEntity entity = new TestEntity("Test Entity");
        repository.create(entity);

        repository.delete(entity);
        TestEntity result = repository.get(entity.getId());
        assertNull(result);
    }

}
