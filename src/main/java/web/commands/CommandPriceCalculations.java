package web.commands;

import business.entities.BoM;
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

public class CommandPriceCalculations extends CommandProtectedPage {

    protected OrderFacade orderFacade;
    protected UserFacade userFacade;
    protected MaterialFacade materialFacade;
    protected CarportCalc carportCalc;
    protected BoM billOfMaterials;
    protected double basePrice;
    protected double salesPrice;
    protected double marginPrice;
    protected double vatPrice;
    protected SVG svg;
    protected double margin;

    public CommandPriceCalculations(String pageToShow, String role) {
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
        billOfMaterials = (BoM) session.getAttribute("bom");
        basePrice = (double)session.getAttribute("baseprice");
        if (request.getParameter("margin") != null) {
            margin = Double.parseDouble(request.getParameter("margin"));
            billOfMaterials.setMargin(margin);
        } else { margin = billOfMaterials.getMargin();}

        salesPrice = Utility.calcSalesPrice(basePrice, margin);
        marginPrice = Utility.calcMarginPrice(basePrice,salesPrice);
        vatPrice = Utility.calcVatPrice(salesPrice);


        svg = new SVG(1, 80, "0 0 1000 880", 0, 0);
        svg.SVGDefs();
        svg.SVGNest(100, 100, "0 0 1000 1", 0, 0);
        svg.drawRoof(request);
        svg.drawBeam(request);
        svg.drawRafter(request);
        svg.drawPost(request);
        svg.SVGClose();

        orderFacade.createSVG(orderId, svg);



        session.setAttribute("svgdrawing", svg.toString());
        session.setAttribute("salesprice", salesPrice);
        session.setAttribute("marginprice", marginPrice);
        session.setAttribute("vatprice", vatPrice);

        pageToShow = "order";

        return pageToShow;
    }
}
