package by.epam.booking.entity;

import by.epam.booking.type.UserRoleType;

import java.util.ArrayList;
import java.util.Objects;

public class User {

    private String login;
    private String password;
    private String email;
    private String name;
    private String surname;
    private Enum<UserRoleType> role;
    private double moneyBalance;
    private Integer bookId;
    private Integer readingPlanId;
    private String readingPlanName;
    private boolean isActive;
    private String bookName;
    private ArrayList<Integer> completedBooks;
    private String image;
    private long buffDate;
    private String buffMoneyType;
    private String status;

    public User(String login, String password, String email, String name, String surname, Enum<UserRoleType> role, boolean isActive) {
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

    public User(String login) {
        this.login = login;
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

    public Enum<UserRoleType> getRole() {
        return role;
    }

    public void setRole(String role) {
        if(role.equals("ADMIN"))
        this.role = UserRoleType.ADMIN;
        else
            this.role = UserRoleType.USER;
    }
    public void setRole(UserRoleType userRoleType) {
       this.role = userRoleType;
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

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public void setRole(Enum<UserRoleType> role) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getBuffDate() {
        return buffDate;
    }

    public void setBuffDate(long buffDate) {
        this.buffDate = buffDate;
    }

    public String getBuffMoneyType() {
        return buffMoneyType;
    }

    public void setBuffMoneyType(String buffMoneyType) {
        this.buffMoneyType = buffMoneyType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("login='").append(login).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getLogin().equals(user.getLogin()) &&
                getEmail().equals(user.getEmail()) &&
                getName().equals(user.getName()) &&
                getSurname().equals(user.getSurname()) &&
                getBookId().equals(user.getBookId()) &&
                getReadingPlanId().equals(user.getReadingPlanId());
    }

    @Override
    public int hashCode() {

        int result;
        long temp;
        result = getLogin().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getSurname().hashCode();
        result = 31 * result + getBookId();
        temp = Double.doubleToLongBits(getReadingPlanId());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
