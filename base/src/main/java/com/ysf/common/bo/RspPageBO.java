package com.ysf.common.bo;

import java.util.List;

/** <br>
 * 标题: 分页返回参数<br>
 * */
public class RspPageBO<T> extends RspInfoBO {
	private static final long serialVersionUID = -7485827693286591127L;

	/** 数据 */
	private List<T>           rows;

	/** 记录总数 */
	private int               recordsTotal;

	/** 总页数 */
	private int               total;

	/** 当前页 */
	private int               pageNo;

	/** 返回 数据
	 * 
	 * @return 数据 */
	public List<T> getRows() {
		return rows;
	}

	/** 设置 数据
	 * 
	 * @param data
	 *        数据 */
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	/** 返回 记录总数
	 * 
	 * @return 记录总数 */
	public int getRecordsTotal() {
		return recordsTotal;
	}

	/** 设置 记录总数
	 * 
	 * @param recordsTotal
	 *        记录总数 */
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	/** 返回 总页数
	 * 
	 * @return 总页数 */
	public int getTotal() {
		return total;
	}

	/** 设置 总页数
	 * 
	 * @param total
	 *        总页数 */
	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
}
