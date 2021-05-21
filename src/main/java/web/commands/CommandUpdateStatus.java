package web.commands;


import business.entities.Order;
import business.exceptions.UserException;
import business.services.CarportCalc;
import business.services.MaterialFacade;
import business.services.OrderFacade;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static business.services.Utility.updateSessionScopeOrderList;


public class CommandUpdateStatus extends CommandProtectedPage {
    protected OrderFacade orderFacade;

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
        orderFacade.updateStatusById(statusId, orderId);

        session.removeAttribute("orderlist");
        orderList = orderFacade.getAllOrders();

        updateSessionScopeOrderList(request, orderList);

        return pageToShow;
    }
}
