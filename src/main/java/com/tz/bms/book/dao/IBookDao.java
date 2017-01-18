package com.tz.bms.book.dao;


import java.util.List;

import com.tz.bms.entity.Book;
import com.tz.bms.entity.Category;
import com.tz.bms.entity.Pageing;

/**
 * 本来用来演示 商品Dao层接口
 *@author 杨倩Yoki
 *@2016-12-22 @下午8:35:43
 */
public interface IBookDao {
	
	/**
	 * 根据商品类别,分页查询商品
	 * @param now
	 * @param size
	 * @param cate
	 * @return
	 */
	public Pageing selectBookByCondition(int now,int size,String cate);

	/**
	 * 根据商品ID,查询商品对象
	 * @param id
	 * @return
	 */
	public Book selectBookById(long id);
	
	public Category selectById(long id);
	
	public List<Category> selectAll();
	
}
