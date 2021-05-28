package web.commands;

import business.entities.User;
import business.services.UserFacade;
import business.exceptions.UserException;
import business.services.Utility;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Application;
import java.util.HashMap;

public class CommandSignup extends CommandUnprotectedPage {
    private UserFacade userFacade;

    public CommandSignup(String pageToShow) {
        super(pageToShow);
        userFacade = new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        // Name
        String name = request.getParameter("name");
        if (name.equals("")) {
            request.setAttribute("error", "Navn mangler.");
            return "signup";
        }

        // Address
        String address = request.getParameter("address");
        if (address.equals("")) {
            request.setAttribute("error", "Adresse mangler.");
            return "signup";
        }

        // Postal code
        if (request.getParameter("postalcode").length() < 1) {
            request.setAttribute("error", "Postnummeret mangler.");
            return "signup";
        }

        int postalCode;
        try {
            postalCode = Integer.parseInt(request.getParameter("postalcode"));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Ugyldigt postnummer.");
            return "signup";
        }

        int digitsCount = (int) (Math.log10(postalCode) + 1);
        if (digitsCount != 4) {
            request.setAttribute("error", "Ugyldigt postnummer.");
            return "signup";
        }

        ServletContext application = request.getServletContext();
        HashMap<Integer, String> cities;
        cities = (HashMap<Integer, String>) application.getAttribute("cities");
        if (!cities.containsKey(postalCode)){
            request.setAttribute("error", "Ugyldigt postnummer. Du kan bruge følgende; " + cities);
            return "signup";
        }

        // Phone No.
        String phoneNo = request.getParameter("phoneno");
        if (phoneNo.length() < 1) {
            request.setAttribute("error", "Telefonnummer mangler.");
            return "signup";
        }

        // Email
        String email = request.getParameter("email");
        if (email.length() < 1) {
            request.setAttribute("error", "Email mangler.");
            return "signup";
        }

        if (Utility.validateEmailAddress(email) == false) {
            request.setAttribute("error", "Ugyldig email.");
            return "signup";
        }

        // Password
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        if (password1.equals("") || password2.equals("")) {
            request.setAttribute("error", "Password mangler.");
            return "signup";
        }

        // Create user
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
            request.setAttribute("error", "De indtastede password er ikke ens.");
            return "signup";
        }
    }
}
