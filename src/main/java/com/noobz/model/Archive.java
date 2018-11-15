package com.noobz.model;

import java.io.Serializable;
import java.util.List;

import com.noobz.domain.Content;

public class Archive implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String date;
	
	private String count;
	
	private List<Content> articles;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public List<Content> getArticles() {
		return articles;
	}

	public void setArticles(List<Content> articles) {
		this.articles = articles;
	}
	
	
	
}
