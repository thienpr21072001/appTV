/**
 * 
 */
package com.app.model;

 
public class PagingSearchFilterUser {
	private String keyword ;
	private int pageSize = 5;
	private int page  ;
	
	
	
	public PagingSearchFilterUser() {
		 
	}
	 
	public PagingSearchFilterUser(String searchName, int pageSize, int pageNumber) {
		 
		this.keyword = searchName;
		this.pageSize = pageSize;
		this.page = pageNumber;
	}

	 
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	 
	public int getPage() {
		if(page == 0 ) {
			return 0;
		}
		return page - 1;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
