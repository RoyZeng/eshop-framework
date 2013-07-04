package com.elliot.framework.utils;

import com.elliot.context.EsfContext;
import com.elliot.context.ParamSetting;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: andy
 * Date: 13-6-30
 *
 * @since:
 */
public class UploadUtil {
    /**
     * 转换图片的名称
     * @param filePath  如：http://static.eop.com/user/1/1/attachment/goods/2001010101030.jpg
     * @param shortName
     * @return
     */
    public static  String getThumbPath(String filePath, String shortName) {
        String pattern = "(.*)([\\.])(.*)";
        String thumbPath = "$1" + shortName + "$2$3";

        Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
        Matcher m = p.matcher(filePath);
        if (m.find()) {
            String s = m.replaceAll(thumbPath);

            return s;
        }
        return null;
    }
    @Deprecated
    public static String replacePath(String path){

        if(StringUtil.isEmpty(path)) return path;
        //if(!path.startsWith(ParamSetting.FILE_STORE_PREFIX)) return path;

        return     path.replaceAll(ParamSetting.FILE_STORE_PREFIX, ParamSetting.IMG_SERVER_DOMAIN+ EsfContext.getContext().getContextPath() );
    }
}
