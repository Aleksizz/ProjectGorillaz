package com.javarush.balykova.service;

import com.javarush.balykova.entity.Game;
import com.javarush.balykova.entity.GameState;
import com.javarush.balykova.entity.User;
import com.javarush.balykova.entity.GameStatistics;
import com.javarush.balykova.repository.GameRepository;
import com.javarush.balykova.repository.UserRepository;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class StatService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public Collection<GameStatistics> getUserStatistics() {
        return userRepository.getAll()
                .stream()
                .map(this::calculateUserStatistics)
                .toList();
    }

    public GameStatistics getTotalUserStatistics() {
        GameStatistics all = GameStatistics.builder().login("ALL").build();
        for (GameStatistics userStatistic : getUserStatistics()) {
            all.setPlay(all.getPlay() + userStatistic.getPlay());
            all.setTotal(all.getTotal() + userStatistic.getTotal());
            all.setWin(all.getWin() + userStatistic.getWin());
            all.setLost(all.getLost() + userStatistic.getLost());
        }
        return all;
    }

    private GameStatistics calculateUserStatistics(User user) {
        Game pattern = Game.builder().userId(user.getId()).build();
        List<Game> games = gameRepository.find(pattern).toList();
        long win = games.stream().filter(game -> game.getGameState().equals(GameState.WIN)).count();
        long lost = games.stream().filter(game -> game.getGameState().equals(GameState.LOSE)).count();
        long play = games.stream().filter(game -> game.getGameState().equals(GameState.PLAY)).count();
        return GameStatistics.builder()
                .login(user.getLogin())
                .win(win)
                .lost(lost)
                .play(play)
                .total(win + lost + play)
                .build();
    }

}