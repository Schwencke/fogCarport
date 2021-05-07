package web.commands;

import business.entities.Order;
import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CommandCarportRequest extends CommandProtectedPage{
    Order order;
    protected OrderFacade orderFacade;

    public CommandCarportRequest(String pageToShow, String role) {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

       int carportLength = Integer.parseInt(request.getParameter("carportlength"));
       int carportWidth = Integer.parseInt(request.getParameter("carportwidth"));
       int claddingId = Integer.parseInt(request.getParameter("claddingid"));
       int roofingId = Integer.parseInt(request.getParameter("roofingid"));
       int shedWidth = Integer.parseInt(request.getParameter("shedwidth"));
       int shedLength = Integer.parseInt(request.getParameter("shedlength"));

    if(order == null){
        order = new Order();
    }
    order.setCarportLength(carportLength);
    order.setCarportWidth(carportWidth);
    order.setCladdingId(claddingId);
    order.setRoofingId(roofingId);
    if(shedWidth != 0){
        order.setShedWidth(shedWidth);
        order.setShedLength(shedLength);
    }

    orderFacade.createOrder(order);

        request.setAttribute("msg", "Forespørgslen er sendt");
        return pageToShow;
    }
}
