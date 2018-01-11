package me.chuzhe.bookstore.service.impl;

import me.chuzhe.bookstore.domain.dao.StatisticsDao;
import me.chuzhe.bookstore.service.StatisticsService;
import me.chuzhe.bookstore.web.dto.admin.StatisticsRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tang on 2017/6/23.
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    private final StatisticsDao statisticsDao;

    @Autowired
    public StatisticsServiceImpl(StatisticsDao statisticsDao) {
        this.statisticsDao = statisticsDao;
    }

    public Map getResult(StatisticsRequestDto dto) {
        statisticsDao.run(dto.getUsername(), dto.getIsbn(), dto.getCategory(), dto.getStartTime(), dto.getEndTime());

        Map<String, Integer> result = new HashMap<>();
        result.put("value_output", statisticsDao.getValueOutput());
        result.put("quantity_output", statisticsDao.getQuantityOutput());
        result.put("value_total", statisticsDao.getTotalValue());
        result.put("quantity_total", statisticsDao.getTotalQuantity());
        result.put("quantity_times_original_price_total", statisticsDao.getTotalQuantityTimesOriginalPrice());

        return result;
    }
}
