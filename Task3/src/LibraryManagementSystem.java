import java.util.*;

class Book {
    private int id;
    private String title;
    private boolean isIssued;

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.isIssued = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issueBook() {
        isIssued = true;
    }

    public void returnBook() {
        isIssued = false;
    }

    @Override
    public String toString() {
        return id + ". " + title + " - " + (isIssued ? "Issued" : "Available");
    }
}

class User {
    private int id;
    private String name;
    private Book borrowedBook;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBook = null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean hasBorrowedBook() {
        return borrowedBook != null;
    }

    public Book getBorrowedBook() {
        return borrowedBook;
    }

    public void borrowBook(Book book) {
        this.borrowedBook = book;
    }

    public void returnBook() {
        this.borrowedBook = null;
    }

    @Override
    public String toString() {
        return id + ". " + name + (borrowedBook != null ? " (Borrowed: " + borrowedBook.getTitle() + ")" : "");
    }
}

class Library {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void displayBooks() {
        System.out.println("\nBook List:");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public void displayUsers() {
        System.out.println("\nUser List:");
        for (User u : users) {
            System.out.println(u);
        }
    }

    public Book findBookById(int id) {
        for (Book b : books) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    public User findUserById(int id) {
        for (User u : users) {
            if (u.getId() == id) return u;
        }
        return null;
    }

    public void issueBook(int userId, int bookId) {
        User user = findUserById(userId);
        Book book = findBookById(bookId);
        if (user == null || book == null) {
            System.out.println("Invalid user or book ID.");
        } else if (user.hasBorrowedBook()) {
            System.out.println("User already has a borrowed book.");
        } else if (book.isIssued()) {
            System.out.println("Book is already issued.");
        } else {
            book.issueBook();
            user.borrowBook(book);
            System.out.println("Book issued successfully.");
        }
    }

    public void returnBook(int userId) {
        User user = findUserById(userId);
        if (user == null || !user.hasBorrowedBook()) {
            System.out.println("No book to return.");
        } else {
            Book book = user.getBorrowedBook();
            book.returnBook();
            user.returnBook();
            System.out.println("Book returned successfully.");
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        // Sample data
        library.addBook(new Book(1, "Java Programming"));
        library.addBook(new Book(2, "Data Structures"));
        library.addBook(new Book(3, "OOP in Java"));

        library.addUser(new User(101, "Alice"));
        library.addUser(new User(102, "Bob"));

        while (true) {
            System.out.println("\n====== Library Menu ======");
            System.out.println("1. View Books");
            System.out.println("2. View Users");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    library.displayUsers();
                    break;
                case 3:
                    System.out.print("Enter User ID: ");
                    int uid = sc.nextInt();
                    System.out.print("Enter Book ID: ");
                    int bid = sc.nextInt();
                    library.issueBook(uid, bid);
                    break;
                case 4:
                    System.out.print("Enter User ID: ");
                    int rid = sc.nextInt();
                    library.returnBook(rid);
                    break;
                case 5:
                    System.out.println("Exiting the system...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

