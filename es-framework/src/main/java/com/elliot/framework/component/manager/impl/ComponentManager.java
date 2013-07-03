package com.elliot.framework.component.manager.impl;

import com.elliot.framework.component.ComponentView;
import com.elliot.framework.component.PluginView;
import com.elliot.framework.component.WidgetView;
import com.elliot.framework.component.context.ComponentContext;
import com.elliot.framework.component.context.WidgetContext;
import com.elliot.framework.component.manager.IComponentManager;

import java.util.ArrayList;
import java.util.List;

import com.elliot.framework.database.BaseSupport;
import com.elliot.framework.plugin.IPlugin;
import com.elliot.framework.plugin.IPluginBundle;
import com.elliot.framework.spring.SpringContextHolder;
import org.springframework.stereotype.Component;

@Component("componentManager")
public class ComponentManager extends BaseSupport
        implements IComponentManager
{
    @Override
    public void startComponents() {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("start components start...");
        }

        List<ComponentView> dbList = getDbList();
        for (ComponentView componentView : dbList) {
            if ((componentView.getInstall_state() != 2) &&
                    (componentView.getEnable_state() == 1)) {
                start(componentView.getComponentid());
            }

        }
        if (this.logger.isDebugEnabled())
            this.logger.debug("start components end!");
    }

    @Override
    public void saasStartComponents() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<ComponentView> list()
    {
        List viewList = new ArrayList();
        List<ComponentView> componentViews = ComponentContext.getComponents();

        List dbList = getDbList();

        if (componentViews != null) {
            for (ComponentView view : componentViews)
            {
                ComponentView componentView = (ComponentView)view.clone();

                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("load component[" + componentView.getName() + "] start ");
                }

                try
                {
                    componentView.setInstall_state(0);
                    componentView.setEnable_state(0);

                    loadComponentState(componentView, dbList);

                    if (this.logger.isDebugEnabled())
                        this.logger.debug("load component[" + componentView.getName() + "] end ");
                }
                catch (Exception e) {
                    this.logger.error("加载组件[" + componentView.getName() + "]出错", e);
                    componentView.setEnable_state(2);
                    componentView.setError_message(e.getMessage());
                }
                viewList.add(componentView);
            }
        }
        System.out.println("component size is "+viewList.size());
        return viewList;
    }

    private void loadComponentState(ComponentView componentView, List<ComponentView> dbList)
    {
        for (ComponentView dbView : dbList)
            if (dbView.getComponentid().equals(componentView.getComponentid()))
            {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("load component[" + componentView.getName() + "]state->install state:" + dbView.getInstall_state() + ",enable_state:" + dbView.getEnable_state());
                }

                componentView.setInstall_state(dbView.getInstall_state());
                componentView.setEnable_state(dbView.getEnable_state());
                componentView.setId(dbView.getId());

                if (componentView.getInstall_state() != 2)
                {
                    if (dbView.getEnable_state() != 0)
                    {
                        if (dbView.getEnable_state() != 1)
                        {
                            componentView.setError_message(dbView.getError_message());
                        }
                    }
                }
            }
    }

    public void install(String componentid)
    {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("install component[" + componentid + "]...");
        }
        try
        {
            ComponentView componentView = getComponentView(componentid);

            if (componentView != null) {
                componentView.getComponent().install();

                if (!isInDb(componentView.getComponentid())) {
                    ComponentView temp = (ComponentView)componentView.clone();
                    temp.setInstall_state(1);

                    this.baseDaoSupport.insert("component", temp);
                }
                else {
                    this.baseDaoSupport.execute("update component set install_state=1 where componentid=?", new Object[] { componentView.getComponentid() });
                }
            }
        } catch (RuntimeException e) {
            this.logger.error("安装组件[" + componentid + "]出错", e);
        }
    }

    private boolean isInDb(String componentid)
    {
        String sql = "select count(0)  from component where componentid=?";
        return this.baseDaoSupport.queryForInt(sql, new Object[] { componentid }) > 0;
    }

    public void unInstall(String componentid)
    {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("install component[" + componentid + "]...");
        }

        ComponentView componentView = getComponentView(componentid);
        if (componentView != null) {
            componentView.getComponent().unInstall();

            if (!isInDb(componentView.getComponentid())) {
                ComponentView temp = (ComponentView)componentView.clone();
                temp.setInstall_state(0);
                this.baseDaoSupport.insert("component", temp);
            } else {
                this.baseDaoSupport.execute("update component set install_state=0 where componentid=?", new Object[] { componentView.getComponentid() });
            }
        }
    }

    @Override
    public void start(String componentid) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("start component[" + componentid + "]...");
        }

        ComponentView componentView = getComponentView(componentid);

        List<PluginView> pluginList = componentView.getPluginList();
        IPlugin plugin;
        for (PluginView pluginView : pluginList) {
            String pluginid = pluginView.getId();
            plugin = (IPlugin) SpringContextHolder.getBean(pluginid);

            List<String> bundleList = pluginView.getBundleList();
            if (bundleList != null)
                for (String bundleId : bundleList) {
                    IPluginBundle pluginBundle = (IPluginBundle) SpringContextHolder.getBean(bundleId);
                    pluginBundle.registerPlugin(plugin);
                }
        }

        List<WidgetView> widgetList = componentView.getWidgetList();
        for (WidgetView widgetView : widgetList) {
            String widgetid = widgetView.getId();
            WidgetContext.putWidgetState(widgetid, Boolean.valueOf(true));
        }

        String sql = "update component set enable_state=1 where componentid=?";
        this.baseDaoSupport.execute(sql, new Object[]{componentid});

        if (this.logger.isDebugEnabled())
            this.logger.debug("start component[" + componentid + "] complete");
    }

    @Override
    public void stop(String paramString) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private ComponentView getComponentView(String componentid)
    {
        List<ComponentView> componentList = ComponentContext.getComponents();

        for (ComponentView componentView : componentList) {
            if (componentView.getComponentid().equals(componentid)) {
                return componentView;
            }
        }

        return null;
    }



    private List<ComponentView> getDbList()
    {
        String sql = "select * from component ";
        return this.baseDaoSupport.queryForList(sql, ComponentView.class, new Object[0]);
    }
}