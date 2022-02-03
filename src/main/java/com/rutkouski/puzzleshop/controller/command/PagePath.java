package com.rutkouski.puzzleshop.controller.command;

/**
 * Class provides string constants of page paths for redirect and forward route types
 */

public final class PagePath {

    public static final String INDEX_PAGE = "/index.jsp";
    public static final String MAIN_PAGE = "/jsps/main.jsp";
    public static final String ABOUT_US_PAGE = "/jsps/about_us.jsp";
    public static final String SHOW_ALL_GOODS_PAGE = "controller?command=show_all_goods";
    public static final String REGISTRATION_PAGE = "/jsps/registration.jsp";
    public static final String ORDER_INFO_PAGE = "/jsps/order_info.jsp";
    public static final String GO_TO_PROFILE_PAGE = "/jsps/user_profile.jsp";
    public static final String UPDATE_PASSWORD_PAGE = "/jsps/update_password.jsp";
    public static final String UPDATE_PROFILE_PAGE = "/jsps/update_profile.jsp";

    /* error pages */
    public static final String ERROR_404_PAGE = "jsps/error/error404.jsp";
    public static final String ERROR_403_PAGE = "jsps/error/error403.jsp";
    public static final String ERROR_500_PAGE = "jsps/error/error500.jsp";

    /* customer pages */
    public static final String GOODS_PAGE = "/jsps/goods.jsp";
    public static final String BASKET_PAGE = "/jsps/customer/basket.jsp";
    public static final String CREATED_ORDER_RESULT_PAGE = "/jsps/customer/created_order_result.jsp";
    public static final String CUSTOMER_ORDERS_LIST_PAGE = "/jsps/customer/orders_list.jsp";
    public static final String SHOW_ORDERS_FOR_CUSTOMER_PAGE = "controller?command=show_orders_for_customer";

    /* admin pages */
    public static final String USER_MANAGEMENT_PAGE = "/jsps/admin/user_management.jsp";
    public static final String ORDER_MANAGEMENT_PAGE = "/jsps/admin/order_management.jsp";
    public static final String EDIT_PUZZLE_PAGE = "/jsps/admin/edit_puzzle.jsp";
    public static final String GO_TO_EDIT_PUZZLE_PAGE = "controller?command=go_to_edit_puzzle&puzzle_id=";
    public static final String GO_TO_ADD_PUZZLE_PAGE = "jsps/admin/add_puzzle.jsp";
    public static final String TO_ORDER_MANAGEMENT_PAGE = "/controller?command=order_management";
    public static final String TO_USER_MANAGEMENT_PAGE = "/controller?command=user_management";

    private PagePath() {
    }
}
