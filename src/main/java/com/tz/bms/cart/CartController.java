package com.tz.bms.cart;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tz.bms.book.service.IBookService;
import com.tz.bms.entity.Book;
import com.tz.bms.entity.Cart;
import com.tz.bms.entity.OrderItem;

/**
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:47:30
 */
@Controller
public class CartController {

	@Resource
	private IBookService bookService;

	@RequestMapping("/cart/{id}/addCart")
	public void addCart(@PathVariable String id, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			Book book = bookService.queryBookById(Long.parseLong(id));
			OrderItem item = new OrderItem();
			int count = 1;
			item.setItemId(Long.parseLong(id));
			item.setBook(book);
			item.setCount(count);
			item.setAllPrice(book.getPrice());
			//获得购物车
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			if (null == cart) {
				cart = new Cart();
			}
			//把订单明细添加到购物车
			cart.addCart(item);
			//把购物车放到session作用域中
			request.getSession().setAttribute("cart", cart);
			//商品总数量
			int allCount = cart.getAllcount();
			//商品总价
			double allPrice = cart.getAllPrice();
			response.getWriter().print("1:" + allCount + ":" + allPrice + "");
		} catch (Exception e) {
			response.getWriter().print("0");
			e.printStackTrace();
		}
	}

	@RequestMapping("/cart/clearCart")
	public void clearCart(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			cart.clearCart();
			request.getSession().setAttribute("cart", cart);
			//商品总数量
			int allCount = cart.getAllcount();
			//商品总价
			double allPrice = cart.getAllPrice();
			//该商品总价
			response.getWriter().print("1:" + allCount + ":" + allPrice + "");
		} catch (Exception e) {
			response.getWriter().print("0");
			e.printStackTrace();
		}
	}

	@RequestMapping("/cart/{count}/{bookId}/modifyCart")
	public void modifyCart(HttpServletResponse response,HttpServletRequest request,@PathVariable String count,@PathVariable String bookId) throws IOException {
		try {
			//拿到该图书
			Book b = bookService.queryBookById(Long.parseLong(bookId));
			//拿到图书数量
			//获得购物车
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			//修改购物车明细
			cart.modifyCart(bookId, count);
			//把购物车放到session作用域中
			request.getSession().setAttribute("cart", cart);
			//商品总数量
			int allCount = cart.getAllcount();
			//商品总价
			double allPrice = cart.getAllPrice();
			//该商品总价
			double onePrice = Integer.parseInt(count) * b.getPrice();
			response.getWriter().print("1:" + allCount + ":" + allPrice + ":" + onePrice + "");
		} catch (Exception e) {
			response.getWriter().print("0");
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/cart/{ids}/removecart")
	public void removecart(HttpServletRequest request,@PathVariable String ids,HttpServletResponse response) throws IOException{
		try{
			//String ids=request.getParameter("ids");
			Cart cart=(Cart) request.getSession().getAttribute("cart");
			cart.removeCart(ids);
			request.getSession().setAttribute("cart", cart);
			//商品总数量
			int allCount = cart.getAllcount();
			//商品总价
			double allPrice = cart.getAllPrice();
			//该商品总价
			response.getWriter().print("1:"+allCount+":"+allPrice+"");	
		}catch(Exception e){
			response.getWriter().print("0");
			e.printStackTrace();
		}
	}
}
