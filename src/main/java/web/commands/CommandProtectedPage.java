package web.commands;

import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class CommandProtectedPage extends Command {
    public String role;
    public String pageToShow;

    public CommandProtectedPage(String pageToShow, String role) {
        this.pageToShow = pageToShow;
        this.role = role;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException, SQLException {
        return pageToShow;
    }

    public String getRole() {
        return role;
    }
}
