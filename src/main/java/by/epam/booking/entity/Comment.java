package by.epam.booking.entity;

import com.mysql.fabric.xmlrpc.base.Data;
import javafx.scene.chart.PieChart;

import java.util.Date;

public class Comment {
    private int id;
    private int bookId;
    private int readingPlanId;
    private Date date;
    private String author;
    private String text;
    private String header;
    private boolean owner;

    public Comment(int bookId, String author, String text, String header) {
        this.bookId = bookId;
        this.readingPlanId = readingPlanId;
        this.author = author;
        this.text = text;
        this.header = header;
        owner=true;
    }

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getReadingPlanId() {
        return readingPlanId;
    }

    public void setReadingPlanId(int readingPlanId) {
        this.readingPlanId = readingPlanId;
    }
}
