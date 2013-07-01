/*    */ package com.elliot.framework.resources;
/*    */ 
/*    */

import com.elliot.context.EopContext;
import com.elliot.context.ParamSetting;
import com.elliot.context.model.EopSite;


import java.util.HashMap;
import java.util.Map;

/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class ResourceStateManager
/*    */ {
/*    */   private static int DISPLOY_STATE;
/* 31 */   private static Map<String, String> disployStateMap = new HashMap();
/*    */ 
/*    */   public static boolean getHaveNewDisploy()
/*    */   {
/* 39 */     if ("2".equals(ParamSetting.RUNMODE)) {
/* 40 */       EopSite site = EopContext.getContext().getCurrentSite();
/* 41 */       return "1".equals(disployStateMap.get(site.getUserid() + "_" + site.getId()));
/*    */     }
/* 43 */     return DISPLOY_STATE == 1;
/*    */   }
/*    */ 
/*    */   public static void setDisplayState(int state)
/*    */   {
/* 54 */     if ("2".equals(ParamSetting.RUNMODE)) {
/* 55 */       EopSite site = EopContext.getContext().getCurrentSite();
/* 56 */       disployStateMap.put(site.getUserid() + "_" + site.getId(), "" + state);
/*    */     } else {
/* 58 */       DISPLOY_STATE = state;
/*    */     }
/*    */   }
/*    */ }

/* Location:           F:\soft\Tomcat6.0\webapps\javamall\WEB-INF\lib\eop.jar
 * Qualified Name:     com.enation.framework.resource.ResourceStateManager
 * JD-Core Version:    0.6.2
 */