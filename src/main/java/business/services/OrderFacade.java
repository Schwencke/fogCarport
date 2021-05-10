package business.services;


import business.entities.Order;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderMapper;

import java.sql.SQLException;
import java.util.HashMap;
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

    public Order getOrderById(int orderId) throws UserException {
       return orderMapper.getOrderById(orderId);
    }

    public List<Order> getAllOrders() throws UserException {
        return orderMapper.getAllOrders();
    }

    public List<Order> getAllOrdersById(int userId) throws UserException {
        return orderMapper.getAllOrdersById(userId);
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

    public HashMap<Integer, String> getAllStatus() throws UserException {
        return orderMapper.getAllStatus();
    }

}
