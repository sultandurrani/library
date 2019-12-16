import java.util.ArrayList;

public class User {

    private String name;
    private int fine;
    private ArrayList<Book> checkedOutBooks = new ArrayList<Book>();

    public User(String name, int fine, Book b) {
        this.name = name;
        this.fine = fine;
        checkedOutBooks.add(b);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public int getFine() {
        return this.fine;
    }

    public void setCheckedOutBooks(Book b) {
        checkedOutBooks.add(b);
    }

    public void removeCheckedOutBooks(Book b) {
        checkedOutBooks.remove(b);
    }

    public ArrayList<String>  getCheckedOutBooks() {
        ArrayList<String> books = new ArrayList<String>();
        for (Book b: checkedOutBooks) {
            if (b != null) {
                books.add(b.getName());
            }
        }
        return books;
    }

    public void displayUser() {
        System.out.println("Name: " + getName() + "\n" +
                "Fines: $" + getFine() + "\n" +
                "Books Checked Out: " + getCheckedOutBooks());
    }

}