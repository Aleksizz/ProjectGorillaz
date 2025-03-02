package com.javarush.balykova.service;

import com.javarush.balykova.entity.Game;
import com.javarush.balykova.entity.ResultGame;
import com.javarush.balykova.entity.User;
import com.javarush.balykova.entity.GameStatistics;
import com.javarush.balykova.repository.GameRepository;
import com.javarush.balykova.repository.UserRepository;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class StatService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public Collection<GameStatistics> getUserStatistics() {
        List<GameStatistics> list = new ArrayList<>();
        for (User user : userRepository.getAll()) {
            GameStatistics gameStatistics = calculateUserStatistics(user);
            list.add(gameStatistics);
        }
        return list;
    }

    public GameStatistics getTotalUserStatistics() {
        GameStatistics all = GameStatistics.builder().login("All Users").build();
        for (GameStatistics userStatistic : getUserStatistics()) {
            all.setTotal(all.getTotal() + userStatistic.getTotal());
            all.setWin(all.getWin() + userStatistic.getWin());
            all.setLost(all.getLost() + userStatistic.getLost());
        }
        return all;
    }

    private GameStatistics calculateUserStatistics(User user) {
        Game pattern = Game.builder().userId(user.getId()).build();
        List<Game> games = gameRepository.find(pattern).toList();
        long win = games.stream().filter(game -> game.getResultGame().equals(ResultGame.WIN)).count();
        long lost = games.stream().filter(game -> game.getResultGame().equals(ResultGame.LOSE)).count();
        return GameStatistics.builder()
                .login(user.getLogin())
                .win(win)
                .lost(lost)
                .total(win + lost)
                .build();
    }

}