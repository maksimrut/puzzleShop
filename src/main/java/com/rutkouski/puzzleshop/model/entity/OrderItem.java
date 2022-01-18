package com.rutkouski.puzzleshop.model.entity;

public class OrderItem extends AbstractEntity {
    private int itemQuantity;
    private int puzzleId;
    private int orderId;

    public OrderItem() {
    }

    public OrderItem(int itemQuantity, int puzzleId, int orderId) {
        super();
        this.itemQuantity = itemQuantity;
        this.puzzleId = puzzleId;
        this.orderId = orderId;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getPuzzleId() {
        return puzzleId;
    }

    public void setPuzzleId(int puzzleId) {
        this.puzzleId = puzzleId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OrderItem orderItem = (OrderItem) o;

        if (itemQuantity != orderItem.itemQuantity) return false;
        if (puzzleId != orderItem.puzzleId) return false;
        return orderId == orderItem.orderId;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + itemQuantity;
        result = 31 * result + puzzleId;
        result = 31 * result + orderId;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderItem{");
        sb.append("itemQuantity=").append(itemQuantity);
        sb.append(", puzzleId=").append(puzzleId);
        sb.append(", orderId=").append(orderId);
        sb.append('}');
        return sb.toString();
    }
}
