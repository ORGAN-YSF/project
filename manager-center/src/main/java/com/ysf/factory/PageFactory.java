package com.ysf.factory;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.plugins.Page;
import com.ysf.util.HttpUtil;

/**
 * 分页工厂
 * @author sunwenxing
 */
public class PageFactory<T> {
	public Page<T> defaultPage() {
		HttpServletRequest request = HttpUtil.getRequest();
		Integer limit = Integer.valueOf(request.getParameter("limit"));			//每页数量
		Integer offset = Integer.valueOf(request.getParameter("offset"));		//每页的偏移量
		String order = request.getParameter("order");							//排序方式
		String sort = request.getParameter("sort");								//排序字段
		
		if(StringUtils.isBlank(sort)) {
			Page<T> page = new Page<>(offset / limit + 1, limit);
			page.setOpenSort(false);
			return page;
		}else {
			Page<T> page = new Page<>(offset / limit + 1, limit, sort);
			if("asc".equals(order)) {
				page.setAsc(true);
			}else {
				page.setAsc(false);
			}
 			return page;
		}
	}
}
