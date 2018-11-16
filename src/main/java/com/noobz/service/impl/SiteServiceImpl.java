package com.noobz.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.noobz.constant.Types;
import com.noobz.constant.WebConst;
import com.noobz.dao.AttachMapper;
import com.noobz.dao.CommentMapper;
import com.noobz.dao.ContentMapper;
import com.noobz.dao.MetaMapper;
import com.noobz.domain.Comment;
import com.noobz.domain.CommentExample;
import com.noobz.domain.Content;
import com.noobz.domain.ContentExample;
import com.noobz.domain.MetaExample;
import com.noobz.domain.ContentExample.Criteria;
import com.noobz.model.Archive;
import com.noobz.model.Statistics;
import com.noobz.service.SiteService;
import com.noobz.util.DateKit;

@Service
public class SiteServiceImpl implements SiteService {

	@Resource
	private ContentMapper contentMapper;

	@Resource
	private CommentMapper commentMapper;

	@Resource
	private AttachMapper attachMapper;

	@Resource
	private MetaMapper metaMapper;

	@Override
	public List<Archive> getArchives() {
		List<Archive> archives = contentMapper.selectArchives();
		ContentExample example = null;
		int start, end;
		if (null != archives) {
			for (Archive archive : archives) {
				example = new ContentExample();
				Criteria criteria = example.createCriteria();
				example.setOrderByClause("created desc");
				String date = archive.getDate();
				Date sd = DateKit.dateFormat(date, "yyyy年MM月");
				start = DateKit.getUnixTimeByDate(sd);
				end = DateKit.getUnixTimeByDate(DateKit.dateAdd(
						DateKit.INTERVAL_MONTH, sd, 1)) - 1;
				criteria.andCreatedGreaterThan(start);
				criteria.andCreatedLessThan(end);
				List<Content> contents = contentMapper.selectByExample(example);
				archive.setArticles(contents);
			}
		}
		return archives;
	}

	@Override
	public List<Content> getRecentContents() {
		int limit = WebConst.RECENT_COUNT;
		if (limit <= 0 || limit > 10) {
			limit = 5;
		}
		ContentExample example = new ContentExample();
		example.setOrderByClause("created desc");
		PageHelper.startPage(1, limit);
		List<Content> list = contentMapper.selectByExampleWithBLOBs(example);
		return list;
	}

	@Override
	public List<Comment> getRecenComments() {
		int limit = WebConst.RECENT_COUNT;
		if (limit <= 0 || limit > 10) {
			limit = 5;
		}
		CommentExample example = new CommentExample();
		example.setOrderByClause("created desc");
		PageHelper.startPage(1, limit);
		List<Comment> list = commentMapper.selectByExampleWithBLOBs(example);
		return list;
	}

	@Override
	public Statistics getStatistics() {
		Statistics s = new Statistics();
		ContentExample contentExample = new ContentExample();
		contentExample.createCriteria().andTypeEqualTo(Types.ARTICLE.getType())
				.andStatusEqualTo(Types.PUBLISH.getType());
		long contentCount = contentMapper.countByExample(contentExample);
		long commentCount = commentMapper.countByExample(null);
		long attachCount = attachMapper.countByExample(null);
		MetaExample metaExample = new MetaExample();
		metaExample.createCriteria().andTypeEqualTo(Types.LINK.getType());
		long linkCount = metaMapper.countByExample(metaExample);
		s.setArticles(contentCount);
		s.setAttachs(attachCount);
		s.setComments(commentCount);
		s.setLinks(linkCount);
		return s;
	}
}
