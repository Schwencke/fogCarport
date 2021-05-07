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

    public void createOrder(Order order) throws UserException{
       orderMapper.createOrder(order);
    }

    public Order getOrderById(int orderNo) throws UserException {
       return orderMapper.getOrder(orderNo);
    }
}
