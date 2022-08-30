package com.app.model;

public class PagingSearchFilterOrder {
	
	private String keyword ;
	private Integer provinceId;
	private Integer approvedStep;
	private Long userId;
	 
	 
	private int pageSize = 5;
	private int page  ;

	public PagingSearchFilterOrder() {
		 
	}
	 
	 
	 
	public PagingSearchFilterOrder(String keyword, Integer provinceId, Integer approvedStep, int pageSize, int page) {
		super();
		this.keyword = keyword;
		this.provinceId = provinceId;
		this.approvedStep = approvedStep;
		this.pageSize = pageSize;
		this.page = page;
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



	public Integer getProvinceId() {
		return provinceId;
	}



	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}



	public Integer getApprovedStep() {
		return approvedStep;
	}



	public void setApprovedStep(Integer approvedStep) {
		this.approvedStep = approvedStep;
	}



	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}
 
	
	
	
}
