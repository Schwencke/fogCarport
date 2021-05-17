package web.commands;


import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CommandUpdateStatus extends CommandProtectedPage {
    protected OrderFacade orderFacade;
    int orderId;
    int statusId;

    public CommandUpdateStatus(String pageToShow, String role) {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        orderId = Integer.parseInt(request.getParameter("orderid"));
        statusId = Integer.parseInt(request.getParameter("statusid"));
        orderFacade.updateStatusById(statusId, orderId);
        return pageToShow;
    }
}
