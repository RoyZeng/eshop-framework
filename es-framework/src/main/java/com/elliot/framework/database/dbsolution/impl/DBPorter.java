package com.elliot.framework.database.dbsolution.impl;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 导入、导出类的公共部分
 * @author andy
 * 
 */
public class DBPorter {

	protected DBSolution solution;

    public DBPorter(DBSolution solution){
        this.solution = solution;
    }

}
