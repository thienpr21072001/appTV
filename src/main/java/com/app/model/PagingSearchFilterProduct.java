package com.app.model;

public class PagingSearchFilterProduct{
	private String keyword ;
	private long cateId ;
	 
	private int pageSize = 5;
	private int page  ;

	public PagingSearchFilterProduct() {
		 
	}
	 
	public PagingSearchFilterProduct(String searchName, long cateId,   int pageSize, int pageNumber) {
		this.keyword = searchName;
		this.cateId = cateId;
		this.pageSize = pageSize;
		this.page = pageNumber;
	}

	 
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public long getCateId() {
		return cateId;
	}
	public void setCateId(long cateId) {
		this.cateId = cateId;
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
