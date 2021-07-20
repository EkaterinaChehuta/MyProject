package com.example.testEightList.Repos;

import com.example.testEightList.domain.Indicator;

import java.sql.SQLException;
import java.util.List;

public interface IndicatorRepos {
    List<Indicator> allIndicator() throws SQLException;
    Indicator getIndicatorById(int id) throws SQLException;
}
