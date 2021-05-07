package web.commands;

import business.entities.Role;
import business.entities.User;
import business.services.UserFacade;
import business.exceptions.UserException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class CommandLogin extends CommandUnprotectedPage {
    private UserFacade userFacade;
    List<Role> roleList;

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

            ServletContext application = request.getServletContext();
            roleList = (List<Role>) application.getAttribute("rolelist");
            String role = roleList.get(user.getRoleId()-1).getName();

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("email", email);
            session.setAttribute("role", role);

            String pageToShow = role;

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
