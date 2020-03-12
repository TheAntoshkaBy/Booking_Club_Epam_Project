package by.epam.booking.entity;

import java.util.ArrayList;
import java.util.Objects;

public class ReadingPlan {
    private String name;
    private String description;
    private ArrayList<Book> books;
    private int idReadingPlan;

    public ReadingPlan(String name, String description, ArrayList<Book> books) {
        this.name = name;
        this.description = description;
        this.books = books;
    }

    public ReadingPlan(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ReadingPlan() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdReadingPlan() {
        return idReadingPlan;
    }

    public void setIdReadingPlan(int idReadingPlan) {
        this.idReadingPlan = idReadingPlan;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReadingPlan)) return false;
        ReadingPlan that = (ReadingPlan) o;
        return getIdReadingPlan() == that.getIdReadingPlan() &&
                getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getIdReadingPlan();
        result = 31 * result + getName().hashCode();
        temp = Double.doubleToLongBits(getIdReadingPlan());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
