package com.noobz.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.noobz.domain.Content;

public interface ContentService {
	
	List<Content> findAllContents();

	Content getContent(String cid);

	PageInfo<Content> getContent(Integer p, Integer limit);
}
