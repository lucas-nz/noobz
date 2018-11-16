package com.noobz.service;

import java.util.List;

import com.noobz.domain.Comment;
import com.noobz.domain.Content;
import com.noobz.model.Archive;
import com.noobz.model.Statistics;

public interface SiteService {

	List<Archive> getArchives();
	
	List<Content> getRecentContents();
	
	List<Comment> getRecenComments();
	
	Statistics getStatistics();
}
