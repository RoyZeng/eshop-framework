/*     */ package com.elliot.app.shop.component.goodscore.plugin.goodsimp;
/*     */ 
/*     */

import com.elliot.context.ParamSetting;
import com.elliot.framework.context.webcontext.ThreadContextHolder;
import com.elliot.framework.database.IDaoSupport;
import com.elliot.framework.plugin.AutoRegisterPlugin;
import com.elliot.framework.utils.FileUtil;
import com.elliot.framework.utils.StringUtil;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import javax.servlet.http.HttpServletRequest;

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
/*     */ public class GoodsDataCleanImportPlugin extends AutoRegisterPlugin
/*     */   implements IBeforeGoodsImportEvent
/*     */ {
/*     */   private IDaoSupport baseDaoSupport;
/*     */ 
/*     */   public void register()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void onBeforeImport(Document configDoc)
/*     */   {
/*  36 */     HttpServletRequest request = ThreadContextHolder.getHttpRequest();
/*     */ 
/*  38 */     String cleanall = request.getParameter("cleanall");
/*  39 */     String cleancat = request.getParameter("cleancat");
/*  40 */     String imptype = request.getParameter("imptype");
/*     */ 
/*  42 */     if (!StringUtil.isEmpty(cleanall))
/*     */     {
/*  45 */       String imgfolder = ParamSetting.IMG_SERVER_PATH + "/attachment/goods";
/*  46 */       FileUtil.delete(imgfolder);
/*     */ 
/*  48 */       String ckeditor = ParamSetting.IMG_SERVER_PATH + "/attachment/ckeditor";
/*  49 */       FileUtil.delete(imgfolder);
/*     */ 
/*  51 */       this.baseDaoSupport.execute("truncate table goods", new Object[0]);
/*  52 */       this.baseDaoSupport.execute("truncate table product", new Object[0]);
/*  53 */       this.baseDaoSupport.execute("truncate table goods_spec", new Object[0]);
/*  54 */       this.baseDaoSupport.execute("truncate table cart", new Object[0]);
/*  55 */       this.baseDaoSupport.execute("truncate table order", new Object[0]);
/*  56 */       this.baseDaoSupport.execute("truncate table order_items", new Object[0]);
/*     */     }
/*     */ 
/*  59 */     if (("2".equals(imptype)) && (!StringUtil.isEmpty(cleancat))) {
/*  60 */       int catid = Integer.valueOf(request.getParameter("catid")).intValue();
/*  61 */       this.baseDaoSupport.execute("delete from product where goods_id in (select goods_id from goods where cat_id=?)", new Object[] { Integer.valueOf(catid) });
/*  62 */       this.baseDaoSupport.execute("delete from goods where cat_id=?", new Object[] { Integer.valueOf(catid) });
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getAuthor()
/*     */   {
/*  71 */     return "kingapex";
/*     */   }
/*     */ 
/*     */   public String getId()
/*     */   {
/*  77 */     return "goodsImageDeletePlugin";
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  83 */     return "商品导入前删除商品图片插件 ";
/*     */   }
/*     */ 
/*     */   public String getType()
/*     */   {
/*  89 */     return "goodsimp";
/*     */   }
/*     */ 
/*     */   public String getVersion()
/*     */   {
/*  95 */     return "1.0";
/*     */   }
/*     */ 
/*     */   public void perform(Object[] params)
/*     */   {
/*     */   }
/*     */ 
/*     */   public IDaoSupport getBaseDaoSupport()
/*     */   {
/* 106 */     return this.baseDaoSupport;
/*     */   }
/*     */ 
/*     */   public void setBaseDaoSupport(IDaoSupport baseDaoSupport)
/*     */   {
/* 111 */     this.baseDaoSupport = baseDaoSupport;
/*     */   }
/*     */ }

/* Location:           F:\soft\Tomcat6.0\webapps\javamall\WEB-INF\lib\component-shop-core.jar
 * Qualified Name:     com.enation.app.shop.component.goodscore.plugin.goodsimp.GoodsDataCleanImportPlugin
 * JD-Core Version:    0.6.2
 */