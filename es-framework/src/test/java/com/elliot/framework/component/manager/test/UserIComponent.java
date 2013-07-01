package com.elliot.framework.component.manager.test;

import com.elliot.framework.component.IComponent;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: huangxa
 * Date: 13-6-5
 * Time: 下午5:04
 * To change this template use File | Settings | File Templates.
 */
@Component("userIComponent")
public class UserIComponent implements IComponent {
    @Override
    public void install() {
        System.out.println("install component!");
    }

    @Override
    public void unInstall() {
        System.out.println("install component!");
    }
}
