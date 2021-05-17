package web.commands;


import business.exceptions.UserException;
import business.services.OrderFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CommandUpdateMeasurements extends CommandProtectedPage {
    protected OrderFacade orderFacade;
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

        orderId = Integer.parseInt(request.getParameter("orderid"));

        carportLength = Integer.parseInt(request.getParameter("carportLength"));
        carportWidth = Integer.parseInt(request.getParameter("carportWidth"));
        orderFacade.updateCarportMeasurementsById(orderId,carportLength,carportWidth);

        shedLength = Integer.parseInt(request.getParameter("shedLength"));
        shedWidth = Integer.parseInt(request.getParameter("shedWidth"));

        orderFacade.updateShedMeasurementsById(orderId, shedLength, shedWidth);

        return pageToShow;
    }
}
