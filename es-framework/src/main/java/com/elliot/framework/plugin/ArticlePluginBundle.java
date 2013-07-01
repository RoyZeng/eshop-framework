package com.elliot.framework.plugin;

import com.elliot.framework.model.DataField;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文章插件桩
 * 
 * @author andy
 */
public class ArticlePluginBundle extends AutoRegisterPluginsBundle {

	public String getName() {

		return "文章插件桩";
	}

	
	/**
	 * 根据DataField获取
	 * @param field
	 * @return
	 */
	public AbstractFieldPlugin getFieldPlugin(DataField field){
        List<IPlugin> plugins = getPlugins();
		if (plugins != null) {
			for (IPlugin plugin : plugins) {
				if (plugin instanceof AbstractFieldPlugin) {
					AbstractFieldPlugin fieldPlugin = (AbstractFieldPlugin) plugin;
					if(fieldPlugin.getId().equals(field.getShow_form())){
						return fieldPlugin;
					}
				}

			}
		}

		return null;
	}
	
	
	public void onSave(Map article,DataField field) {
		try {

			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof AbstractFieldPlugin) {
						AbstractFieldPlugin fieldPlugin = (AbstractFieldPlugin) plugin;
						if(fieldPlugin.getId().equals(field.getShow_form())){
							fieldPlugin.onSave(article ,field);
						}
					}  
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onSave(Map article) {
		try {

			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					  if (plugin instanceof IDataSaveEvent){
						IDataSaveEvent dataSaveEvent =(IDataSaveEvent) plugin;
						dataSaveEvent.onSave(article);
					}
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**数据删除事件 lzf add 2010-12-01
	 * @param catid
	 * @param articleid
	 */
	public void onDelete(Integer catid,Integer articleid){
		try{
			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					  if (plugin instanceof IDataDeleteEvent){
						  IDataDeleteEvent dataDeleteEvent = (IDataDeleteEvent) plugin;
						  dataDeleteEvent.onDelete(catid, articleid);
					}
					
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String onDisplay(DataField field,Object value){
		try {

			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof AbstractFieldPlugin) {
						AbstractFieldPlugin fieldPlugin = (AbstractFieldPlugin) plugin;
						if(fieldPlugin.getId().equals(field.getShow_form())){
							return  fieldPlugin.onDisplay(field, value);
						}
					}
				}
			}

			return "输入项"+ field.getShow_form() +"未找到插件解析";
		} catch (Exception e) {
			e.printStackTrace();
			return "输入项"+field.getShow_form()+"发生错误";
		}		
	}
	
	public List findPlugin(String id){
        List<IPlugin> plugins = getPlugins();
/*     */
/* 156 */     List pluginList = new ArrayList();
/* 157 */     if (plugins != null) {
/* 158 */       for (IPlugin plugin : plugins) {
/* 159 */         if ((plugin instanceof AbstractFieldPlugin))
/*     */         {
/* 161 */           pluginList.add(plugin);
/*     */         }
/*     */       }
/*     */     }
/* 165 */     return pluginList;
	}
	
	public List getPlugins(){
		return this.plugins;
	}

    @Override
    public void unRegisterPlugin(IPlugin paramIPlugin) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
