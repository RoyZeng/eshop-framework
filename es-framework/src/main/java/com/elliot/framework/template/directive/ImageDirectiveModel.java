/*     */ package com.elliot.framework.template.directive;
/*     */ 
/*     */

import com.elliot.context.EsfContext;
import com.elliot.context.ParamSetting;
import com.elliot.context.model.EopSite;
import com.elliot.framework.resources.Resource;
import com.elliot.framework.utils.StringUtil;
import com.elliot.framework.utils.UploadUtil;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */

/*     */
/*     */ public class ImageDirectiveModel extends AbstractResourceDirectiveModel
/*     */   implements TemplateDirectiveModel
/*     */ {
/*     */   public void execute(Environment env, Map params, TemplateModel[] arg2, TemplateDirectiveBody arg3)
/*     */     throws TemplateException, IOException
/*     */   {
/*  32 */     Resource resource = createResource(params);
/*  33 */     resource.setType("image");
/*     */ 
/*  38 */     String src = params.get("src").toString();
/*  39 */     String postfix = getValue(params, "postfix");
/*  40 */     String imageurl = getImageUrl(src, postfix);
/*  41 */     StringBuffer html = new StringBuffer();
/*     */ 
/*  43 */     html.append("<img");
/*  44 */     html.append(" src=\"" + imageurl + "\"");
/*     */ 
/*  47 */     Set keySet = params.keySet();
/*  48 */     Iterator itor = keySet.iterator();
/*     */ 
/*  50 */     while (itor.hasNext()) {
/*  51 */       String name = (String)itor.next();
/*  52 */       if ((!"src".equals(name)) && 
/*  53 */         (!"postfix".equals(name))) {
/*  54 */         String value = getValue(params, name);
/*  55 */         if (!StringUtil.isEmpty(value)) {
/*  56 */           html.append(" " + name + "=\"" + value + "\"");
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  63 */     html.append(" />");
/*  64 */     env.getOut().write(html.toString());
/*     */   }
/*     */ 
/*     */   private String getImageUrl(String pic, String postfix)
/*     */   {
/*  69 */     if (StringUtil.isEmpty(pic))
/*  70 */       pic = ParamSetting.DEFAULT_IMG_URL;
/*  71 */     if (pic.toUpperCase().startsWith("HTTP"))
/*  72 */       return pic;
/*  73 */     if (pic.startsWith("fs:")) {
/*  74 */       pic = UploadUtil.replacePath(pic);
/*     */     }
/*     */     else {
/*  77 */       EsfContext ectx = EsfContext.getContext();
/*     */ 
/*  80 */       if (!pic.startsWith("/")) {
/*  81 */         pic = "/" + pic;
/*     */       }
/*     */ 
/*  91 */       if (("2".equals(ParamSetting.RESOURCEMODE)) || (ParamSetting.DEVELOPMENT_MODEL))
/*     */       {
/*  93 */         EopSite site = ectx.getCurrentSite();
/*     */ 
/*  95 */         pic = ectx.getResDomain() + "/themes/" + site.getThemepath() + pic;
/*     */       }
/*     */       else
/*     */       {
/*  99 */         pic = ectx.getResDomain() + pic;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 104 */     if (!StringUtil.isEmpty(postfix)) {
/* 105 */       return UploadUtil.getThumbPath(pic, postfix);
/*     */     }
/* 107 */     return pic;
/*     */   }
/*     */ }

/* Location:           F:\soft\Tomcat6.0\webapps\javamall\WEB-INF\lib\eop.jar
 * Qualified Name:     com.enation.framework.directive.ImageDirectiveModel
 * JD-Core Version:    0.6.2
 */