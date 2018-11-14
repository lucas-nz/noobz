package com.noobz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noobz.dao.ContentMapper;
import com.noobz.domain.Content;
import com.noobz.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService{

	@Resource
	private ContentMapper contentMapper;
	
	@Override
	public List<Content> findAllContents() {
		return contentMapper.selectByExample(null);
	}

}
