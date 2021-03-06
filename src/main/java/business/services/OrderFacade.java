package business.services;

import business.entities.BoM;
import business.entities.Order;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderMapper;

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

    public Map<String, List<Integer>> getPredefined() throws UserException {
        return orderMapper.getPredefined();
    }

    public Map<Integer, Integer> getRoofing() throws UserException {
        return orderMapper.getRoofing();
    }

    public Map<Integer, Integer> getCladding() throws UserException {
        return orderMapper.getCladding();
    }

    public String getMaterialNameById(int id) throws UserException {
        return orderMapper.getMaterialNameById(id);
    }

    public HashMap<Integer, String> getAllStatus() throws UserException {
        return orderMapper.getAllStatus();
    }

    public void updateCarportMeasurementsById(int orderId, int carportLength, int carportWidth) throws UserException {
        orderMapper.updateCarportMeasurementsById(orderId, carportLength, carportWidth);
    }

    public void updateShedMeasurementsById(int orderId, int shedLength, int shedWidth) throws UserException {
        orderMapper.updateShedMeasurementsById(orderId, shedLength, shedWidth);
    }

    public void updateStatusById(int statusId, int orderId){
        orderMapper.updateStatusById(statusId, orderId);
    }

    public void updateOrderPrice(int orderId, double orderPrice) {
        orderMapper.updateOrderPrice(orderId, orderPrice);
    }

    public void createSVG(int orderId, SVG svg){
        orderMapper.createSVG(orderId, svg);
    }

    public String getSVG(int orderId){
        return orderMapper.getSVG(orderId);
    }

    public void setBoM(BoM billOfMaterials, int orderId) {
        orderMapper.setBoM(billOfMaterials, orderId);
    }
    public String[] getBOM(int orderId) {
        return orderMapper.getBOM(orderId);
    }

}
