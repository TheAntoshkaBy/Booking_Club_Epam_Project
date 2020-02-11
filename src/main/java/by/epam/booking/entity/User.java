package by.epam.booking.entity;

import by.epam.booking.enumeration.Role;

import java.util.ArrayList;

public class User {

    private String login;
    private String password;
    private String email;
    private String name;
    private String surname;
    private Enum<Role> role;
    private double moneyBalance;
    private Integer bookId;
    private Integer readingPlanId;
    private String readingPlanName;
    private boolean isActive;
    private String bookName;
    private ArrayList<Integer> completedBooks;

    public User(String login, String password, String email, String name, String surname, Enum<Role> role, boolean isActive) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.isActive = isActive;
    }

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Enum<Role> getRole() {
        return role;
    }

    public void setRole(String role) {
        if(role.equals("ADMIN"))
        this.role = Role.ADMIN;
        else
            this.role = Role.USER;
    }
    public void setRole(Role role) {
       this.role = role;
    }
    public double getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(double moneyBalance) {
        this.moneyBalance = moneyBalance;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setRole(Enum<Role> role) {
        this.role = role;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getReadingPlanId() {
        return readingPlanId;
    }

    public void setReadingPlanId(Integer readingPlanId) {
        this.readingPlanId = readingPlanId;
    }

    public String getReadingPlanName() {
        return readingPlanName;
    }

    public void setReadingPlanName(String readingPlanName) {
        this.readingPlanName = readingPlanName;
    }

    public ArrayList<Integer> getCompletedBooks() {
        return completedBooks;
    }

    public void setCompletedBooks(ArrayList<Integer> completeBooks) {
        this.completedBooks = completeBooks;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("login='").append(login).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
