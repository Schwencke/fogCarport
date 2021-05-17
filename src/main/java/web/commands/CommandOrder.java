package web.commands;

import business.entities.Material;
import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.services.CarportCalc;
import business.services.MaterialFacade;
import business.services.OrderFacade;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandOrder extends CommandProtectedPage {

    protected OrderFacade orderFacade;
    protected UserFacade userFacade;
    protected MaterialFacade materialFacade;
    protected CarportCalc carportCalc;
    protected List<Material> postList;
    protected List<Material> rafterList;
    protected List<Material> sternList;
    protected List<Material> beamList;
    Order order;
    Material material;
    int carportWidth;
    int carportLength;
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
        carportWidth = (int) (order.getCarportWidth() / 0.1);
        carportLength = (int) (order.getCarportLength() / 0.1);

        postList = carportCalc.calcPost(carportWidth,carportLength);
        rafterList = carportCalc.calcRafter(carportWidth,carportLength);
        //sternList = carportCalc.calcStern(carportWidth,carportLength);
        beamList = carportCalc.calcBeam(carportWidth,carportLength);




        session.setAttribute("postList", postList);
        session.setAttribute("rafterList", rafterList);
        session.setAttribute("beamList", beamList);
        session.setAttribute("orderuser", orderUser);
        session.setAttribute("order", order);

        pageToShow = "order";

        return pageToShow;
    }
}
