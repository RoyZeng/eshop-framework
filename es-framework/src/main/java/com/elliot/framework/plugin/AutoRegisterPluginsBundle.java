package com.elliot.framework.plugin;

import com.elliot.context.EopContext;
import com.elliot.context.ParamSetting;
import com.elliot.context.model.EopSite;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自动注册插件桩
 * 
 * @author andy
 * 
 */
public abstract class AutoRegisterPluginsBundle implements IPluginBundle {
    protected static final Log loger = LogFactory
            .getLog(AutoRegisterPluginsBundle.class);

    protected List<IPlugin> plugins;
    private Map<String, List<IPlugin>> saasPlugins;

    public void registerPlugin(IPlugin plugin) {
        if ("2".equals(ParamSetting.RUNMODE)) {
            registerPlugin2(plugin);
        }

        if ("1".equals(ParamSetting.RUNMODE))
            registerPlugin1(plugin);

    }


    private void registerPlugin1(IPlugin plugin)
/*     */   {
/*  47 */     if (this.plugins == null) {
/*  48 */       this.plugins = new ArrayList();
/*     */     }
/*     */
/*  51 */     if (!this.plugins.contains(plugin)) {
/*  52 */       this.plugins.add(plugin);
/*     */     }
/*     */
/*  55 */     if (loger.isDebugEnabled())
/*  56 */       loger.debug("为插件桩" + getName() + "注册插件：" + plugin.getClass());
/*     */   }
    /*     */
/*     */   private void registerPlugin2(IPlugin plugin)
/*     */   {
/*  65 */     if (this.saasPlugins == null) {
/*  66 */       this.saasPlugins = new HashMap();
/*     */     }
/*     */
/*  69 */     String key = getKey();
/*     */
/*  71 */     List pluginList = (List)this.saasPlugins.get(key);
/*     */
/*  73 */     if (pluginList == null) {
/*  74 */       pluginList = new ArrayList();
/*  75 */       this.saasPlugins.put(key, pluginList);
/*     */     }
/*  77 */     if (!pluginList.contains(plugin))
/*  78 */       pluginList.add(plugin);
/*     */   }

    abstract public String getName();

    public synchronized List<IPlugin> getPlugins() {
        if ("2".equals(ParamSetting.RUNMODE)) {
            if (this.saasPlugins == null) {
                return new ArrayList();
            }

            String key = getKey();
            List pluginList = (List) this.saasPlugins.get(key);
            if (pluginList == null) {
                return new ArrayList();
            }
            return pluginList;
        }

        return this.plugins;
    }

    private String getKey() {
        EopSite site = EopContext.getContext().getCurrentSite();
        int userid = site.getUserid().intValue();
        int siteid = site.getId().intValue();
        String key = userid + "_" + siteid;

        return key;
    }
    public synchronized void unRegisterPlugin(IPlugin _plugin)
/*     */   {
/*  87 */     if ("2".equals(ParamSetting.RUNMODE))
/*     */     {
/*  89 */       if (this.saasPlugins == null) {
/*  90 */         return;
/*     */       }
/*     */
/*  93 */       String key = getKey();
/*  94 */       List pluginList = (List)this.saasPlugins.get(key);
/*  95 */       if (pluginList == null) {
/*  96 */         return;
/*     */       }
/*  98 */       pluginList.remove(_plugin);
/*     */     }
/* 103 */     else if (this.plugins != null) {
/* 104 */       this.plugins.remove(_plugin);
/*     */     }
/*     */   }

}

