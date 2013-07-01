/*    */ package com.elliot.framework.template.directive;
/*    */ 
/*    */

import com.elliot.framework.resources.Resource;

import com.elliot.framework.resources.impl.ResourceBuilder;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

import java.io.IOException;
import java.util.Map;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class CssDirectiveModel extends AbstractResourceDirectiveModel
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public void execute(Environment env, Map params, TemplateModel[] arg2, TemplateDirectiveBody arg3)
/*    */     throws TemplateException, IOException
/*    */   {
/* 20 */     Resource resource = createResource(params);
/* 21 */     resource.setType("css");
/* 22 */     ResourceBuilder.putCss(resource);
/*    */   }
/*    */ }

/* Location:           F:\soft\Tomcat6.0\webapps\javamall\WEB-INF\lib\eop.jar
 * Qualified Name:     com.enation.framework.directive.CssDirectiveModel
 * JD-Core Version:    0.6.2
 */