package com.rutkouski.puzzleshop.model.entity;

import java.time.OffsetDateTime;

public class CustomerComment extends AbstractEntity {
    private int rating;
    private OffsetDateTime date;
    private int puzzleId;
    private int customerId;
    private String comment;

    public CustomerComment() {
    }

    public CustomerComment(int rating, OffsetDateTime date, int puzzleId, int customerId,
                           String comment) {
        this.rating = rating;
        this.date = date;
        this.puzzleId = puzzleId;
        this.customerId = customerId;
        this.comment = comment;
    }

    public CustomerComment(int rating, OffsetDateTime date, int puzzleId, int customerId) {
        this.rating = rating;
        this.date = date;
        this.puzzleId = puzzleId;
        this.customerId = customerId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public int getPuzzleId() {
        return puzzleId;
    }

    public void setPuzzleId(int puzzleId) {
        this.puzzleId = puzzleId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CustomerComment that = (CustomerComment) o;

        if (rating != that.rating) return false;
        if (puzzleId != that.puzzleId) return false;
        if (customerId != that.customerId) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return comment != null ? comment.equals(that.comment) : that.comment == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + rating;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + puzzleId;
        result = 31 * result + customerId;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomerComment{");
        sb.append("rating=").append(rating);
        sb.append(", date=").append(date);
        sb.append(", puzzleId=").append(puzzleId);
        sb.append(", customerId=").append(customerId);
        sb.append(", comment='").append(comment).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
