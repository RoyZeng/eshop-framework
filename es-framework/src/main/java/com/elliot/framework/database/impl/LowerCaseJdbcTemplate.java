package com.elliot.framework.database.impl;

import com.elliot.context.ParamSetting;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


/**
 * 覆写jdbctemlate ，使用LowerCaseColumnMapRowMapper
 * @author andy
 * 2010-6-13上午11:05:32
 */
public class LowerCaseJdbcTemplate extends JdbcTemplate {
	@Override
	protected RowMapper getColumnMapRowMapper() {
		if("2".equals(ParamSetting.DBTYPE)){
			return new LowerCaseColumnMapRowMapper();
		}else{
			return new ColumnMapRowMapper();
		}
	}

}
