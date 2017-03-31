/**
 * 
 */
package org.wcs.lemursportal.helper.pagination;

import java.util.List;

/**
 * @author mikajy.hery
 *
 */
public class PaginationResponse<E> {
	
	private int pageNum;
	private int pageSize;
	private long totalRows;
	private List<E> results;
	
	public PaginationResponse(List<E> results, int pageNum, int pageSize, long totalRows) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.results = results;
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
	public List<E> getResults() {
		return results;
	}
	public void setResults(List<E> results) {
		this.results = results;
	}

	public long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}
	
}
