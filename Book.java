public class Book {

	private String name;
	private int count;
    private int bookID;
	
	public Book( int bookID, String name, int count) {
		this.bookID = bookID;
		this.name = name;
		this.count = count;
	}

	public String getName() {
		return name;
	}
   
    public int getBookID() {
      return bookID;
   }

   public void setBookID(int bookID) {this.bookID = bookID;}

	public void setName(String name) {this.name = name;}
	
	public int getCount() {
		return count;
	}

	public void incrementCount(int c) {
	    this.count += c;
    }

    public void decrementCount(int c) {
	    this.count -= c;
    }

	public void setCount(int count) {this.count = count;}
	
}