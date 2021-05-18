package web.commands;

import business.entities.User;
import business.services.UserFacade;
import business.exceptions.UserException;
import business.services.Utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class CommandLogin extends CommandUnprotectedPage {
    private UserFacade userFacade;
    String pageToShow;

    public CommandLogin(String pageToShow) {
        super(pageToShow);
        userFacade = new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
//TODO: FIX NU DET PIS MED AT MAN KAN LOGGE IND SELVOM MAN ER EN IDIOT
        try {
            User user = userFacade.login(email, password);

            String role = Utility.getNameById(request, "roles", user.getRoleId());
            String city = Utility.getNameById(request, "cities", user.getPostalCode());

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("email", email);
            session.setAttribute("role", role);
            session.setAttribute("city", city);

            if (session.getAttribute("front") != null) {
                pageToShow = "index";
            } else {
                pageToShow = role;
            }

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
