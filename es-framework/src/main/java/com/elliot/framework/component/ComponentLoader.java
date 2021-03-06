package com.elliot.framework.component;

import com.elliot.framework.component.ComponentView;
import com.elliot.framework.component.IComponent;
import com.elliot.framework.component.context.ComponentContext;
import com.elliot.framework.plugin.AutoRegisterPlugin;
import com.elliot.framework.plugin.IPluginBundle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.List;


public class ComponentLoader implements BeanPostProcessor {


    public Object postProcessAfterInitialization(Object bean, String arg1)
            throws BeansException {

        return bean;

    }

    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {

        AutoRegisterPlugin plugin;

        if ((bean instanceof AutoRegisterPlugin)) {

            plugin = (AutoRegisterPlugin) bean;

            if (plugin.getBundleList() != null) {

                List<IPluginBundle> pluginBundelList = plugin.getBundleList();

                for (IPluginBundle bundle : pluginBundelList) {

                    bundle.registerPlugin(plugin);

                }


            }

        }

        if ((bean instanceof IComponent)) {

            IComponent component = (IComponent) bean;

            ComponentView componentView = new ComponentView();

            componentView.setComponent(component);

            componentView.setComponentid(beanName);

            ComponentContext.registerComponent(componentView);

        }

        return bean;

    }

}
