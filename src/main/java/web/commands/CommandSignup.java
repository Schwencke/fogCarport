package web.commands;

import business.entities.User;
import business.services.UserFacade;
import business.exceptions.UserException;
import business.services.Utility;

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
        int postalCode = Integer.parseInt(request.getParameter("postalcode"));
        String phoneNo = request.getParameter("phoneno");
        String email = request.getParameter("email");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        if (Utility.validateEmailAddress(email)) {
            if (password1.equals(password2)) {
                User user = userFacade.createUser(name, address, postalCode, phoneNo, email, password1);

                String role = Utility.getNameById(request, "roles", user.getRoleId());
                String city = Utility.getNameById(request, "cities", user.getPostalCode());

                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                session.setAttribute("user", user);
                session.setAttribute("role", role);
                session.setAttribute("city", city);

                return "customer";
            } else {
                request.setAttribute("error", "The two passwords did not match.");
                return "signup";
            }
        } else {
            request.setAttribute("error", "Invalid email address.");
            return "signup";
        }
    }
}
