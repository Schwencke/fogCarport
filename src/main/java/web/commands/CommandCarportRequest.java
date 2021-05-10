package web.commands;

import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CommandCarportRequest extends CommandProtectedPage {
    Order order;
    protected OrderFacade orderFacade;

    public CommandCarportRequest(String pageToShow, String role) {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int carportWidth = Integer.parseInt(request.getParameter("carportwidth"));
        int carportLength = Integer.parseInt(request.getParameter("carportlength"));
        int claddingId = Integer.parseInt(request.getParameter("cladding"));
        int roofingId = Integer.parseInt(request.getParameter("roofing"));
        int shedWidth = Integer.parseInt(request.getParameter("shedwidth"));
        int shedLength = Integer.parseInt(request.getParameter("shedlength"));
        int userId = user.getUserId();
        if (carportWidth > 0) {
            if (carportLength > 0) {
                if ((shedWidth == 0 && shedLength == 0) || (shedWidth > 0 && shedLength > 0)) {
                    if (order == null) {
                        order = new Order();
                    }
                    order.setCarportLength(carportLength);
                    order.setCarportWidth(carportWidth);
                    order.setCladdingId(claddingId);
                    order.setRoofingId(roofingId);
                    order.setUserId(userId);
                    if (shedWidth != 0) {
                        order.setShedWidth(shedWidth);
                        order.setShedLength(shedLength);
                    }

                    orderFacade.createOrder(order);

                    request.setAttribute("msg", "Forespørgslen er sendt");
                } else {
                    request.setAttribute("error", "vælg begge mål til skur");
                }
            } else {
                request.setAttribute("error", "Mangler længde");
            }
        } else {
            request.setAttribute("error", "Mangler bredde");

        }
        return pageToShow;
    }
}
