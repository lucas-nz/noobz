package com.noobz.service;

import java.util.List;

import com.noobz.domain.Meta;

public interface MetaService {

	List<Meta> getMetasByType(String type);
}
