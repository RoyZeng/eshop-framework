package com.elliot.framework.database.dbsolution;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.elliot.context.EopContext;
import com.elliot.context.ParamSetting;
import com.elliot.context.model.EopSite;
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
    public static boolean dbImport(String xml, String prefix){
        Connection conn = getConnection(null);
        IDBSolution dbsolution = getDBSolution();
        dbsolution.setPrefix(prefix);
        dbsolution.setConnection(conn);
        boolean result;
        if (ParamSetting.RUNMODE.equals("1")) {
            result = dbsolution.dbImport(xml);
        }else {
            EopSite site = EopContext.getContext().getCurrentSite();
            Integer userid = site.getUserid();
            Integer siteid = site.getId();
            result = dbsolution.dbSaasImport(xml, userid.intValue(), siteid.intValue());
            try {
                conn.close();
            }catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return result;
    }
    public static Connection getConnection(JdbcTemplate jdbcTemplate) {
        if (jdbcTemplate == null)
            jdbcTemplate = (JdbcTemplate)SpringContextHolder.getBean("jdbcTemplate");
        try{
            return jdbcTemplate.getDataSource().getConnection();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
