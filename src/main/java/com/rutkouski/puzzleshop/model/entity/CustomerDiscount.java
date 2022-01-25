package com.rutkouski.puzzleshop.model.entity;

import java.math.BigDecimal;

public class CustomerDiscount extends AbstractEntity {
    private Integer discount;

    public CustomerDiscount() {
    }

    public CustomerDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CustomerDiscount discount1 = (CustomerDiscount) o;

        return discount != null ? discount.equals(discount1.discount) : discount1.discount == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomerDiscount{");
        sb.append("discount=").append(discount);
        sb.append('}');
        return sb.toString();
    }
}
