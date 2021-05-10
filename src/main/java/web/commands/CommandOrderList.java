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

public class CommandOrderList extends CommandProtectedPage {

    protected OrderFacade orderFacade;
    List<Order> orderList;

    public CommandOrderList(String pageToShow, String role) {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        String role = Utility.getNameById(request, "roles", user.getRoleId());
        pageToShow = role;

        if (pageToShow.equals("salesperson")) {
            orderList = orderFacade.getAllOrders();
            pageToShow = "admin";
        } else {
            orderList = orderFacade.getAllOrdersById(user.getUserId());
        }

        session.setAttribute("orderlist", orderList);

        return pageToShow;
    }
}
