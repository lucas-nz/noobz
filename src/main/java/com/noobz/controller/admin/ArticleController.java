package com.noobz.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.noobz.constant.Types;
import com.noobz.domain.Content;
import com.noobz.domain.ContentExample;
import com.noobz.domain.Meta;
import com.noobz.exception.TipException;
import com.noobz.service.ContentService;
import com.noobz.service.MetaService;

@RequestMapping("/admin/article")
@Controller
@Transactional(rollbackFor = TipException.class)
public class ArticleController {

	@Resource
	private ContentService contentService;

	@Resource
	private MetaService metaService;

	@GetMapping("")
	public String index(@RequestParam(defaultValue = "1") String page,
			@RequestParam(defaultValue = "15") String limit,
			HttpServletRequest request) {
		ContentExample example = new ContentExample();
		example.setOrderByClause("created desc");
		PageInfo<Content> articles = contentService.getContentWithCondition(
				example, Integer.valueOf(page), Integer.valueOf(limit));
		request.setAttribute("articles", articles);
		return "admin/article_list";
	}

	@GetMapping("/{cid}")
	public String editArticle(@PathVariable String cid,
			HttpServletRequest request) {
		Content content = contentService.getContent(cid);
		List<Meta> categories = metaService.getMetasByType(Types.CATEGORY.getType());
		request.setAttribute("contents", content);
		request.setAttribute("active", "article");
		request.setAttribute("categories", categories);
		return "admin/article_edit";
	}

}
