package pl.javastart.library.app;

import pl.javastart.library.exception.*;
import pl.javastart.library.io.ConsolePrinter;
import pl.javastart.library.io.DataReader;
import pl.javastart.library.io.file.FileManager;
import pl.javastart.library.io.file.FileManagerBuilder;
import pl.javastart.library.model.*;

import java.util.Comparator;
import java.util.InputMismatchException;

public class LibraryControl {
    private ConsolePrinter consolePrinter = new ConsolePrinter();
    private DataReader dataReader = new DataReader(consolePrinter);
    private FileManager fileManager;
    private Library library;

    LibraryControl() {
        fileManager = new FileManagerBuilder(consolePrinter, dataReader).build();
        try {
            library = fileManager.importData();
            consolePrinter.printLine("Data from file is imported");
        } catch (DataImportException | InvalidDataException e) {
            consolePrinter.printLine(e.getMessage());
            consolePrinter.printLine("New database is initaialized");
            library = new Library();
        }
    }

    void controlLoop() {
        Option option;
        do {
            printOptions();
            option = getOption();
            switch (option) {
                case ADD_BOOK -> addBooks();
                case ADD_MAGAZINE -> addMagazines();
                case PRINT_BOOKS -> printBooks();
                case PRINT_MAGAZINES -> printMagazines();
                case DELETE_BOOK -> deleteBook();
                case DELETE_MAGAZINE -> deleteMagazine();
                case ADD_USER -> addUser();
                case PRINT_USERS -> printUsers();
                case FIND_PUBLICATION -> findPublication();
                case EXIT -> exit();
                default -> consolePrinter.printLine("Entred invalid option,try again");
            }
        } while (option != Option.EXIT);
    }

    private void findPublication() {
        consolePrinter.printLine("Entry publication title");
        String title = dataReader.getString();
        String notFoundMessage = "Publication not exist";
        library.findPublicationByTitle(title)
                .map(Publication::toString)
                .ifPresentOrElse(System.out::println, () -> System.out.println(notFoundMessage));
    }

    private void printUsers() {
        consolePrinter.printUser(library.getSortedUsers(
                Comparator.comparing(User::getLastName, String.CASE_INSENSITIVE_ORDER)
        ));
    }

    private void addUser() {
        LibraryUser libraryUser = dataReader.createLibraryUser();
        try {
            library.addUser(libraryUser);
        } catch (UserAlreadyEistException e) {
            consolePrinter.printLine(e.getMessage());
        }
    }
    private Option getOption() {
        boolean optionOK = false;
        Option option = null;
        while (!optionOK) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOK = true;
            } catch (NoSuchOptionException e) {
                consolePrinter.printLine(e.getMessage());
            } catch (InputMismatchException e) {
                consolePrinter.printLine("Entred value is not number, try again");
            }
        }
        return option;
    }

    private void printOptions() {
        consolePrinter.printLine("Choose option: ");
        for (Option option : Option.values()) {
            consolePrinter.printLine(option.toString());
        }
    }

    private void addBooks() {
        try {
            Book book = dataReader.readAndCreateBook();
            library.addPublication(book);
        } catch (InputMismatchException e) {
            consolePrinter.printLine("Failed to add book");
        } catch (ArrayIndexOutOfBoundsException e) {
            consolePrinter.printLine(e.getMessage());
        }
    }
    private void deleteBook() {
        Book book = dataReader.readAndCreateBook();
        try {
            if (library.removePublication(book)) {
                consolePrinter.printLine("Book is deleted");
            } else
                consolePrinter.printLine("Chosen book not exist");
        } catch (InputMismatchException e) {
            consolePrinter.printLine("Entry invalid data");
        }
    }

    private void addMagazines() {
        try {
            Magazine magazine = dataReader.readAndCreateMagazine();
            library.addPublication(magazine);
        } catch (InputMismatchException e) {
            consolePrinter.printLine("Failed to add magazine");
        } catch (ArrayIndexOutOfBoundsException e) {
            consolePrinter.printLine(e.getMessage());
        }
    }
    private void deleteMagazine() {
        Magazine magazine = dataReader.readAndCreateMagazine();
        try {
            if (library.removePublication(magazine)) {
                consolePrinter.printLine("Magazine is deleted");
            } else
                consolePrinter.printLine("Chosen magazine not exist");
        } catch (InputMismatchException e) {
            consolePrinter.printLine("Entry invalid data");
        }
    }

    private void printBooks() {
        consolePrinter.printBooks(library.getSortedPublications(
                Comparator.comparing(Publication::getTitle, String.CASE_INSENSITIVE_ORDER)
        ));
    }

    private void printMagazines() {
        consolePrinter.printMagazines(library.getSortedPublications(
                Comparator.comparing(Publication::getTitle, String.CASE_INSENSITIVE_ORDER)
        ));
    }

    private void exit() {
        try {
            fileManager.exportData(library);
            consolePrinter.printLine("Succesfull export data");
        } catch (DataExportException e) {
            consolePrinter.printLine(e.getMessage());
        }
        consolePrinter.printLine("End of the program, bye bye! ");
        dataReader.close();
    }

}
