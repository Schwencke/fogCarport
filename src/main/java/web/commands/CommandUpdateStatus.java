package web.commands;


import business.entities.Order;
import business.exceptions.UserException;
import business.services.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static business.services.Utility.updateSessionScopeOrderList;


public class CommandUpdateStatus extends CommandProtectedPage {
    protected OrderFacade orderFacade;
    protected double ordrePrice;
    List<Order> orderList;

    public CommandUpdateStatus(String pageToShow, String role) {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        HttpSession session = request.getSession();

        int orderId = Integer.parseInt(request.getParameter("orderid"));
        int statusId = Integer.parseInt(request.getParameter("statusid"));
        if (statusId == 2){
            ordrePrice = Double.parseDouble((request.getParameter("orderprice")));
            orderFacade.updateOrderPrice(orderId, ordrePrice);
        }
        orderFacade.updateStatusById(statusId, orderId);

        session.removeAttribute("orderlist");
        orderList = orderFacade.getAllOrders();

        updateSessionScopeOrderList(request, orderList);

        return pageToShow;
    }
}
