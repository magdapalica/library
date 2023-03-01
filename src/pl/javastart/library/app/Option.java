package pl.javastart.library.app;

import pl.javastart.library.exception.NoSuchOptionException;

public enum Option {
    EXIT(0, "Close program"),
    ADD_BOOK(1, "Add new book to library"),
    ADD_MAGAZINE(2, " Add new magazine to library"),
    PRINT_BOOKS(3, "Show avaiable books"),
    PRINT_MAGAZINES(4, " Show avaiable magazines"),
    DELETE_BOOK(5, "Remove Book"),
    DELETE_MAGAZINE(6, "Remove Magazine"),
    ADD_USER(7, "Add new User"),
    PRINT_USERS(8, "Show all Users"),
    FIND_PUBLICATION(9, "Show Publication");

    private int value;
    private String description;

    Option(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }

    static Option createFromInt(int option) throws NoSuchOptionException {
        try {
            return Option.values()[option];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchOptionException("Unavailable option" + option);
        }
    }
}
