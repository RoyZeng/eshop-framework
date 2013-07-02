/*    */ package com.elliot.app.shop.component.widget.nav;
/*    */ 
/*    */

import com.elliot.framework.context.webcontext.ThreadContextHolder;
import com.elliot.framework.utils.StringUtil;
import com.elliot.shop.manager.IGoodsCatManager;

import com.enation.eop.sdk.widget.AbstractWidget;

import com.enation.javashop.core.model.Cat;
import com.enation.javashop.core.utils.UrlUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
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

/*    */
/*    */ @Component("search_nav")
/*    */ @Scope("prototype")
/*    */ public class SearchNavWidget extends AbstractWidget
/*    */ {
/*    */   private IGoodsCatManager goodsCatManager;
/*    */ 
/*    */   protected void config(Map<String, String> params)
/*    */   {
/*    */   }
/*    */ 
/*    */   protected void display(Map<String, String> params)
/*    */   {
/* 34 */     HttpServletRequest request = ThreadContextHolder.getHttpRequest();
/* 35 */     String url = request.getServletPath();
/* 36 */     Cat cat = null;
/* 37 */     String catidstr = UrlUtils.getParamStringValue(url, "cat");
/* 38 */     if ((!StringUtil.isEmpty(catidstr)) && (!"0".equals(catidstr))) {
/* 39 */       Integer catid = Integer.valueOf(catidstr);
/* 40 */       cat = this.goodsCatManager.getById(catid.intValue());
/* 41 */       putData("cat", cat);
/*    */     }
/*    */   }
/*    */ 
/*    */   public IGoodsCatManager getGoodsCatManager() {
/* 46 */     return this.goodsCatManager;
/*    */   }
/*    */ 
/*    */   public void setGoodsCatManager(IGoodsCatManager goodsCatManager) {
/* 50 */     this.goodsCatManager = goodsCatManager;
/*    */   }
/*    */ }

/* Location:           F:\soft\Tomcat6.0\webapps\javamall\WEB-INF\lib\component-shop-core.jar
 * Qualified Name:     com.enation.app.shop.component.widget.nav.SearchNavWidget
 * JD-Core Version:    0.6.2
 */