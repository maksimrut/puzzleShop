package com.rutkouski.puzzleshop.model.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Order extends AbstractEntity {
    private OffsetDateTime date;
    private BigDecimal totalPrice;
    private Status status;
    private int customerId;

    public enum Status {
        IN_PROCESS("В обработке"),
        APPROVED("Одобрен"),
        PAID("Оплачен"),
        CANCELLED("Отменен");

        String value;

        Status(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public Order() {
    }

    public Order(OffsetDateTime date, BigDecimal totalPrice, int customerId) {
        this.date = date;
        this.totalPrice = totalPrice;
        this.status = Status.IN_PROCESS;
        this.customerId = customerId;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        if (customerId != order.customerId) return false;
        if (date != null ? !date.equals(order.date) : order.date != null) return false;
        if (totalPrice != null ? !totalPrice.equals(order.totalPrice) : order.totalPrice != null) return false;
        return status == order.status;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + customerId;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("date=").append(date);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", status=").append(status);
        sb.append(", customerId=").append(customerId);
        sb.append('}');
        return sb.toString();
    }
}
