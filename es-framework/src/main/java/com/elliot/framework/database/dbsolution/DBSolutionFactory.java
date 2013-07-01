package com.elliot.framework.database.dbsolution;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.elliot.context.ParamSetting;
import com.elliot.framework.spring.SpringContextHolder;
import org.springframework.jdbc.core.JdbcTemplate;



public class DBSolutionFactory {
	public static IDBSolution getDBSolution() {
		IDBSolution result = null;
		if (ParamSetting.DBTYPE.equals("1")) {
			result = SpringContextHolder.getBean("mysqlSolution");
		} else if (ParamSetting.DBTYPE.equals("2")) {
			result = SpringContextHolder.getBean("oracleSolution");
		} else if (ParamSetting.DBTYPE.equals("3")) {
			result = SpringContextHolder.getBean("sqlserverSolution");
		} else
			throw new RuntimeException("未知的数据库类型");
		return result;
	}
}
