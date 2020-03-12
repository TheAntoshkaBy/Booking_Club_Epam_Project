package by.epam.booking.entity;

import com.mysql.fabric.xmlrpc.base.Data;
import javafx.scene.chart.PieChart;

import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return getId() == comment.getId() &&
                getAuthor().equals(comment.getAuthor());
    }

    @Override
    public int hashCode() {

        int result;
        long temp;
        result = getId();
        result = 31 * result + getAuthor().hashCode();
        temp = Double.doubleToLongBits(getAuthor().hashCode());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append("id=").append(id);
        sb.append(", bookId=").append(bookId);
        sb.append(", readingPlanId=").append(readingPlanId);
        sb.append(", date=").append(date);
        sb.append(", author='").append(author).append('\'');
        sb.append(", text='").append(text).append('\'');
        sb.append(", header='").append(header).append('\'');
        sb.append(", owner=").append(owner);
        sb.append('}');
        return sb.toString();
    }
}
