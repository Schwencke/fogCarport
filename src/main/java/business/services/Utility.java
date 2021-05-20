package business.services;

import business.entities.Material;
import business.entities.Order;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Utility {

    @SafeVarargs
    public static double calcBasePrice(List<Material>... lists){
        DecimalFormat df = new DecimalFormat("#.##");
        double total = 0.0;
        List<Material> result = new ArrayList<>();
        Stream.of(lists).forEach(result::addAll);

        for (Material material : result) {
           total += material.getQuantity() * material.getPrice();
        }
        total %= total * 0.60;
        return Double.parseDouble(df.format(total));
    }

    public static double calcSalesPrice(double baseprice, double margin){
        DecimalFormat df = new DecimalFormat("#.##");
        margin = margin / 100;
        double salesprice = baseprice + (baseprice * margin);

        return Double.parseDouble(df.format(salesprice));
    }

    public static boolean validateEmailAddress(String email) {
        String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

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

    @SafeVarargs
    public static List<Material> concatenateLists(List<Material>... materials) {
        List<Material> result = new ArrayList<>();
        Stream.of(materials).forEach(result::addAll);
        return result;
    }

    public static double calcMarginPrice(double basePrice, double salesPrice) {
        DecimalFormat df = new DecimalFormat("#.##");
        double marginPrice;
        marginPrice = salesPrice - basePrice;
        return Double.parseDouble(df.format(marginPrice));
    }

    public static double calcVatPrice(double salesPrice) {
        DecimalFormat df = new DecimalFormat("#.##");
        double vatPrice = 0.0;
        vatPrice = salesPrice * 1.25;
        return Double.parseDouble(df.format(vatPrice));
    }
}
