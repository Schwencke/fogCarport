package web;

//<editor-fold desc="Imports">
import business.exceptions.UserException;
import business.persistence.Database;
import business.services.MaterialFacade;
import business.services.OrderFacade;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import business.services.UserFacade;
import web.commands.Command;
import web.commands.CommandUnknown;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
//</editor-fold>

@WebServlet(name = "FrontController", urlPatterns = {"/fc/*"})

public class FrontController extends HttpServlet
{

    private final static String USER = "root";
    private final static String PASSWORD = "1234";
    private final static String URL = "jdbc:mysql://localhost:3306/carport?serverTimezone=CET";

    public static Database database;

    public void init() throws ServletException {

        // Initialize database connection
        if (database == null) {
            try {
                database = new Database(USER, PASSWORD, URL);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger("web").log(Level.SEVERE, ex.getMessage(), ex);
            }
        }

        // Initialize global datastructures here:
        ServletContext application = getServletContext();

        OrderFacade orderFacade = new OrderFacade(database);
        Map<String, List<Integer>> predefined = new HashMap<>();
        try {
            predefined = orderFacade.getPredefined();
        } catch (UserException throwables) {
            throwables.printStackTrace();
        }
        List<Integer> predefinedCarportWidth = new ArrayList<>(predefined.get("carportWidth"));
        List<Integer> predefinedCarportLength = new ArrayList<>(predefined.get("carportLength"));
        List<Integer> predefinedShedWidth = new ArrayList<>(predefined.get("shedWidth"));
        List<Integer> predefinedShedLength = new ArrayList<>(predefined.get("shedLength"));

        application.setAttribute("carportWidth", predefinedCarportWidth);
        application.setAttribute("carportLength", predefinedCarportLength);
        application.setAttribute("shedWidth", predefinedShedWidth);
        application.setAttribute("shedLength", predefinedShedLength);

        UserFacade userFacade = new UserFacade(database);
        HashMap<Integer, String> roles;
        try {
            roles = userFacade.getAllRoles();
        } catch (UserException ex) {
            throw new ServletException(ex.getMessage());
        }
        application.setAttribute("roles", roles);

        HashMap<Integer, String> status;
        try {
            status = orderFacade.getAllStatus();
        } catch (UserException ex) {
            throw new ServletException(ex.getMessage());
        }
        application.setAttribute("status", status);

        HashMap<Integer, String> cities;
        try {
            cities = userFacade.getAllCities();
        } catch (UserException ex) {
            throw new ServletException(ex.getMessage());
        }
        application.setAttribute("cities", cities);

        MaterialFacade materialFacade = new MaterialFacade(database);
        HashMap<Integer, String> units;
        try {
            units = materialFacade.getAllUnits();
        } catch (UserException ex) {
            throw new ServletException(ex.getMessage());
        }
        application.setAttribute("units", units);

        HashMap<Integer, Integer> roofing = null;
        try {
            roofing = (HashMap<Integer, Integer>) orderFacade.getRoofing();
        } catch (UserException e) {
            e.printStackTrace();
        }
        application.setAttribute("roofing", roofing);

        HashMap<Integer, Integer> cladding = null;
        try {
            cladding = (HashMap<Integer, Integer>) orderFacade.getCladding();
        } catch (UserException e) {
            e.printStackTrace();
        }
        application.setAttribute("cladding", cladding);

        HashMap<Integer, String> clads = new HashMap<>();
        for (Integer value : cladding.values()) {
            for (Integer integer : cladding.keySet()) {
                try {
                    clads.put(integer, orderFacade.getMaterialNameById(value));
                } catch (UserException ex) {
                    throw new ServletException(ex.getMessage());
                }
            }
        }
        application.setAttribute("claddinglist", clads);

        HashMap<Integer, String> roofs = new HashMap<>();
        for (Integer value : roofing.values()) {
            for (Integer integer : cladding.keySet()) {
                try {
                    roofs.put(integer, orderFacade.getMaterialNameById(value));
                } catch (UserException ex) {
                    throw new ServletException(ex.getMessage());
                }
            }
        }
        application.setAttribute("roofinglist", roofs);
    }

    protected void processRequest(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            Command action = Command.fromPath(request, database);

            if (action instanceof CommandUnknown) {
                response.sendError(404);
                return;
            }

            String view = action.execute(request, response);

            if (view.startsWith(Command.REDIRECT_INDICATOR)) {
                String page = view.substring(Command.REDIRECT_INDICATOR.length());
                response.sendRedirect(page);
                return;
            }

            request.getRequestDispatcher("/WEB-INF/" + view + ".jsp").forward(request, response);
        } catch (UnsupportedEncodingException | UserException | SQLException ex) {
            request.setAttribute("problem", ex.getMessage());
            Logger.getLogger("web").log(Level.SEVERE, ex.getMessage(), ex);
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "FrontController for application";
    }
}
