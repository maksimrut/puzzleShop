package com.rutkouski.puzzleshop.controller.command;

import com.rutkouski.puzzleshop.controller.command.impl.admin.*;
import com.rutkouski.puzzleshop.controller.command.impl.customer.*;
import com.rutkouski.puzzleshop.controller.command.impl.general.*;
import com.rutkouski.puzzleshop.controller.command.impl.guest.GoToRegistrationCommand;
import com.rutkouski.puzzleshop.controller.command.impl.guest.RegistrationCommand;
import com.rutkouski.puzzleshop.controller.command.impl.guest.SignInCommand;
import com.rutkouski.puzzleshop.model.entity.User;

import java.util.EnumSet;

import static com.rutkouski.puzzleshop.model.entity.User.Role.*;

public enum CommandType {

    /* general commands */
    NON_EXISTENT(new NonExistentCommand(), EnumSet.of(ADMIN, CUSTOMER, GUEST)),
    CHANGE_LOCALE(new ChangeLocaleCommand(), EnumSet.of(ADMIN, CUSTOMER, GUEST)),
    GO_TO_MAIN(new GoToMainCommand(), EnumSet.of(ADMIN, CUSTOMER, GUEST)),
    LOG_OUT(new LogOutCommand(), EnumSet.of(ADMIN, CUSTOMER)),
    SHOW_ALL_GOODS(new ShowAllGoodsCommand(), EnumSet.of(ADMIN, CUSTOMER, GUEST)),
    GO_TO_ABOUT_US(new GoToAboutUsCommand(), EnumSet.of(ADMIN, CUSTOMER, GUEST)),
    SHOW_PUZZLES_BY_DIFFICULTY_LEVEL(new ShowPuzzlesByDifficultyLevelCommand(), EnumSet.of(ADMIN, CUSTOMER, GUEST)),

    /* guest commands */
    GO_TO_REGISTRATION(new GoToRegistrationCommand(), EnumSet.of(GUEST)),
	REGISTRATION(new RegistrationCommand(), EnumSet.of(GUEST)),
    SIGN_IN(new SignInCommand(), EnumSet.of(GUEST)),

    /* customer commands */
    SHOW_BASKET(new ShowBasketCommand(), EnumSet.of(CUSTOMER)),
    RECOUNT_ORDER_WHILE_ADDING_ITEM(new RecountOrderWhileAddingItemCommand(), EnumSet.of(CUSTOMER)),
    RECOUNT_ORDER_WHILE_REMOVING_ITEM(new RecountOrderWhileRemovingItemCommand(), EnumSet.of(CUSTOMER)),
    ADD_ITEM_TO_BASKET(new AddItemToBasketCommand(), EnumSet.of(CUSTOMER)),
    CREATE_ORDER(new CreateOrderCommand(), EnumSet.of(CUSTOMER)),
    SHOW_ORDERS_FOR_CUSTOMER(new ShowOrdersForCustomerCommand(), EnumSet.of(CUSTOMER)),

    /* admin commands */
    GO_TO_EDIT_PUZZLE(new GoToEditPuzzleCommand(), EnumSet.of(ADMIN)),
    EDIT_PUZZLE(new EditPuzzleCommand(), EnumSet.of(ADMIN)),
    USER_MANAGEMENT(new UserManagementCommand(), EnumSet.of(ADMIN)),
    ADD_PUZZLE(new AddPuzzleCommand(), EnumSet.of(ADMIN)),
    GO_TO_ADD_PUZZLE(new GoToAddPuzzleCommand(), EnumSet.of(ADMIN)),
    DELETE_PUZZLE(new DeletePuzzleCommand(), EnumSet.of(ADMIN));

    private final Command command;
    private final EnumSet<User.Role> allowedRoles;

    CommandType(Command command, EnumSet<User.Role> allowedRoles) {
        this.command = command;
        this.allowedRoles = allowedRoles;
    }

    public Command getCurrentCommand() {
        return command;
    }

    public EnumSet<User.Role> getAllowedRoles() {
        return allowedRoles;
    }
}
