package com.noobz.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noobz.dao.ContentMapper;
import com.noobz.domain.Content;
import com.noobz.domain.ContentExample;
import com.noobz.domain.ContentExample.Criteria;
import com.noobz.model.Archive;
import com.noobz.service.SiteService;
import com.noobz.util.DateKit;

@Service
public class SiteServiceImpl implements SiteService{

	@Resource
	private ContentMapper contentMapper;
	
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
				end = DateKit.getUnixTimeByDate(DateKit.dateAdd(DateKit.INTERVAL_MONTH, sd, 1)) - 1;
				criteria.andCreatedGreaterThan(start);
				criteria.andCreatedLessThan(end);
				List<Content> contents = contentMapper.selectByExample(example);
				archive.setArticles(contents);
			}
		}
		return archives;
	}
}
