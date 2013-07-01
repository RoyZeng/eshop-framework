/*    */ package com.elliot.framework.template.directive;
/*    */ 
/*    */

import com.elliot.context.ParamSetting;

import com.elliot.framework.resources.Resource;
import com.elliot.framework.utils.StringUtil;




import java.util.Map;

/*    */
/*    */
/*    */

/*    */
/*    */ public abstract class AbstractResourceDirectiveModel
/*    */ {
/*    */   protected Resource createResource(Map params)
/*    */   {
/* 18 */     String src = params.get("src").toString();
/* 19 */     String compress = getValue(params, "compress");
/* 20 */     String merge = getValue(params, "merge");
/*    */ 
/* 22 */     String iscommon = getValue(params, "iscommon");
/*    */ 
/* 24 */     if (StringUtil.isEmpty(merge)) merge = "true";
/* 25 */     if (StringUtil.isEmpty(compress)) compress = "true";
/*    */ 
/* 29 */     Resource resource = new Resource();
/* 30 */     resource.setSrc(src);
/* 31 */     resource.setMerge("true".equals(merge) ? 1 : 0);
/* 32 */     resource.setCompress("true".equals(compress) ? 1 : 0);
/*    */ 
/* 35 */     resource.setIscommon("true".equals(iscommon));
/*    */ 
/* 39 */     if (ParamSetting.DEVELOPMENT_MODEL) {
/* 40 */       resource.setMerge(0);
/* 41 */       resource.setIscommon(false);
/*    */     }
/*    */ 
/* 44 */     return resource;
/*    */   }
/*    */ 
/*    */   protected String getValue(Map params, String name)
/*    */   {
/* 49 */     Object value_obj = params.get(name);
/* 50 */     if (value_obj == null) {
/* 51 */       return null;
/*    */     }
/*    */ 
/* 54 */     return value_obj.toString();
/*    */   }
/*    */ }
