package com.tz.bms.book.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tz.bms.book.service.IBookService;
import com.tz.bms.entity.Book;
import com.tz.bms.entity.Category;
import com.tz.bms.entity.Pageing;

/**
 * 本来用来演示
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:43:37
 */
@Controller
public class BookController {
	@Resource
	private IBookService bookService;

	@RequestMapping("/book/IndexAction")
	public String indexAction(Model model, HttpServletRequest request) {
		Pageing bestBook = bookService.queryBookByCondition(1, 2, "精选图书");
		Pageing newBook = bookService.queryBookByCondition(1, 3, "新书");
		Pageing recommendBook = bookService.queryBookByCondition(1, 3, "推荐图书");
		List<Category> categorys = bookService.selectAllCategory();
		model.addAttribute("bestBook", bestBook);
		model.addAttribute("newBook", newBook);
		request.getSession().setAttribute("recommendBook", recommendBook);
		request.getSession().setAttribute("categorys", categorys);
		return "/jsp/book/index.jsp";

	}

	@RequestMapping("/book/{pageNow}/{pageSize}/cateBook")
	public String cateBook(@PathVariable String pageNow,
			@PathVariable String pageSize, Model model) {
		pageNow = pageNow == null ? "1" : pageNow;
		pageSize = pageSize == null ? "8" : pageSize;
		Pageing pageing = bookService.queryBookByCondition(
				Integer.parseInt(pageNow), Integer.parseInt(pageSize), null);
		model.addAttribute("pageing", pageing);
		return "/jsp/book/category.jsp";
	}

	@RequestMapping("/book/{bid}/detailBook")
	public String detailBook(@PathVariable String bid, Model model) {
		Book book = bookService.queryBookById(Long.parseLong(bid));
		model.addAttribute("book", book);
		return "/jsp/book/detail.jsp";
	}

	@RequestMapping("/book/{cateId}/{pageNow}/{pageSize}/viewBookByCate")
	public String viewBookByCate(Model model,HttpServletRequest request,@PathVariable String cateId,@PathVariable String pageNow,String pageSize) {
		Category category = bookService.querytById(Long.parseLong(cateId));
		pageNow = pageNow == null ? "1" : pageNow;
		pageSize = pageSize == null ? "8" : pageSize;
		Pageing pageing = bookService.queryBookByCondition(
				Integer.parseInt(pageNow), Integer.parseInt(pageSize),
				category.getCateName());
		request.getSession().setAttribute("pageing", pageing);
		model.addAttribute("cateName", category.getCateName());
		return "/jsp/book/specials.jsp";
	}
}
