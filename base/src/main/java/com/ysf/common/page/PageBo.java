package com.ysf.common.page;

import java.util.List;
/**
 * 分页BO
 */
public interface PageBo<T> {
    
    public int getOffset();

    public int getLimit();
      
    /**
     * 
     * 方法描述： 当前页
     * @return
     */
    public int getCurrentPage();
    
    /**
     * 
     * 方法描述： 每页显示记录数
     * @return
     */
    public int getPageSize();
    
    /**
     * 
     * 方法描述：  总页数
     * @return
     */
    public int getTotalPage();
    
    /**
     * 
     * 方法描述： 总记录数
     * @return
     */
    public int getTotalRecord();
    
    /**
     * 
     * 方法描述： 记录集
     * @return
     */
    public List<T> getRecords();
    
    /**
     * 
     * 方法描述： 当前页
     * @param currentPage
     */
    public void setCurrentPage(int currentPage);
    
    /**
     * 
     * 方法描述： 每页显示记录数
     * @param pageSize
     */
    public void setPageSize(int pageSize);
    
    /**
     * 
     * 方法描述： 总记录数
     * @param totalRecord
     */
    public void setTotalRecord(int totalRecord);
    
    /**
     * 
     * 方法描述： 记录集
     * @param records
     */
    public void setRecords(List<T> records);
    
}

