import java.time.LocalDate;
import java.util.*;
public class LibraryCustomer {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Random random = new Random();
		String checkedOut = "";
		int stop = 0;
		
		String dayOfWeek = LocalDate.now().getDayOfWeek().name();
		String bookName;
		long startTime = System.currentTimeMillis();

		Book book1 = new Book("To Kill A Mockingbird", random.nextInt(30));
		Book book2 = new Book("Catch 22", random.nextInt(30));
		Book book3 = new Book("Gone With The Wind", random.nextInt(30));
		Book book4 = new Book("Don Quixote", random.nextInt(30));
		Book book5 = new Book("The Lord Of The Rings", random.nextInt(30));
		Book book6 = new Book("A Tale Of Two Cities", random.nextInt(30));
		Book book7 = new Book("And Then There Were None", random.nextInt(30));
		Book book8 = new Book("Alice's Adventures In Wonderland", random.nextInt(30));
		Book book9 = new Book("The Alchemist", random.nextInt(30));
		Book book10 = new Book("Kane and Abel", random.nextInt(30));
		
		Book [] bookInventory = {book1,book2,book3,book4,book5,book6,book7,book8,book9,book10};

		do {
			System.out.println("Here are the books we have available and how many copies we have available\n");
			for (int i = 0; i < bookInventory.length; i++) {
				System.out.println(bookInventory[i].name() + ", " + bookInventory[i].count());
			}

			System.out.println("\nEnter 1 to check out a book. Enter 2 to return a book.");
			int choice = s.nextInt();
			s.nextLine();

			if (choice == 1) {

				System.out.println("\nEnter the name of the book you would like to check out (Please be mindful of exact spelling.)");
				bookName = s.nextLine();
				checkedOut = bookName;

				for (int i = 0; i < bookInventory.length; i++) {
					if (bookInventory[i].name().equalsIgnoreCase(bookName)) {
						--bookInventory[i].count;
						System.out.println("\nYou have checked out " + bookInventory[i].name() + ". Please return the book by next " + dayOfWeek);
						System.out.println("The updated inventory count for the book is " + bookInventory[i].count());
						System.out.println("Press 1 to keep using the program");
						stop = s.nextInt();
					}
				}

			} else if (choice == 2) {

				int newBookCount = 0;
				long endTime = System.currentTimeMillis();
				long elapsedTime = endTime - startTime;
				System.out.println("Enter which book you are returning");
				String returnBook = s.nextLine();
				for (Book b : bookInventory) {
					if (b.name().equalsIgnoreCase(returnBook)) {
						++b.count;
						newBookCount = b.count;
					}
				}
				if (elapsedTime > 10000 && returnBook.equalsIgnoreCase(checkedOut)) {
					System.out.println("You are returning the book late. You will be charged $" +
							((elapsedTime / 10000) * 10) + " as a late fee.");
				} else {
					System.out.println("You have successfully returned the book. The updated inventory count is " + newBookCount + ". Thank you!");
				}
				System.out.println("Press 1 to keep using the program");
				stop = s.nextInt();

			} else {
				System.out.println("Entry not recognized");
				stop = 1;
			}
		} while (stop == 1);
	}

}