package web.commands;


import business.entities.Order;
import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public class CommandUpdateStatus extends CommandProtectedPage {
    protected OrderFacade orderFacade;
    List<Order> orderList;
    int orderId;
    int statusId;

    public CommandUpdateStatus(String pageToShow, String role) {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        orderId = Integer.parseInt(request.getParameter("orderid"));
        statusId = Integer.parseInt(request.getParameter("statusid"));
        orderFacade.updateStatusById(statusId, orderId);
        orderList = orderFacade.getAllOrders();
        session.setAttribute("orderlist", orderList);
        return pageToShow;
    }
}
