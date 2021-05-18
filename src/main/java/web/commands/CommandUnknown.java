package web.commands;

import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandUnknown extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        String msg = "Ukendt kommando. Kontakt venligst IT.";
        throw new UserException(msg);
    }
}
