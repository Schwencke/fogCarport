package business.services;


import business.entities.Order;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderMapper;

import java.sql.SQLException;

public class OrderFacade {
    OrderMapper orderMapper;

    public OrderFacade(Database database) {
        orderMapper = new OrderMapper(database);
    }

    public Order createOrder(int userId, int carportLength, int carportWidth, int claddingId, int roofingId, int shedWidth, int shedLength) throws UserException, SQLException {
        Order order = new Order(userId, carportLength, carportWidth, claddingId, roofingId, shedWidth, shedLength);
        orderMapper.createOrder(order);
        return order;
    }

    public Order getOrderById(int orderNo) throws UserException {
       return orderMapper.getOrder(orderNo);

    }
}
