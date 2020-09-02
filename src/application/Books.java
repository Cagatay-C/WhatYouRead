package application;

public class Books {
	private int book_id;
	private String book_name;
	private String author_name;
	
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	Books(){
		
	}
	Books(int book_id, String book_name, String author_name){
		this.book_id = book_id;
		this.book_name = book_name;
		this.author_name = author_name;
		
	}
}
