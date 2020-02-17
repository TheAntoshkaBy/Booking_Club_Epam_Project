package by.epam.booking.entity;

import java.util.ArrayList;

public class Book {
    private Integer count;
    private String author;
    private String name;
    private String description;
    private Integer id;
    private ArrayList<Comment> comments;
    private Comment buffComment;
    private long buffDate;
    private boolean status;
    private String image;

    public Book(String name, String author,  String description, Integer count) {
        this.count = count;
        this.author = author;
        this.name = name;
        this.description = description;
        status = false;
    }

    public Comment getBuffComment() {
        return buffComment;
    }

    public void setBuffComment(Comment buffComment) {
        this.buffComment = buffComment;
    }

    public long getBuffDate() {
        return buffDate;
    }

    public void setBuffDate(long buffDate) {
        this.buffDate = buffDate;
    }

    public Book() {
        comments = new ArrayList<>();
        status = false;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("count=").append(count);
        sb.append(", author='").append(author).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
