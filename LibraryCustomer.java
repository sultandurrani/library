import java.time.LocalDate;
import java.util.*;
import java.util.Date;
import java.util.Calendar;
public class LibraryCustomer {

	public static void main(String[] args) {
        Scanner stringS = new Scanner(System.in); //String scanner
        Scanner intS = new Scanner (System.in); //Int scanner
        Random random = new Random();
        String checkedOut = "";
        int stop = 1;
        int choice = 0;
        int stop1 = 0;
        long startTime = 0;
        User currentUser = null;
        boolean keepGoing = false;
        Date date = new Date();
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);        

        int inputID;

        Book book1 = new Book(1 ,"Hamlet", random.nextInt(30));
        Book book2 = new Book(2, "Macbeth", random.nextInt(30));
        Book book3 = new Book(3, "Dracula", random.nextInt(30));
        Book book4 = new Book(4, "Othello", random.nextInt(30));
        Book book5 = new Book(5, "Persuasion", random.nextInt(30));
        Book book6 = new Book(6, "Night", random.nextInt(30));
        Book book7 = new Book(7, "Carrie", random.nextInt(30));
        Book book8 = new Book(8, "Matilda", random.nextInt(30));
        Book book9 = new Book(9, "Emma", random.nextInt(30));
        Book book10 = new Book(10, "It", random.nextInt(30));

        Book[] bookInventory = {book1, book2, book3, book4, book5, book6, book7, book8, book9, book10};

        User user1 = new User("Joseph", 0, book1);
        User user2 = new User("Mo", 50, book3);
        User user3 = new User("Rosalie", 10, book10);
        User user4 = new User("Oliver", 0, book7);
        User user5 = new User("Taylor", 30, book5);

        User[] accounts = {user1, user2, user3, user4, user5};

        //User Information Part
        do {
            System.out.println("Welcome! Please choose a service from the menu below:\n" +
                                "Press 1 if you are an existing user\n" +
                                "Press 2 if you are a new user");
            int choice1 = intS.nextInt();
            String name;

            switch(choice1) {

                case 1: System.out.println("Enter the name on the account");
                    name = stringS.next();
                    stringS.nextLine();

                    if (searchUser(accounts,name) != null) {
                        System.out.println();
                        searchUser(accounts,name).displayUser();
                        currentUser = searchUser(accounts,name);
                    }
                    else {
                        System.out.println("Sorry could not find an account under that name. Please try again or register.");
                        keepGoing = true;
                        break;
                    }

                    startTime = System.currentTimeMillis();
                    break;

               case 2: System.out.println("Enter your name");
                    String tempName = stringS.next().toUpperCase();
                    stringS.nextLine();
                    User user6 = new User(tempName, 0, null);
                    currentUser = user6;
                    currentUser.displayUser();
                    break;

                default: System.out.println("The entry was not recognized. Please try again.");
                    keepGoing = true;
            }

        } while (keepGoing == true);


		//Book Check Out / Return Part
		do {
            System.out.println("\nHi " + currentUser.getName() + ". Choose a service from the menu below:\n" +
                    "Enter 1 to check out a book\n" +
                    "Enter 2 to return a book\n" +
                    "Enter 3 to quit");
            choice = intS.nextInt();

            //Checking out a book
            switch (choice) {
                case 1:

                    System.out.println("Here are the books we have available and how many copies we have available\n");

                    for (int i = 0; i < bookInventory.length; i++) {
                        System.out.println(bookInventory[i].getBookID() + ". " + bookInventory[i].getName() + ", " + bookInventory[i].getCount());
                    }

                    System.out.println("\nEnter the ID of the book you would like to check out.");
                    inputID = intS.nextInt(); // nextInt

                    for (int i = 0; i < bookInventory.length; i++) {
                        if (bookInventory[i].getBookID() == (inputID) && bookInventory[i].getCount() > 0) {
                            startTime = System.currentTimeMillis();
                            bookInventory[i].decrementCount(1);
                            currentUser.setCheckedOutBooks(bookInventory[i]);
                            cal.add(Calendar.DATE, 14);
                            Date dueDate = cal.getTime();
                            System.out.println("\nYou have checked out " + bookInventory[i].getName() + " on " + date.toString() +". Please return the book by " + dueDate);
                            System.out.println("The updated inventory count for the book is " + bookInventory[i].getCount());
                            break;
                        }
                        else if (bookInventory[i].getBookID() == (inputID) && bookInventory[i].getCount() <= 0) {
                            System.out.println("There are unfortunately not enough copies available to check out. Please enter 1 if you wish to check out another book");
                        }
                    }
                    break;

                //Returning a book
                case 2:
                    int newBookCount = 0;
                    long endTime = System.currentTimeMillis();
                    long elapsedTime = endTime - startTime;
                    Book tempBook = null;
                    boolean flag = false;
                    String returnBook;

                    do {
                        System.out.println("Enter which book you are returning");
                        returnBook = stringS.nextLine();
                        for (Book b : bookInventory) {
                            if (b.getName().equalsIgnoreCase(returnBook)) {
                                tempBook = b;
                                tempBook.incrementCount(1);
                                newBookCount = b.getCount();
                                flag = true;
                            }
                        }
                        if (flag == false) {
                            System.out.println("Sorry, the entry was not recognized. Please try again");
                        }
                    } while (flag == false);

                    //Charging fines
                    if (startTime > 0 && elapsedTime > 10000 && returnBook.equalsIgnoreCase(checkedOut) || currentUser.getFine() > 0) {
                        System.out.println("You are returning the book late. You will be charged $" +
                                (currentUser.getFine() + (elapsedTime / 10000) * 10) + " as a late fee.");
                        currentUser.setFine((int) (currentUser.getFine() + (elapsedTime / 10000) * 10));
                        currentUser.removeCheckedOutBooks(tempBook);
                        System.out.printf("\nHere is your updated account information:\nName: %s\nFines: $%d\nBooks Checked Out: %s\n\n", currentUser.getName(), currentUser.getFine(), currentUser.getCheckedOutBooks());
                    }
                    else {
                        currentUser.removeCheckedOutBooks(tempBook);
                        System.out.println("You have successfully returned the book. The updated inventory count is " + newBookCount + ". Thank you!");
                    }
                   
                    break;

			    case 3:  stop = 1; break;

			    default:
                System.out.println("Entry not recognized");
            }
            if (choice == 1 || choice == 2) { // to check if we need to print out a press 1 to continue
                System.out.println("Press 1 to keep using the program. Press any other key to quit.");
                stop = intS.nextInt();
            }
        } while (stop == 1);

		currentUser.displayUser();
		System.out.println("Thank you. Have a great day!");

	}

	public static User searchUser(User[] accts, String n) {
	    User current = null;
	    for (User u: accts) {
	        if (u.getName().equalsIgnoreCase(n)) {
                current = u;
            }
        }
	    return current;
    }

    public static String [] searchBook(Book[] inventory, String name) {
	    String[] books = new String[10];
	    for (int i=0; i<inventory.length; i++) {
	        if (inventory[i].getName().contains(name)) {
	            books[i] = inventory[i].getName();
            }
        }
        return books;
    }

}