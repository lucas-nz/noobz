package com.noobz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.noobz.constant.Types;
import com.noobz.constant.WebConst;
import com.noobz.dao.ContentMapper;
import com.noobz.domain.Content;
import com.noobz.domain.ContentExample;
import com.noobz.domain.ContentExample.Criteria;
import com.noobz.exception.TipException;
import com.noobz.service.ContentService;
import com.noobz.util.DateKit;
import com.noobz.util.Tools;
import com.vdurmont.emoji.EmojiParser;

@Service
public class ContentServiceImpl implements ContentService{

	@Resource
	private ContentMapper contentMapper;
	
	@Override
	public List<Content> findAllContents() {
		return contentMapper.selectByExample(null);
	}
	
	@Override
	public Content getContent(String id) {
		if (StringUtils.isNotBlank(id)) {
			if (Tools.isNumber(id)) {
				return contentMapper.selectByPrimaryKey(Integer.valueOf(id));
			} else {
				ContentExample contentExample = new ContentExample();
				contentExample.createCriteria().andSlugEqualTo(id);
				List<Content> list = contentMapper.selectByExample(contentExample);
				if (list.size() != 1) {
					throw new TipException("query content by id and return is not one. ");
				}
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public PageInfo<Content> getContent(Integer p, Integer limit) {
		ContentExample example = new ContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo(Types.ARTICLE.getType());
		criteria.andStatusEqualTo(Types.PUBLISH.getType());
		example.setOrderByClause("created desc");
		PageHelper.startPage(p, limit);
		List<Content> list = contentMapper.selectByExampleWithBLOBs(example);
		PageInfo<Content> pageInfo = new PageInfo<>(list); 
		return pageInfo;
	}
	
	@Override
	public PageInfo<Content> getContentWithCondition(ContentExample example,
			Integer p, Integer limit) {
		PageHelper.startPage(p, limit);
		List<Content> list = contentMapper.selectByExample(example);
		PageInfo<Content> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	@Override
	@Transactional
	public String updateContent(Content content) {
		if (null == content) {
			return "文章对象为空";
		}
		if (StringUtils.isBlank(content.getTitle())) {
			return "文章标题不能为空.";
		}
        if (StringUtils.isBlank(content.getContent())) {
            return "文章内容不能为空";
        }
        int titleLength = content.getTitle().length();
        if (titleLength > WebConst.MAX_TITLE_COUNT) {
            return "文章标题过长";
        }
        int contentLength = content.getContent().length();
        if (contentLength > WebConst.MAX_TEXT_COUNT) {
            return "文章内容过长";
        }
        if (StringUtils.isBlank(content.getSlug())) {
            content.setSlug(null);
        }
        int time = DateKit.getCurrentUnixTime();
        content.setModified(time);
        Integer cid = content.getCid();
        content.setContent(EmojiParser.parseToAliases(content.getContent()));

        contentMapper.updateByPrimaryKeySelective(content);
//        relationshipService.deleteById(cid, null);
//        meta.saveMetas(cid, content.getTags(), Types.TAG.getType());
//        metasService.saveMetas(cid, content.getCategories(), Types.CATEGORY.getType());
        return WebConst.SUCCESS_RESULT;
	}
}
