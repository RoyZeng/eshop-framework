package com.elliot.app.shop.component.goodscore.widget.goods.goodslist;

import com.elliot.framework.context.webcontext.ThreadContextHolder;
import com.elliot.framework.utils.StringUtil;

import com.enation.eop.sdk.widget.AbstractWidget;

import com.elliot.shop.manager.IGoodsCatManager;
import com.enation.javashop.core.model.Cat;
import com.enation.javashop.core.model.mapper.GoodsListMapper;
import com.enation.javashop.core.utils.UrlUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("goodslist2")
@Scope("prototype")
public class GoodsListWidget2 extends AbstractWidget {
    private IGoodsCatManager goodsCatManager;

    protected void config(Map<String, String> params) {
    }

    protected void display(Map<String, String> params) {
        String catid = (String) params.get("catid");
        String tagid = (String) params.get("tagid");
        String goodsnum = (String) params.get("goodsnum");

        if ((catid == null) || (catid.equals(""))) {
            String uri = ThreadContextHolder.getHttpRequest().getServletPath();
            catid = UrlUtils.getParamStringValue(uri, "cat");
        }

        if ((catid == null) || (catid.equals("")) || (catid.equals("0"))) {
            Map goodsMap = (Map) ThreadContextHolder.getHttpRequest().getAttribute("goods");
            if ((goodsMap != null) && (goodsMap.get("cat_id") != null)) {
                catid = goodsMap.get("cat_id").toString();
            }

        }

        List goodsList = listGoods(catid, tagid, goodsnum);
        putData("goodsList", goodsList);
    }

    private List listGoods(String catid, String tagid, String goodsnum) {
        int num = 10;
        if (!StringUtil.isEmpty(goodsnum)) {
            num = Integer.valueOf(goodsnum).intValue();
        }

        StringBuffer sql = new StringBuffer();
        sql.append("select g.* from " + getTableName("tag_rel") + " r LEFT JOIN " + getTableName("goods") + " g ON g.goods_id=r.rel_id where g.disabled=0 and g.market_enable=1");

        if (!StringUtil.isEmpty(catid)) {
            Cat cat = this.goodsCatManager.getById(Integer.valueOf(catid).intValue());
            String cat_path = cat.getCat_path();
            if (cat_path != null) {
                sql.append(" and  g.cat_id in(");
                sql.append("select c.cat_id from " + getTableName("goods_cat") + " ");
                sql.append(" c where c.cat_path like '" + cat_path + "%')");
            }
        }

        if (!StringUtil.isEmpty(tagid)) {
            sql.append(" AND r.tag_id=" + tagid + "");
        }

        sql.append(" order by r.ordernum desc");

        List list = this.daoSupport.queryForList(sql.toString(), 1, num, new GoodsListMapper());

        return list;
    }

    public IGoodsCatManager getGoodsCatManager() {
        return this.goodsCatManager;
    }

    public void setGoodsCatManager(IGoodsCatManager goodsCatManager) {
        this.goodsCatManager = goodsCatManager;
    }
}
