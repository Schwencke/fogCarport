package business.services;

import business.entities.Order;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

public class Utility {

    public static String getNameById(HttpServletRequest request, String name, int id) {
        ServletContext application = request.getServletContext();
        HashMap<Integer, String> names = (HashMap<Integer, String>) application.getAttribute(name);
        return names.get(id);
    }

    public static void updateSessionScopeOrderList(HttpServletRequest request, List<Order> orderList) {
        HttpSession session = request.getSession();
        if (orderList.size() > 0) {
            for (Order order : orderList) {
                if (order.getStatusId() != 5) {
                    session.setAttribute("orderlist", orderList);
                }
            }
        }
    }
}
