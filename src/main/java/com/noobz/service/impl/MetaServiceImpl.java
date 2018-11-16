package com.noobz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.noobz.dao.MetaMapper;
import com.noobz.domain.Meta;
import com.noobz.domain.MetaExample;
import com.noobz.service.MetaService;

@Service
public class MetaServiceImpl implements MetaService{
	
	@Resource
	private MetaMapper metaMapper;
	
	@Override
	public List<Meta> getMetasByType(String type) {
		if (StringUtils.isNotBlank(type)) {
			MetaExample example = new MetaExample();
			example.setOrderByClause("sort asc, mid desc");
			example.createCriteria().andTypeEqualTo(type);
			List<Meta> list = metaMapper.selectByExample(example);
			return list;
		}
		return null;
	}
}
