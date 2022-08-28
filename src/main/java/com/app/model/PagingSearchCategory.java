package com.app.model;

public class PagingSearchCategory {
	
	private  String searchName;
	private  int pageSize;
	private  int pageNumber;
	
	
	public PagingSearchCategory() {
		this.pageSize = 2;
		this.pageNumber = 0;
	}
	public PagingSearchCategory(String searchName, int pageSize, int pageNumber) {
		 
		this.searchName = searchName;
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
	}
	public String getSearchName() {
		return searchName;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	@Override
	public String toString() {
		return "PagingSearchCategory [searchName=" + searchName + ", pageSize=" + pageSize + ", pageNumber="
				+ pageNumber + "]";
	}
	
	
	
}
