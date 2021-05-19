package web.commands;

import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.services.OrderFacade;
import business.services.Utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CommandCarportRequest extends CommandProtectedPage {
    protected OrderFacade orderFacade;
    Order order;
    List<Order> orderList;

    public CommandCarportRequest(String pageToShow, String role) {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        order = null;
        User user = (User) session.getAttribute("user");
        int carportWidth = Integer.parseInt(request.getParameter("carportwidth"));
        int carportLength = Integer.parseInt(request.getParameter("carportlength"));
        int claddingId = Integer.parseInt(request.getParameter("cladding"));
        int roofingId = Integer.parseInt(request.getParameter("roofing"));
        int shedWidth = Integer.parseInt(request.getParameter("shedwidth"));
        int shedLength = Integer.parseInt(request.getParameter("shedlength"));
        int userId = user.getUserId();

        pageToShow = "index";
        order = null;

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

                    // Create order
                    orderFacade.createOrder(order);

                    // Update orderlist
                    orderList = orderFacade.getAllOrdersById(user.getUserId());
                    session.setAttribute("orderlist", orderList);

                    // Output to user
                    request.setAttribute("msg", "Forespørgslen blev afsendt. Du vil blive kontaktet af en salgsmedarbejder hurtigst muligt.");

                    // Page to show
                    String role = Utility.getNameById(request, "roles", user.getRoleId());
                    pageToShow = role;
                } else {
                    request.setAttribute("error", "Skur mål mangler.");
                }
            } else {
                request.setAttribute("error", "Carport længde mangler.");
            }
        } else {
            request.setAttribute("error", "Carport bredde mangler.");
        }
        return pageToShow;
    }
}
