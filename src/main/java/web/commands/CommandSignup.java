package web.commands;

import business.entities.User;
import business.persistence.Database;
import business.services.UserFacade;
import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CommandSignup extends CommandUnprotectedPage {
    private UserFacade userFacade;

    public CommandSignup(String pageToShow) {
        super(pageToShow);
        userFacade = new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        int zipCode = Integer.parseInt(request.getParameter("zipcode"));
        String phoneNo = request.getParameter("phoneno");
        String email = request.getParameter("email");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        if (password1.equals(password2)) {
            User user = userFacade.createUser(name, address, zipCode, phoneNo, email, password1);
            HttpSession session = request.getSession();

            user.setRole("customer");

            session.setAttribute("email", email);
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());
            return user.getRole();
        } else {
            request.setAttribute("error", "the two passwords did not match");
            return "signup";
        }
    }
}
