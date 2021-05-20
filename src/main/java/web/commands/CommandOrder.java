package web.commands;

import business.entities.Material;
import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.services.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class CommandOrder extends CommandProtectedPage {

    protected OrderFacade orderFacade;
    protected UserFacade userFacade;
    protected MaterialFacade materialFacade;
    protected CarportCalc carportCalc;
    protected List<Material> postList;
    protected List<Material> rafterList;
    protected List<Material> sternUnderFrontAndBackList;
    protected List<Material> sternUnderSidesList;
    protected List<Material> sternOverFrontList;
    protected List<Material> sternOverSidesList;
    protected List<Material> sternWaterFrontList;
    protected List<Material> sternWaterSidesList;
    protected List<Material> sternList;
    protected List<Material> beamList;
    protected List<Material> roofList;
    Order order;
    User orderUser;

    public CommandOrder(String pageToShow, String role) {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
        this.userFacade = new UserFacade(database);
        this.materialFacade = new MaterialFacade(database);
        this.carportCalc = new CarportCalc(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException, SQLException {

        HttpSession session = request.getSession();

        int orderId = Integer.parseInt(request.getParameter("order"));
        order = orderFacade.getOrderById(orderId);
        orderUser = userFacade.getUserById(order.getUserId());
        int carportWidth = order.getCarportWidth() * 10;
        int carportLength = order.getCarportLength() * 10;

        postList = carportCalc.calcPost(carportWidth, carportLength);
        rafterList = carportCalc.calcRafter(carportWidth, carportLength);
        sternUnderFrontAndBackList = carportCalc.calcSternUnderFrontAndBack(carportWidth);
        sternUnderSidesList = carportCalc.calcSternUnderSides(carportLength);
        sternOverFrontList = carportCalc.calcSternOverFront(carportWidth);
        sternOverSidesList = carportCalc.calcSternOverSides(carportLength);
        sternWaterFrontList = carportCalc.calcSternWaterFront(carportWidth);
        sternWaterSidesList = carportCalc.calcSternWaterSides(carportLength);
        beamList = carportCalc.calcBeam(carportWidth, carportLength);
        roofList = carportCalc.calcRoofing(carportWidth, carportLength);

        sternList = Utility.concatenateLists(sternUnderFrontAndBackList, sternUnderSidesList, sternOverFrontList, sternOverSidesList, sternWaterFrontList, sternWaterSidesList);

        session.setAttribute("sternList", sternList);
        session.setAttribute("postList", postList);
        session.setAttribute("rafterlist", rafterList);
        session.setAttribute("beamlist", beamList);
        session.setAttribute("rooflist", roofList);
        session.setAttribute("orderuser", orderUser);
        session.setAttribute("order", order);

        pageToShow = "order";

        return pageToShow;
    }
}
