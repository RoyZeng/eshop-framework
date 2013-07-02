package com.elliot.app.shop.component.point.plugin;

import com.elliot.framework.plugin.AutoRegisterPlugin;
import com.enation.app.base.core.plugin.IOnSettingInputShow;
import com.enation.framework.plugin.page.JspPageTabs;
import org.springframework.stereotype.Component;

@Component
public class PointSettingPlugin extends AutoRegisterPlugin implements
		IOnSettingInputShow {

	
	public void register() {
		JspPageTabs.addTab("setting",3, "积分设置");
	}

	
	public String getSettingGroupName() {
	
		return "point";
	}

    @Override
    public int getOrder() {
        return 3;
    }

    @Override
    public String getTabName() {
        return "积分设置";
    }


    public String onShow() {
	
		return "setting";
	}

	
	public String getAuthor() {
	
		return "andy";
	}

	
	public String getId() {
	
		return "point_setting";
	}

	
	public String getName() {
	
		return "积分设置";
	}

	
	public String getType() {
	
		return "setting";
	}

	
	public String getVersion() {
	
		return "1.0";
	}

	
	public void perform(Object... params) {
		
	}

}
