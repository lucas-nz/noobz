package com.noobz.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.noobz.constant.Types;
import com.noobz.constant.WebConst;
import com.noobz.domain.Content;
import com.noobz.domain.Meta;
import com.noobz.domain.User;
import com.noobz.model.Archive;
import com.noobz.service.ContentService;
import com.noobz.service.MetaService;
import com.noobz.service.SiteService;
import com.noobz.service.UserService;

@Controller
public class IndexController extends BaseController {

	@Resource
	private UserService userService;

	@Resource
	private ContentService contentService;

	@Resource
	private SiteService siteService;

	@Resource
	private MetaService metaService;

	/**
	 * @return: String
	 * @Comment: 首页
	 */
	@GetMapping("/")
	public String index(HttpServletRequest request,
			@RequestParam(value = "limit", defaultValue = "6") int limit) {
		return this.index(request, 1, limit);
	}

	@GetMapping(value = "page/{p}")
	public String index(HttpServletRequest request, @PathVariable int p,
			@RequestParam(value = "limit", defaultValue = "6") int limit) {
		p = p < 0 || p > WebConst.MAX_PAGE ? 1 : p;
		PageInfo<Content> articles = contentService.getContent(p, limit);
		request.setAttribute("articles", articles);
		return render("index");
	}

	/**
	 * 归档页
	 * 
	 * @param: @param request
	 * @return: String
	 */
	@GetMapping(value = "archives")
	public String archives(HttpServletRequest request) {
		List<Archive> archives = siteService.getArchives();
		request.setAttribute("archives", archives);
		return this.render("archives");
	}

	/**
	 * 友链页
	 * 
	 * @param: @param request
	 * @return: String
	 */
	@GetMapping(value = "links")
	public String links(HttpServletRequest request) {
		List<Meta> links = metaService.getMetasByType(Types.LINK.getType());
		request.setAttribute("links", links);
		return this.render("links");
	}

	/**
	 * 文章页
	 * 
	 * @param: @param request
	 * @param: @param cid 文章主键
	 * @return: String
	 */
	@GetMapping(value = { "article/{cid}", "article/{cid}.html" })
	public String getArticle(HttpServletRequest request,
			@PathVariable String cid) {
		Content content = contentService.getContent(cid);
		if (null == content || "draft".equals(content.getStatus())) {
			return this.render_404();
		}
		request.setAttribute("article", content);
		request.setAttribute("is_post", true);
		return this.render("post");
	}

	@GetMapping("/search/{keyword}")
	public String search(HttpServletRequest request,
			@PathVariable String keyword,
			@RequestParam(defaultValue = "15") String limit) {
		return "/admin/login";
	}

	@ResponseBody
	@GetMapping("/user")
	public List<User> findAllUsers() {
		return userService.findAllUsers();
	}

}
