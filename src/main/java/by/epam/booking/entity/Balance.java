package by.epam.booking.entity;

import java.util.Date;
import java.util.Objects;

public class Balance {
    private Integer idOperation;
    private Double balance;
    private long buffDate;
    private String type;
    private Date date;
    private String authorLogin;

    public Balance(Integer idOperation, Double balance, String type, Date date, String authorLogin) {
        this.idOperation = idOperation;
        this.balance = balance;
        this.type = type;
        this.date = date;
        this.authorLogin = authorLogin;
    }

    public Balance() {
    }

    public Integer getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Integer idOperation) {
        this.idOperation = idOperation;
    }

    public Double getBalance() {
        return balance;
    }

    public long getBuffDate() {
        return buffDate;
    }

    public void setBuffDate(long buffDate) {
        this.buffDate = buffDate;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthorLogin() {
        return authorLogin;
    }

    public void setAuthorLogin(String authorLogin) {
        this.authorLogin = authorLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Balance)) return false;
        Balance balance1 = (Balance) o;
        return  getIdOperation() == balance1.getIdOperation() &&
                getBalance() == balance1.getBalance() &&
                getDate() == balance1.getDate()&&
                getAuthorLogin().equals(balance1.getAuthorLogin());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getIdOperation();
        result = 31 * result + getAuthorLogin().hashCode();
        result = (int) (31 * result + getBalance());
        result = 31 * result + getDate().hashCode();
        temp = Double.doubleToLongBits(getIdOperation());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Balance{");
        sb.append("idOperation=").append(idOperation);
        sb.append(", balance=").append(balance);
        sb.append(", type=").append(type);
        sb.append(", date=").append(date);
        sb.append(", authorLogin='").append(authorLogin).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
