package com.subjectsbooks.app.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class BookVO {
	
	@Id
	@Column(name="bookid")
	protected	long bookId;

	@Column(name="title")
	protected String title;

	@Column(name="price")
	protected double price;

	@Column(name="volume")
	protected Integer volume;

	@Column(name="publishDate")
	protected Date publishDate;

	public BookVO() {
		
	}
	
	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	
	@Override
	public String toString() {
		return "Book [bookid=" + bookId + ", title=" + title +  ", price=" + price +", volume=" + volume + ", publishDate=" + publishDate + "]";
	}

}
