package web.commands;

import business.entities.Order;
import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CommandOrder extends CommandProtectedPage {

    protected OrderFacade orderFacade;
    Order order;

    public CommandOrder(String pageToShow, String role) {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        HttpSession session = request.getSession();

        int orderId = Integer.parseInt(request.getParameter("order"));
        order = orderFacade.getOrderById(orderId);

        session.setAttribute("order", order);

        pageToShow = "order";

        return pageToShow;
    }
}
