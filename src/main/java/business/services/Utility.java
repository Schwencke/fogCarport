package business.services;

import business.entities.Material;
import business.entities.Order;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

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

    public static List<Material> concatenateLists(List<Material> sternUnderFrontAndBackList, List<Material> sternUnderSidesList, List<Material> sternOverFrontList, List<Material> sternOverSidesList, List<Material> sternWaterFrontList, List<Material> sternWaterSidesList){
        List<Material> result = new ArrayList<>();
        Stream.of(sternUnderFrontAndBackList,sternUnderSidesList,sternOverFrontList,sternOverSidesList,sternWaterFrontList, sternWaterSidesList).forEach(result::addAll);
        return result;
    }
}
