package com.elliot.plugin.member.manager.impl;

import com.elliot.framework.utils.FileUtil;
import com.elliot.framework.utils.StringUtil;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: huangxa
 * Date: 13-6-9
 * Time: 上午9:14
 * To change this template use File | Settings | File Templates.
 */
public class PermissionConfig {
    private static Map<String, Integer> authMap = new HashMap();
    public static int getAuthId(String type) {
        return ((Integer) authMap.get(type)).intValue();
    }

    static {
        try {
            InputStream in = FileUtil.getResourceAsStream("auth.properties");
            Properties props = new Properties();
            props.load(in);

            int allocation = StringUtil.toInt(props.getProperty("auth.allocation"), true);
            int depot_allocation = StringUtil.toInt(props.getProperty("auth.depot_allocation"), true);
            int depot_ship = StringUtil.toInt(props.getProperty("auth.depot_ship"), true);
            int depot_admin = StringUtil.toInt(props.getProperty("auth.depot_admin"), true);
            int finance = StringUtil.toInt(props.getProperty("auth.finance"), true);
            int order = StringUtil.toInt(props.getProperty("auth.order"), true);
            int goods = StringUtil.toInt(props.getProperty("auth.goods"), true);
            int depot_supper = StringUtil.toInt(props.getProperty("auth.depot_supper"), true);
            int customer_service = StringUtil.toInt(props.getProperty("auth.customer_service"), true);
            int add_goods = StringUtil.toInt(props.getProperty("auth.add_goods"), true);
            int goods_warn = StringUtil.toInt(props.getProperty("auth.goods_warn"), true);
            int super_admin = StringUtil.toInt(props.getProperty("auth.super_admin"), true);

            authMap.put("allocation", Integer.valueOf(allocation));
            authMap.put("depot_allocation", Integer.valueOf(depot_allocation));
            authMap.put("depot_ship", Integer.valueOf(depot_ship));
            authMap.put("depot_admin", Integer.valueOf(depot_admin));
            authMap.put("finance", Integer.valueOf(finance));
            authMap.put("order", Integer.valueOf(order));
            authMap.put("goods", Integer.valueOf(goods));
            authMap.put("depot_supper", Integer.valueOf(depot_supper));
            authMap.put("customer_service", Integer.valueOf(customer_service));
            authMap.put("add_goods", Integer.valueOf(add_goods));
            authMap.put("goods_warn", Integer.valueOf(goods_warn));
            authMap.put("super_admin", Integer.valueOf(super_admin));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
