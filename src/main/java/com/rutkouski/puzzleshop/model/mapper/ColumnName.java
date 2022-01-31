package com.rutkouski.puzzleshop.model.mapper;

import java.math.BigDecimal;

public final class ColumnName {

    /* users table */
    public static final String USER_ID = "users.id";
    public static final String LOGIN = "users.login";
    public static final String PASSWORD = "users.password";
    public static final String EMAIL = "users.email";
    public static final String FIRST_NAME = "users.first_name";
    public static final String PHONE = "users.phone";
    /* roles table */
    public static final String USER_ROLE = "roles.role";
    /* user_statuses table */
    public static final String USER_STATUS = "user_statuses.status";
    /* puzzles table */
    public static final String PUZZLE_ID = "puzzles.id";
    public static final String PUZZLE_NAME = "puzzles.name";
    public static final String PRICE = "puzzles.price";
    public static final String DIFFICULTY_LEVEL = "puzzles.difficulty_level";
    public static final String DESCRIPTION = "puzzles.description";
    public static final String IMAGE = "puzzles.image";
    /* customers table */
    public static final String CUSTOMER_ID = "customers.id";
    public static final String DISCOUNT_VALUE = "customers.discount";
    /* orders table */
    public static final String ORDER_ID = "orders.id";
    public static final String ORDER_DATE = "orders.order_date";
    public static final String TOTAL_PRICE = "orders.total_price";
    public static final String ORDER_CUSTOMER_ID = "orders.customer_id";
    /* order_statuses table */
    public static final String ORDER_STATUS = "order_statuses.status";
    /* order_statuses table */
    public static final String ORDER_ITEM_ID = "order_items.id";
    public static final String ITEM_QUANTITY = "order_items.item_quantity";
    public static final String ITEM_ID = "order_items.puzzle_id";
    public static final String ORDER_ITEMS_ORDER_ID = "order_items.order_id";

    private ColumnName() {
    }
}
