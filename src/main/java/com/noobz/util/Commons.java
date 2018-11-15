package com.noobz.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.noobz.constant.WebConst;
import com.noobz.domain.Content;
import com.vdurmont.emoji.EmojiParser;

@Component
public class Commons {

	public static final String THEME = "themes/default";
	
	public static String permalink(Content content) {
		Integer cid = content.getCid();
		String slug = content.getSlug();
		return permalink(cid, slug);
	}
	
	
	public static String permalink(Integer cid, String slug) {
		return site_url("/article/" + (StringUtils.isNotBlank(slug) ? slug : cid.toString()));
	}


	public static String site_url() {
		return site_url("");
	}
	
	public static String site_url(String sub) {
		return site_option("site_url") + sub;
	}

	public static String site_title() {
		return site_option("site_title");
	}

	/**
	 * 显示文章缩略图
	 */
	public static String show_thumb(Content content) {
		int cid = content.getCid();
		int size = cid % 20;
		size = size == 0 ? 1 : size;
		return "/user/img/rand/" + size + ".jpg";
	}
	
	/**
	 * 网站配置项
	 * @param: @param key
	 * @param: @return      
	 * @return: String
	 */
	public static String site_option(String key) {
		return site_option(key, "");
	}

	/**
	 *  网站配置项
	 * @param: @param key
	 * @param: @param defValue 默认值
	 * @param: @return      
	 * @return: String
	 */
	public static String site_option(String key, String defValue) {
		if (StringUtils.isBlank(key)) {
			return "";
		}
		String str = WebConst.initConfig.get(key);
		if (StringUtils.isNotEmpty(str)) {
			return str;
		}else {
			return defValue;
		}
	}

	/**
	 * 显示分类
	 * @param: @param categories
	 * @param: @throws UnsupportedEncodingException      
	 * @return: String
	 */
	public static String show_categories(String categories) throws UnsupportedEncodingException {
		if (StringUtils.isNotBlank(categories)) {
			String[] arr = categories.split(",");
			StringBuffer abuf = new StringBuffer();
			for (String c : arr) {
				abuf.append("<a href=\"/category/" + URLEncoder.encode(c, "UTF-8") + "\">" + c + "</a>");
			}
			return abuf.toString();
		}
		return show_categories("默认分类");
	}
	
	 /**
     * 显示标签
     *
     * @param tags
     * @return
     */
    public static String show_tags(String tags) throws UnsupportedEncodingException {
        if (StringUtils.isNotBlank(tags)) {
            String[] arr = tags.split(",");
            StringBuffer sbuf = new StringBuffer();
            for (String c : arr) {
                sbuf.append("<a href=\"/tag/" + URLEncoder.encode(c, "UTF-8") + "\">" + c + "</a>");
            }
            return sbuf.toString();
        }
        return "";
    }
	

    /**
     * 截取文章摘要
     *
     * @param value 文章内容
     * @param len   要截取文字的个数
     * @return
     */
    public static String intro(String value, int len) {
        int pos = value.indexOf("<!--more-->");
        if (pos != -1) {
            String html = value.substring(0, pos);
            return TaleUtils.htmlToText(TaleUtils.mdToHtml(html));
        } else {
            String text = TaleUtils.htmlToText(TaleUtils.mdToHtml(value));
            if (text.length() > len) {
                return text.substring(0, len);
            }
            return text;
        }
    }

    /**
     * 显示文章内容，转换markdown为html
     *
     * @param value
     * @return
     */
    public static String article(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.replace("<!--more-->", "\r\n");
            value =  TaleUtils.mdToHtml(value);
            return value;
        }
        return "";
    }
	
	
    /**
     * 格式化unix时间戳为日期
     *
     * @param unixTime
     * @return
     */
    public static String fmtdate(Integer unixTime) {
        return fmtdate(unixTime, "yyyy-MM-dd");
    }

    /**
     * 格式化unix时间戳为日期
     *
     * @param unixTime
     * @param patten
     * @return
     */
    public static String fmtdate(Integer unixTime, String patten) {
        if (null != unixTime && StringUtils.isNotBlank(patten)) {
            return DateKit.formatDateByUnixTime(unixTime, patten);
        }
        return "";
    }	

    /**
     * An :grinning:awesome :smiley:string &#128516;with a few :wink:emojis!
     * <p>
     * 这种格式的字符转换为emoji表情
     *
     * @param value
     * @return
     */
    public static String emoji(String value) {
        return EmojiParser.parseToUnicode(value);
    }
}
