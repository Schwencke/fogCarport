package web.commands;

import business.entities.BoM;
import business.entities.Material;
import business.entities.Order;
import business.exceptions.UserException;
import business.services.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CommandUpdateMeasurements extends CommandProtectedPage {
    protected OrderFacade orderFacade;
    protected UserFacade userFacade;
    protected MaterialFacade materialFacade;
    Order order;
    int carportWidth;
    int carportLength;
    int shedLength;
    int shedWidth;
    int orderId;
    protected CarportCalc carportCalc;
    protected List<Material> sternUnderFrontAndBackList;
    protected List<Material> sternUnderSidesList;
    protected List<Material> sternOverFrontList;
    protected List<Material> sternOverSidesList;
    protected List<Material> sternWaterFrontList;
    protected List<Material> sternWaterSidesList;
    protected List<Material> sternList;
    protected List<Material> postList;
    protected List<Material> rafterList;
    protected List<Material> beamList;
    protected List<Material> roofList;
    protected double basePrice;
    protected double salesPrice;
    protected double marginPrice;
    protected double vatPrice;
    protected BoM billOfMaterials;

    public CommandUpdateMeasurements(String pageToShow, String role) {
        super(pageToShow, role);
        this.orderFacade = new OrderFacade(database);
        this.userFacade = new UserFacade(database);
        this.materialFacade = new MaterialFacade(database);
        this.carportCalc = new CarportCalc(database);
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

        sternList = Utility.concatenateLists(sternUnderFrontAndBackList, sternUnderSidesList, sternOverFrontList, sternOverSidesList, sternWaterFrontList, sternWaterSidesList);
        beamList = carportCalc.calcBeam(carportWidth, carportLength);
        roofList = carportCalc.calcRoofing(carportWidth, carportLength);
        basePrice = Utility.calcBasePrice(postList, rafterList, sternList, beamList);

        if (billOfMaterials == null) {
            billOfMaterials = new BoM();
        }
        billOfMaterials.setMaterials(Utility.concatenateLists(postList, rafterList, sternList, beamList));
        billOfMaterials.setBasePrice(basePrice);

        salesPrice = Utility.calcSalesPrice(basePrice, billOfMaterials.getMargin());
        vatPrice = Utility.calcVatPrice(salesPrice);
        marginPrice = Utility.calcMarginPrice(basePrice, salesPrice);

        session.setAttribute("marginprice", marginPrice);
        session.setAttribute("vatprice", vatPrice);
        session.setAttribute("salesprice", salesPrice);
        session.setAttribute("baseprice", basePrice);
        session.setAttribute("bom", billOfMaterials);

        session.setAttribute("order", order);


        return pageToShow;
    }
}
