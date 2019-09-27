package com.subjectsbooks.app.model;

import java.io.Serializable;
import java.util.Date;


public class BookDTO  implements Serializable {
		
		private static final long serialVersionUID = 1L;
		String bookId;
		String title;
		String price;
		String volume;
		String publishDate;
		String message;
		

				public String getBookId() {
			return bookId;
		}



		public void setBookId(String bookId) {
			this.bookId = bookId;
		}



		public String getMessage() {
			return message;
		}



		public void setMessage(String message) {
			this.message = message;
		}



		public String getTitle() {
			return title;
		}



		public void setTitle(String title) {
			this.title = title;
		}



		public String getPrice() {
			return price;
		}



		public void setPrice(String price) {
			this.price = price;
		}



		public String getVolume() {
			return volume;
		}



		public void setVolume(String volume) {
			this.volume = volume;
		}



		public String getPublishDate() {
			return publishDate;
		}



		public void setPublishDate(String publishDate) {
			this.publishDate = publishDate;
		}



				@Override
		public String toString() {
			return "Book [bookId=" + bookId + ", title=" + title + ", price=" + price + ", volume=" + volume
					+ ", publishDate=" + publishDate + "]";
		}

	}
