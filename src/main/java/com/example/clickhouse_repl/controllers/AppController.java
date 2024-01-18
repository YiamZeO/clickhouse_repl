package com.example.clickhouse_repl.controllers;

import com.example.clickhouse_repl.repositories.DetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/get_details")
public class AppController {
    private final DetailsRepository detailsRepository;

    @Autowired
    public AppController(DetailsRepository detailsRepository) {
        this.detailsRepository = detailsRepository;
    }

    @GetMapping("/get_data")
    public List<Map<String, Object>> getData(@RequestParam @NotNull String tablePrefix,
                                             @RequestParam @NotNull String type,
                                             @RequestParam @NotNull int day) {
        return detailsRepository.getData(tablePrefix, type, day);
    }

    @GetMapping("/get_comments")
    public Map<String, String> getComments(@RequestParam @NotNull String tablePrefix,
                                                 @RequestParam @NotNull String type,
                                                 @RequestParam @NotNull int day) {
        return detailsRepository.getComments(tablePrefix, type, day);
    }
}
