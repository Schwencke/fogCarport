package web.commands;

import business.entities.User;
import business.services.UserFacade;
import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class CommandLogin extends CommandUnprotectedPage {
    private UserFacade userFacade;

    public CommandLogin(String pageToShow) {
        super(pageToShow);
        userFacade = new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userFacade.login(email, password);

            HttpSession session = request.getSession();

            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());
            session.setAttribute("email", email);

            String pageToShow = user.getRole();

            if (pageToShow.equals("salesperson")) {
                pageToShow = "admin";
            }

            return REDIRECT_INDICATOR + pageToShow;
        } catch (UserException | SQLException ex) {
            request.setAttribute("error", "Wrong username or password!");
            return "login";
        }
    }
}
