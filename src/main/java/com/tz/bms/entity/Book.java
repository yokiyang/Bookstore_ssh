package com.tz.bms.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 
 * 本来用来演示 图书实体类
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:48:42
 */
@Entity
@Table(name="ONLINE_BOOK")
public class Book {
	private Long bookId;
	private String bookName;
	private String author;
	private String publish;
	private Date publishDate;
	private double price;
	private String introduce;
	private String imgUrl;
	private Category category;
	
	@Id
	@GeneratedValue
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	@ManyToOne
	@JoinColumn(name="CATE_ID")
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName
				+ ", author=" + author + ", publish=" + publish
				+ ", publishDate=" + publishDate + ", price=" + price
				+ ", introduce=" + introduce + ", imgUrl=" + imgUrl
				+ ", category=" + category + "]";
	}
}
