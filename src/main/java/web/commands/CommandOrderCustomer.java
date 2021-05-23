package web.commands;

import business.entities.BoM;
import business.entities.Material;
import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.services.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandOrderCustomer extends CommandProtectedPage {
    protected OrderFacade orderFacade;
    protected double salesPrice;
    String[] materials;
    String svg;
    Order order;

    public CommandOrderCustomer(String pageToShow, String role) {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException, SQLException {
        HttpSession session = request.getSession();
        int orderId = Integer.parseInt(request.getParameter("order"));
        order = orderFacade.getOrderById(orderId);
        svg = orderFacade.getSVG(orderId);
        materials = orderFacade.getBOM(orderId);


        session.setAttribute("bommert", materials);
        session.setAttribute("order", order);
        session.setAttribute("svgcustomer", svg);

        pageToShow = "order";

        return pageToShow;
    }
}
