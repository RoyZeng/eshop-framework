package com.elliot.framework.database;

import org.apache.log4j.Logger;

import com.elliot.context.EsfContext;

public abstract class BaseSupport<T> {
	protected final Logger logger = Logger.getLogger(getClass());
		
	private IDBRouter baseDBRouter; 
	protected IDaoSupport<T> baseDaoSupport;  
	protected IDaoSupport<T> daoSupport;   

	/**
	 * 获取表名
	 * @return
	 */
	protected String getTableName(String moude){
		return baseDBRouter.getTableName(  moude);
		
	}

	/**
	 * 检测操作的“属主”合法性
	 * @param userid
	 */
	protected void checkIsOwner( final Integer userid){
		if(userid==null){
			throw new PermssionRuntimeException();
		}
		
		Integer suserid = EsfContext.getContext().getCurrentSite().getUserid();
		
		if(suserid.intValue()!=userid.intValue()){
			throw new PermssionRuntimeException();
		}
	}

	public IDaoSupport<T> getBaseDaoSupport() {
		return baseDaoSupport;
	}

	public void setBaseDaoSupport(IDaoSupport<T> baseDaoSupport) {
		this.baseDaoSupport = baseDaoSupport;
	}
	
	public void setDaoSupport(IDaoSupport<T> daoSupport) {
		this.daoSupport = daoSupport;
	}

	public IDBRouter getBaseDBRouter() {
		return baseDBRouter;
	}

	public void setBaseDBRouter(IDBRouter baseDBRouter) {
		this.baseDBRouter = baseDBRouter;
	}
}
