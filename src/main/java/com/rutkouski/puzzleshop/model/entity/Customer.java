package com.rutkouski.puzzleshop.model.entity;

import java.math.BigDecimal;

public class Customer extends User {
    private int discount;

    public Customer() {
    }

    public Customer(String login, String password, String email, Role role) {
        super(login, password, email, role);
        this.discount = 0;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Customer customer = (Customer) o;

        return discount == customer.discount;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + discount;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Customer{");
        sb.append("discount=").append(discount);
        sb.append('}');
        return sb.toString();
    }
}
