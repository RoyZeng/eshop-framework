package com.elliot.framework.manager;

import com.elliot.framework.model.DataModel;

import java.util.List;

/**
 * 数据模型管理
 * @author andy
 */
public interface IDataModelManager {
	/**
	 * 添加一个数据模型
	 * @param dataModel
	 */
	public void add(DataModel dataModel);
	
	
	/**
	 * 修改一个数据模型
	 * @param dataModel
	 */
	public void edit(DataModel dataModel);
	
	
	/**
	 * 删除一个数据模型
	 * @param modelid
	 */
	public void delete(Integer modelid);
	
	/**
	 * 读取所有数据模型列表
	 * @return
	 */
	public List<DataModel>  list();
	
	
	/**
	 * 获取一个数据模型
	 * @param modelid
	 * @return
	 */
	public DataModel get(Integer modelid);
	
	/**
	 * 检查数据模型是否已经被使用
	 * @param modelid
	 * @return 0:未被使用
	 */
	public int checkIfModelInUse(Integer modelid);
	
	
	
	
}
