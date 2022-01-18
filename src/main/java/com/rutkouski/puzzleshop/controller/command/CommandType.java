package com.rutkouski.puzzleshop.controller.command;

import com.rutkouski.puzzleshop.controller.command.impl.general.ChangeLocaleCommand;
import com.rutkouski.puzzleshop.controller.command.impl.guest.GoToRegistrationCommand;
import com.rutkouski.puzzleshop.controller.command.impl.general.NonExistentCommand;
import com.rutkouski.puzzleshop.controller.command.impl.guest.RegistrationCommand;
import com.rutkouski.puzzleshop.controller.command.impl.guest.SignInCommand;
import com.rutkouski.puzzleshop.model.entity.User;

import java.util.EnumSet;

import static com.rutkouski.puzzleshop.model.entity.User.Role.*;

public enum CommandType {

    /* general commands */
//	LOG_OUT,
    NON_EXISTENT(new NonExistentCommand(), EnumSet.of(ADMIN, CUSTOMER, GUEST)),
    CHANGE_LOCALE(new ChangeLocaleCommand(), EnumSet.of(ADMIN, CUSTOMER, GUEST)),

    /* guest commands */
    GO_TO_REGISTRATION(new GoToRegistrationCommand(), EnumSet.of(GUEST)),
	REGISTRATION(new RegistrationCommand(), EnumSet.of(GUEST)),
    SIGN_IN(new SignInCommand(), EnumSet.of(GUEST));

    /* customer commands */

    //CHANGE_PERSONAL_DATA_COMMAND


    /* admin commands */


    private Command command;
    private EnumSet<User.Role> allowedRoles;

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
