package com.tz.bms.book.service;

import java.util.List;

import com.tz.bms.entity.Book;
import com.tz.bms.entity.Category;
import com.tz.bms.entity.Pageing;

/**
 * 本来用来演示 商品书的业务层接口
 *@author 杨倩Yoki
 *@2016-12-22 @下午8:30:54
 */
public interface IBookService {
	
	/**
	 * 根据条件查询,并进行分页
	 * @param row
	 * @param size
	 * @param cate
	 * @return
	 */
	public Pageing queryBookByCondition(int now,int size,String cate);

	/**
	 * 根据商品标识,查询商品信息
	 * @param id
	 * @return
	 */
	public Book queryBookById(long id);
	
	public Category querytById(long id);
	
	public List<Category> selectAllCategory();
	
}
