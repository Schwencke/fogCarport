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
import java.util.List;
import java.util.Map;

public class CommandOrder extends CommandProtectedPage {

    protected OrderFacade orderFacade;
    protected UserFacade userFacade;
    protected MaterialFacade materialFacade;
    protected CarportCalc carportCalc;
    protected List<Material> materialList;
    Order order;
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
        carportWidth = order.getCarportWidth();
        carportLength = order.getCarportLength();
        materialList.add(carportCalc.calcBeam(carportWidth, carportLength));
        materialList.add(carportCalc.calcPost(carportWidth, carportLength));
        materialList.add(carportCalc.calcRafter(carportWidth, carportLength));

        session.setAttribute("materialeList", materialList);
        session.setAttribute("orderuser", orderUser);
        session.setAttribute("order", order);

        pageToShow = "order";

        return pageToShow;
    }
}
