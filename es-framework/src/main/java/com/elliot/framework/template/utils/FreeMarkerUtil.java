package com.elliot.framework.template.utils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import com.elliot.framework.template.directive.DirectiveFactory;
import com.elliot.framework.context.webcontext.ThreadContextHolder;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;

import freemarker.cache.MruCacheStorage;
import freemarker.template.*;

/**
 * freemarker工具
 * 
 * @author andy 2010-2-8下午04:23:18
 */
public class FreeMarkerUtil {
	private FreeMarkerUtil() {
	}

	private static Configuration cfg;

	/**
	 * 获取servlet上下文件的Configuration
	 * 
	 * @param pageFolder
	 * @return
	 */
	public static Configuration getServletCfg(String pageFolder) {

		Configuration cfg = new Configuration();
		cfg.setServletContextForTemplateLoading(ThreadContextHolder
				.getHttpRequest().getSession().getServletContext(), pageFolder);
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		return cfg;
	}

    public static Configuration getCfg() {
        Configuration cfg = new Configuration();
        cfg.setTemplateUpdateDelay(6000);
        cfg.setCacheStorage(new MruCacheStorage(20, 250));

        Map directiveMap = DirectiveFactory.getCommonDirective();
        Iterator keyIter = directiveMap.keySet().iterator();

        while (keyIter.hasNext()) {
            String key = (String) keyIter.next();

            cfg.setSharedVariable(key, (TemplateModel) directiveMap.get(key));
        }

        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.CHINA);
        cfg.setEncoding(Locale.CHINA, "UTF-8");

        return cfg;
    }

    public static Configuration getFolderCfg(String pageFolder)
			throws IOException {
		cfg =getCfg();
		cfg.setDirectoryForTemplateLoading(new File(pageFolder));
		
		return cfg;

	}

	public static void test() {
		Configuration cfg;
		try {
			cfg = FreeMarkerUtil
					.getFolderCfg("D:/workspace/eopnew/eop/WebContent/WEB-INF/classes/com/enation/shop/core/widget/goodscat");
			Template temp = cfg.getTemplate("GoodsCat.html");
			ByteOutputStream stream = new ByteOutputStream();

			Writer out = new OutputStreamWriter(stream, "UTF-8");
			temp.process(new HashMap(), out);

			out.flush();
			String html = stream.toString();
			System.out.println("+++++++++" + html);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException,
			TemplateException {
		Configuration cfg = FreeMarkerUtil
				.getFolderCfg("D:/workspace/eopnew/eop/WebContent/WEB-INF/classes/com/enation/javashop/core/widget/goodscat");
		Template temp = cfg.getTemplate("GoodsCat.html");
		ByteOutputStream stream = new ByteOutputStream();

		Writer out = new OutputStreamWriter(stream, "UTF-8");
		temp.process(new HashMap(), out);

		out.flush();
		String html = stream.toString();
		System.out.println(html);
	}

}
