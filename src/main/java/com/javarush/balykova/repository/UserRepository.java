package com.javarush.balykova.repository;

import com.javarush.balykova.entity.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepository implements Repository<User> {

    private final Map<Long, User> map = new HashMap<>();

    public static final AtomicLong id = new AtomicLong(System.currentTimeMillis());

    public UserRepository() {
        map.put(1L, new User(1L, "Alisa", "qwerty"));
        map.put(2L, new User(2L, "Bob", ""));
        map.put(3L, new User(3L, "Carl", "admin"));
        map.put(4L, new User(4L, "Alexxizz", "admin"));
    }

    @Override
    public Collection<User> getAll() {
        return map.values();
    }

    @Override
    public Optional<User> get(long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public void create(User entity) {
        entity.setId(id.incrementAndGet());
        update(entity);
    }

    @Override
    public void update(User entity) {
        map.put(entity.getId(), entity);
    }

    @Override
    public void delete(User entity) {
        map.remove(entity.getId());
    }
}
