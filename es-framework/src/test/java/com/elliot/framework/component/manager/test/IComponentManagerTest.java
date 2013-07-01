package com.elliot.framework.component.manager.test;

import com.elliot.framework.component.manager.impl.ComponentManager;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.naming.Context;

/**
 * Created with IntelliJ IDEA.
 * User: andy
 * Date: 13-6-4
 * Time: 下午2:19
 */
public class IComponentManagerTest {
    @Test
    public void testList(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/commons-db-config.xml");
        ComponentManager componentManager = (ComponentManager)context.getBean("componentManager");
        componentManager.list();
    }

    @Test
    public void testInstall(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/commons-db-config.xml");
        ComponentManager componentManager = (ComponentManager)context.getBean("componentManager");
        componentManager.install("userIComponent");

    }
}
