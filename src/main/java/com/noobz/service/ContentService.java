package com.noobz.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.noobz.domain.Content;
import com.noobz.domain.ContentExample;

public interface ContentService {
	
	List<Content> findAllContents();

	Content getContent(String cid);

	PageInfo<Content> getContent(Integer p, Integer limit);
	
	PageInfo<Content> getContentWithCondition(ContentExample example, Integer p, Integer limit);
	
	String updateContent(Content content);
}
