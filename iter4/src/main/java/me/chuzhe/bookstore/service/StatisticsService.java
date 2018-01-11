package me.chuzhe.bookstore.service;

import me.chuzhe.bookstore.web.dto.admin.StatisticsRequestDto;

import java.util.Map;

/**
 * Created by tang on 2017/6/7.
 */
public interface StatisticsService {
    Map getResult(StatisticsRequestDto dto);
}
