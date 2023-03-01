package pl.javastart.library.io;

import pl.javastart.library.model.Book;
import pl.javastart.library.model.LibraryUser;
import pl.javastart.library.model.Magazine;

import java.util.Scanner;

public class DataReader {
    private Scanner sc = new Scanner(System.in);
    private ConsolePrinter consolePrinter;

    public DataReader(ConsolePrinter consolePrinter) {
        this.consolePrinter = consolePrinter;

    }

    public String getString() {
        return sc.nextLine();
    }

    public void close() {
        sc.close();
    }

    public int getInt() {
        try {
            return sc.nextInt();
        } finally {
            sc.nextLine();
        }
    }

    public Book readAndCreateBook() {
        consolePrinter.printLine("Title");
        String title = sc.nextLine();
        consolePrinter.printLine("Author");
        String author = sc.nextLine();
        consolePrinter.printLine("Publisher");
        String publisher = sc.nextLine();
        consolePrinter.printLine("ISBN");
        String isbn = sc.nextLine();
        consolePrinter.printLine("Release Date");
        int releaseDate = getInt();
        consolePrinter.printLine("Pages");
        int pages = getInt();

        return new Book(title,publisher, releaseDate, author, pages, isbn);
    }
    public LibraryUser createLibraryUser(){
        consolePrinter.printLine("Name");
        String firstName = sc.nextLine();
        consolePrinter.printLine("Last Name");
        String lastName = sc.nextLine();
        consolePrinter.printLine("PESEL");
        String pesel = sc.nextLine();
        return new LibraryUser(firstName, lastName, pesel);
    }

    public Magazine readAndCreateMagazine() {
        consolePrinter.printLine("Title");
        String title = sc.nextLine();
        consolePrinter.printLine("Publisher");
        String publisher = sc.nextLine();
        consolePrinter.printLine("Language");
        String language = sc.nextLine();
        consolePrinter.printLine("Year");
        int year = getInt();
        consolePrinter.printLine("Month");
        int month = getInt();
        consolePrinter.printLine("Day");
        int day = getInt();

        return new Magazine(title, publisher, year, month, day, language);
    }
}
