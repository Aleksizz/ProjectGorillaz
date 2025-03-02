package com.javarush.balykova.cmd;

import com.javarush.balykova.service.StatService;
import com.javarush.balykova.util.RequestHelpers;
import jakarta.servlet.http.HttpServletRequest;

import static com.javarush.balykova.util.Key.LIST_USER_STATISTICS;
import static com.javarush.balykova.util.Key.TOTAL_USER_STATISTICS;

@SuppressWarnings("unused")
public class Statistics implements Command {

    private final StatService statService;

    public Statistics(StatService statService) {
        this.statService = statService;
    }

    @Override
    public String doGet(HttpServletRequest req) {

        req.setAttribute(LIST_USER_STATISTICS, statService.getUserStatistics());
        req.setAttribute(TOTAL_USER_STATISTICS, statService.getTotalUserStatistics());
        return getView();
    }
}