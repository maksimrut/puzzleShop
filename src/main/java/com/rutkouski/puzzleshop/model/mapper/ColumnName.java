package com.rutkouski.puzzleshop.model.mapper;

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


    //TODO
    public static final String REGISTERED = "users.registered";
    /* orders table */
    public static final String ORDER_ID = "orders.id";
    public static final String ORDER_CREATED = "orders.created";
    public static final String ORDER_CASH = "orders.cash";
    public static final String ORDER_USER_ID = "orders.user_id";
    public static final String DELIVERY_TIME = "orders.delivery_time";
    /* order_statuses table */
    public static final String ORDER_STATUS = "order_statuses.status";
    /* orders_have_meals table */
    public static final String ORDER_QUANTITY = "orders_have_meals.quantity";

    private ColumnName() {
    }
}
