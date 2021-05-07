package business.services;


import business.entities.Order;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


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

    public Map<String, List<Integer>> getPredefined() throws SQLException {
        return orderMapper.getPredefined();
    }

    public Map<Integer, Integer> getRoofing(){
        return orderMapper.getRoofing();

    }

    public Map<Integer, Integer> getCladding(){
        return orderMapper.getCladding();

    }

    public String getMaterialNameById(int id){
        return orderMapper.getMaterialNameById(id);
    }

}
