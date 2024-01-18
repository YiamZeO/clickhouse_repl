package com.example.clickhouse_repl.repositories;

import java.util.List;
import java.util.Map;

public interface DetailsRepository {
    List<Map<String, Object>> getData(String tablePrefix, String type, int day);

    Map<String, String> getComments(String tablePrefix, String type, int day);
}
