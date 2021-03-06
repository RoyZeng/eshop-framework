package com.elliot.framework.component.context;

import com.elliot.context.EsfContext;
import com.elliot.context.ParamSetting;
import com.elliot.context.model.EopSite;


import java.util.HashMap;
import java.util.Map;

public class WidgetContext
{
    private static Map<String, Boolean> widgetState;
    private static Map<String, Map<String, Boolean>> saasWidgetState;

    public static void putWidgetState(String widgetId, Boolean state)
    {
        if ("2".equals(ParamSetting.RUNMODE)) {
            String key = getKey();
            Map stateMap = (Map)saasWidgetState.get(key);
            if (stateMap == null) {
                stateMap = new HashMap();
            }

            stateMap.put(widgetId, state);
            saasWidgetState.put(key, stateMap);
        }
        else {
            widgetState.put(widgetId, state);
        }
    }

    public static Boolean getWidgetState(String widgetId)
    {
        if ("2".equals(ParamSetting.RUNMODE)) {
            String key = getKey();
            Map stateMap = (Map)saasWidgetState.get(key);
            if (stateMap == null) {
                stateMap = new HashMap();
                saasWidgetState.put(key, stateMap);
            }

            Boolean state = (Boolean)stateMap.get(widgetId);
            if (state == null) return Boolean.valueOf(false);
            return state;
        }

        Boolean state = (Boolean)widgetState.get(widgetId);
        if (state == null) return Boolean.valueOf(true);
        return state;
    }

    private static String getKey(){
        EopSite site = EsfContext.getContext().getCurrentSite();
        int userid = site.getUserid().intValue();
        int siteid = site.getId().intValue();
        String key = userid + "_" + siteid;

        return key;
    }

    static
    {
        if ("2".equals(ParamSetting.RUNMODE)) {
            saasWidgetState = new HashMap();
        }
        else
            widgetState = new HashMap();
    }
}