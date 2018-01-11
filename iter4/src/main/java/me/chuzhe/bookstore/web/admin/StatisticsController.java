package me.chuzhe.bookstore.web.admin;

import me.chuzhe.bookstore.service.StatisticsService;
import me.chuzhe.bookstore.web.dto.admin.StatisticsRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by tang on 2017/6/7.
 */
@Controller
@RequestMapping("/admin/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    public ModelAndView getTest(StatisticsRequestDto dto, ModelAndView modelAndView) {

        modelAndView.setViewName("admin/statistics");
        modelAndView.addObject("stat", statisticsService.getResult(dto));
        modelAndView.addObject("dto", dto);

        return modelAndView;
    }
}
