package cn.framework.myandroidlibrary.http;

import java.io.Serializable;

/**
 * 分页对象
 * @author hsm
 *
 */
public class PageInfo implements Serializable{

	private int curPage=1;
	private int pageSize=10;
	private int totalSize=0;
	private int totalPage=1;
	private boolean firstPage=true;//用于瀑布流情况
	private boolean nextPage=false;
	private Long lastID;

	public boolean hasNextPage(){
		if(nextPage){
			return true;
		}
		return (curPage<totalPage);
	}

	public int nextPageNo(){
		return curPage+1;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		if (curPage>1){
			firstPage=false;
		}else{
			firstPage=true;
		}
		this.curPage = curPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public boolean isNextPage() {
		return nextPage;
	}
	public boolean getNextPage() {
		return nextPage;
	}

	public void setNextPage(boolean nextPage) {
		this.nextPage = nextPage;
	}

	public Long getLastID() {
		return lastID;
	}

	public void setLastID(Long lastID) {
		this.lastID = lastID;
	}

	public boolean isFirstPage() {
		if (curPage>1){
			return false;
		}
		return firstPage;
	}

	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}
}
