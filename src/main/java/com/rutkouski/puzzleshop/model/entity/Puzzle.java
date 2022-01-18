package com.rutkouski.puzzleshop.model.entity;

import java.math.BigDecimal;

public class Puzzle extends AbstractEntity {
    private String name;
    private BigDecimal price;
    private int difficultyLevel;
    private String description;
    private String picturePath;

    public Puzzle() {
    }

    public Puzzle(String name, BigDecimal price, int difficultyLevel, String description,
                  String picturePath) {
        this.name = name;
        this.price = price;
        this.difficultyLevel = difficultyLevel;
        this.description = description;
        this.picturePath = picturePath;
    }

    public Puzzle(String name, BigDecimal price, int difficultyLevel, String description) {
        this.name = name;
        this.price = price;
        this.difficultyLevel = difficultyLevel;
        this.description = description;
    }

    public Puzzle(String name, BigDecimal price, int difficultyLevel) {
        this.name = name;
        this.price = price;
        this.difficultyLevel = difficultyLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Puzzle puzzle = (Puzzle) o;

        if (difficultyLevel != puzzle.difficultyLevel) return false;
        if (name != null ? !name.equals(puzzle.name) : puzzle.name != null) return false;
        if (price != null ? !price.equals(puzzle.price) : puzzle.price != null) return false;
        if (description != null ? !description.equals(puzzle.description) : puzzle.description != null) return false;
        return picturePath != null ? picturePath.equals(puzzle.picturePath) : puzzle.picturePath == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + difficultyLevel;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (picturePath != null ? picturePath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Puzzle{");
        sb.append("name='").append(name).append('\'');
        sb.append(", price=").append(price);
        sb.append(", difficultyLevel=").append(difficultyLevel);
        sb.append(", description='").append(description).append('\'');
        sb.append(", picturePath='").append(picturePath).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
