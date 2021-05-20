package web.commands;


import business.entities.Order;
import business.exceptions.UserException;
import business.services.OrderFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public class CommandUpdateMeasurements extends CommandProtectedPage {
    protected OrderFacade orderFacade;
    Order order;
    int carportWidth;
    int carportLength;
    int shedLength;
    int shedWidth;
    int orderId;

    public CommandUpdateMeasurements(String pageToShow, String role) {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        orderId = Integer.parseInt(request.getParameter("orderid"));

        if (request.getParameter("carportLengthDropDown") != null) {
            carportLength = Integer.parseInt(request.getParameter("carportLengthDropDown"));
            carportWidth = Integer.parseInt(request.getParameter("carportWidthDropDown"));
            orderFacade.updateCarportMeasurementsById(orderId, carportLength, carportWidth);
        }
        if (request.getParameter("shedLengthDropDown") != null) {
            shedLength = Integer.parseInt(request.getParameter("shedLengthDropDown"));
            shedWidth = Integer.parseInt(request.getParameter("shedWidthDropDown"));
            orderFacade.updateShedMeasurementsById(orderId, shedLength, shedWidth);
        }
        order = orderFacade.getOrderById(orderId);
        session.setAttribute("order", order);

        return pageToShow;
    }
}
