package com.example.clickhouse_repl.repositories.rep_impl;

import com.example.clickhouse_repl.repositories.DetailsRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DetailsRepositoryImpl implements DetailsRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final String DB_PREFIX = "deg_2023";

    public DetailsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Map<String, Object>> getData(String tablePrefix, String type, int day) {
        String tableName = tablePrefix + "_" + day + "_" + type;
        String sql = "SELECT * FROM " + DB_PREFIX + "." + tableName + " ORDER BY date ASC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Map<String, Object> rowMap = new HashMap<>();
            int colCount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= colCount; i++)
                rowMap.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
            return rowMap;
        });
    }

    @Override
    public Map<String, String> getComments(String tablePrefix, String type, int day) {
        String tableName = tablePrefix + "_" + day + "_" + type;
        String sql = "SELECT name, comment FROM system.columns WHERE table = '" + tableName
                + "' AND database = '" + DB_PREFIX + "' AND comment != ''";
        Map<String, String> columnInfo = new HashMap<>();
        jdbcTemplate.query(sql, (rs, rowNum) -> {
            columnInfo.put(rs.getString("name"), rs.getString("comment"));
            return null;
        });
        return columnInfo;
    }
}
