import java.time.LocalDate;
import java.util.*;
public class LibraryCustomer {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		String dayOfWeek = LocalDate.now().getDayOfWeek().name();
		String bookName;
		
		Book book1 = new Book("To Kill A Mockingbird", 10);
		Book book2 = new Book("Catch-22", 20);
		Book book3 = new Book("Gone With The Wind", 15);
		Book book4 = new Book("Don Quixote", 5);
		Book book5 = new Book("The Lord Of The Rings", 7);
		Book book6 = new Book("A Tale Of Two Cities", 14);
		Book book7 = new Book("And Then There Were None", 22);
		Book book8 = new Book("Alice's Adventures In Wonderland", 12);
		Book book9 = new Book("The Alchemist", 17);
		Book book10 = new Book("Kane And Abel", 30);
		
		Book [] bookInventory = {book1,book2,book3,book4,book5,book6,book7,book8,book9,book10};
		
		System.out.println("Here are the books and how many copies we have available\n");
		for (int i=0; i<bookInventory.length; i++) {
			System.out.println(bookInventory[i].name() + ", " + bookInventory[i].count());
		}
		
		System.out.println("\nEnter the name of the book you would like to check out (Please be mindful of exact spelling.)");
		bookName = s.nextLine();
		
		for (int i=0; i<bookInventory.length; i++) {	
			if (bookInventory[i].name().equals(bookName)) {
				--bookInventory[i].count;
				System.out.println("\nYou have checked out " +bookInventory[i].name() + ". Please return the book by next " + dayOfWeek);
				System.out.println("The updated inventory count for the book is " + bookInventory[i].count());
			}
		}
		
	}

}