/**
 * 
 */
package org.wcs.lemursportal.helper.pagination;

/**
 * @author mikajy.hery
 *
 */
public class PaginationRequest<E> {
	
	private int pageNum;
	private int pageSize;
	private E criteria;
	
	public PaginationRequest(int pageNum, int pageSize) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public PaginationRequest(int pageNum, int pageSize, E criteria) {
		this(pageNum, pageSize);
		this.criteria = criteria;
	}
	
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public E getCriteria() {
		return criteria;
	}

	public void setCriteria(E criteria) {
		this.criteria = criteria;
	}
	
}
