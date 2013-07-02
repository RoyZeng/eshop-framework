package com.elliot.app.shop.component.goodscore.plugin.album;

import com.elliot.framework.plugin.AutoRegisterPlugin;
import com.enation.app.base.core.plugin.IOnSettingInputShow;
import org.springframework.stereotype.Component;


@Component
public class ImageSettingPlugin extends AutoRegisterPlugin
        implements IOnSettingInputShow {
    public String onShow() {
        return "setting";
    }

    public String getSettingGroupName() {
        return "photo";
    }

    public String getTabName() {
        return "图片设置";
    }

    public int getOrder() {
        return 1;
    }
}