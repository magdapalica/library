package pl.javastart.library.model;

import java.io.Serializable;
import java.time.Year;
import java.util.Objects;

public abstract class Publication implements Serializable, CsvConvertible{
    private String title;
    private String publisher;
    private Year year;


    Publication(String title, String publisher, int year) {
        this.title = title;
        this.publisher = publisher;
        this.year = Year.of(year);
    }

    public String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getPublisher() {
        return publisher;
    }

    void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return title + ", " + publisher + ", " + year.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publication)) return false;
        Publication that = (Publication) o;
        return Objects.equals(title, that.title) && Objects.equals(publisher, that.publisher) && Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, publisher, year);
    }
}
