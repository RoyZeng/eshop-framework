package com.elliot.framework.database.dbsolution;

import java.sql.Connection;
/**
 * 
 * @author andy
 * 
 * 数据库导入导出解决方案接口
 * 
 */
public interface IDBSolution {
	public boolean setConnection(Connection conn);
	/**
	 * 通过设置并创建JDBC连接
	 * @param driver
	 * @param url
	 * @param username
	 * @param password
	 */
	public boolean dbImport(String xml);
	public boolean dbExport(String[] tables, String xml);
	public String dbExport(String[] tables, boolean dataOnly);
	public int deleteTable(String table);
	public void setPrefix(String prefix);
    public boolean dbSaasImport(String xml, int userid, int siteid);
    public String dbSaasExport(String[] tables, boolean dataOnly, int userid, int siteid);
    public String toLocalType(String type, String size);
}
