package com.noobz.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.noobz.constant.WebConst;
import com.noobz.domain.Content;

@Component
public class Common {

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
}
