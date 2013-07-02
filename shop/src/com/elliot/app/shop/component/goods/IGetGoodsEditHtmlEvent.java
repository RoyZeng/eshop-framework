package com.elliot.app.shop.component.goods;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract interface IGetGoodsEditHtmlEvent
{
  public abstract String getEditHtml(Map paramMap, HttpServletRequest paramHttpServletRequest);
}

/* Location:           F:\soft\Tomcat6.0\webapps\javamall\WEB-INF\lib\shop.jar
 * Qualified Name:     com.enation.app.shop.core.plugin.goods.IGetGoodsEditHtmlEvent
 * JD-Core Version:    0.6.2
 */