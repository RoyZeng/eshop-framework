/*     */ package com.elliot.app.shop.component.goodscore.plugin.params;
/*     */ 
/*     */

import com.elliot.app.shop.component.goods.IGoodsTabShowEvent;
import com.elliot.framework.database.IDaoSupport;
import com.elliot.framework.utils.StringUtil;

import com.enation.eop.processor.core.freemarker.FreeMarkerPaser;

import com.enation.javashop.core.model.Cat;
import com.enation.javashop.core.model.GoodsType;
import com.enation.javashop.core.model.support.ParamGroup;
import com.enation.javashop.core.plugin.goods.AbstractGoodsPlugin;
import com.elliot.shop.manager.IGoodsCatManager;
import com.enation.javashop.core.service.IGoodsTypeManager;
import com.enation.javashop.plugin.standard.type.GoodsTypeUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
/*     */ @Component
/*     */ public class GoodsParamsAdminPlugin extends AbstractGoodsPlugin
/*     */   implements IGoodsTabShowEvent
/*     */ {
/*     */   private IDaoSupport<GoodsType> baseDaoSupport;
/*     */   private IGoodsCatManager goodsCatManager;
/*     */   private IGoodsTypeManager goodsTypeManager;
/*     */ 
/*     */   public String getAddHtml(HttpServletRequest request)
/*     */   {
/*  46 */     int catid = StringUtil.toInt(request.getParameter("catid"), true);
/*  47 */     Cat cat = this.goodsCatManager.getById(catid);
/*  48 */     int typeid = cat.getType_id().intValue();
/*  49 */     ParamGroup[] paramAr = this.goodsTypeManager.getParamArByTypeId(typeid);
/*     */ 
/*  51 */     FreeMarkerPaser freeMarkerPaser = FreeMarkerPaser.getInstance();
/*     */ 
/*  53 */     freeMarkerPaser.setPageName("params_input");
/*  54 */     freeMarkerPaser.putData("paramAr", paramAr);
/*  55 */     return freeMarkerPaser.proessPageContent();
/*     */   }
/*     */ 
/*     */   public String getEditHtml(Map goods, HttpServletRequest request)
/*     */   {
/*  67 */     String params = goods.get("params") == null ? "" : goods.get("params").toString();
/*  68 */     ParamGroup[] paramAr = GoodsTypeUtil.converFormString(params);
/*     */ 
/*  70 */     FreeMarkerPaser freeMarkerPaser = FreeMarkerPaser.getInstance();
/*  71 */     freeMarkerPaser.setPageName("params_input");
/*  72 */     freeMarkerPaser.putData("paramAr", paramAr);
/*  73 */     freeMarkerPaser.putData("is_edit", Boolean.valueOf(true));
/*  74 */     return freeMarkerPaser.proessPageContent();
/*     */   }
/*     */ 
/*     */   public void onBeforeGoodsAdd(Map goods, HttpServletRequest request)
/*     */   {
/*  87 */     String[] paramnums = request.getParameterValues("paramnums");
/*  88 */     String[] groupnames = request.getParameterValues("groupnames");
/*  89 */     String[] paramnames = request.getParameterValues("paramnames");
/*  90 */     String[] paramvalues = request.getParameterValues("paramvalues");
/*     */ 
/*  92 */     String params = this.goodsTypeManager.getParamString(paramnums, groupnames, paramnames, paramvalues);
/*     */ 
/*  94 */     goods.put("params", params);
/*     */   }
/*     */ 
/*     */   public void onBeforeGoodsEdit(Map goods, HttpServletRequest request)
/*     */   {
/* 104 */     String[] paramnums = request.getParameterValues("paramnums");
/* 105 */     String[] groupnames = request.getParameterValues("groupnames");
/* 106 */     String[] paramnames = request.getParameterValues("paramnames");
/* 107 */     String[] paramvalues = request.getParameterValues("paramvalues");
/*     */ 
/* 110 */     String params = this.goodsTypeManager.getParamString(paramnums, groupnames, paramnames, paramvalues);
/*     */ 
/* 112 */     goods.put("params", params);
/*     */   }
/*     */ 
/*     */   public void onAfterGoodsAdd(Map goods, HttpServletRequest request)
/*     */     throws RuntimeException
/*     */   {
/*     */   }
/*     */ 
/*     */   public void onAfterGoodsEdit(Map goods, HttpServletRequest request)
/*     */   {
/*     */   }
/*     */ 
/*     */   public String getTabName()
/*     */   {
/* 138 */     return "参数";
/*     */   }
/*     */ 
/*     */   public int getOrder()
/*     */   {
/* 144 */     return 9;
/*     */   }
/*     */ 
/*     */   public IDaoSupport<GoodsType> getBaseDaoSupport()
/*     */   {
/* 150 */     return this.baseDaoSupport;
/*     */   }
/*     */ 
/*     */   public void setBaseDaoSupport(IDaoSupport<GoodsType> baseDaoSupport)
/*     */   {
/* 157 */     this.baseDaoSupport = baseDaoSupport;
/*     */   }
/*     */ 
/*     */   public IGoodsCatManager getGoodsCatManager()
/*     */   {
/* 164 */     return this.goodsCatManager;
/*     */   }
/*     */ 
/*     */   public void setGoodsCatManager(IGoodsCatManager goodsCatManager)
/*     */   {
/* 171 */     this.goodsCatManager = goodsCatManager;
/*     */   }
/*     */ 
/*     */   public IGoodsTypeManager getGoodsTypeManager()
/*     */   {
/* 178 */     return this.goodsTypeManager;
/*     */   }
/*     */ 
/*     */   public void setGoodsTypeManager(IGoodsTypeManager goodsTypeManager)
/*     */   {
/* 185 */     this.goodsTypeManager = goodsTypeManager;
/*     */   }
/*     */ }

/* Location:           F:\soft\Tomcat6.0\webapps\javamall\WEB-INF\lib\component-shop-core.jar
 * Qualified Name:     com.enation.app.shop.component.goodscore.plugin.params.GoodsParamsAdminPlugin
 * JD-Core Version:    0.6.2
 */