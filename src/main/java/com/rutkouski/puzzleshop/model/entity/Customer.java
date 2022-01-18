package com.rutkouski.puzzleshop.model.entity;

import java.math.BigDecimal;

public class Customer extends User {
    private BigDecimal balance;
    private CustomerDiscount customerDiscount;

    public Customer() {
    }

    public Customer(String login, String password, String email, Role role,
                    BigDecimal balance, CustomerDiscount customerDiscount) {
        super(login, password, email, role);
        this.balance = balance;
        this.customerDiscount = customerDiscount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public CustomerDiscount getCustomerDiscount() {
        return customerDiscount;
    }

    public void setCustomerDiscount(CustomerDiscount customerDiscount) {
        this.customerDiscount = customerDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Customer customer = (Customer) o;

        if (balance != null ? !balance.equals(customer.balance) : customer.balance != null) return false;
        return customerDiscount != null ? customerDiscount.equals(customer.customerDiscount) : customer.customerDiscount == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (customerDiscount != null ? customerDiscount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Customer{");
        sb.append("balance=").append(balance);
        sb.append(", customerDiscount=").append(customerDiscount);
        sb.append('}');
        return sb.toString();
    }
}
