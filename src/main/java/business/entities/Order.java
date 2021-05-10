package business.entities;

import java.sql.Timestamp;

public class Order {
   int orderId;
   int userId;
   int statusId;
   double price;
   Timestamp timeCreated;
   Timestamp  timeUpdated;
   int carportLength;
   int carportWidth;
   int claddingId;
   int roofingId;
   int shedWidth;
   int shedLength;

    public Order() {
    }

    public Order(int userId, int carportLength, int carportWidth, int claddingId, int roofingId, int shedWidth, int shedLength) {
        this.userId = userId;
        this.carportLength = carportLength;
        this.carportWidth = carportWidth;
        this.claddingId = claddingId;
        this.roofingId = roofingId;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
    }

    public Order(int orderId, int statusId, double price, Timestamp timeCreated, Timestamp timeUpdated, int carportLength, int carportWidth, int claddingId, int roofingId, int shedWidth, int shedLength) {
        this.orderId = orderId;
        this.statusId = statusId;
        this.price = price;
        this.timeCreated = timeCreated;
        this.timeUpdated = timeUpdated;
        this.carportLength = carportLength;
        this.carportWidth = carportWidth;
        this.claddingId = claddingId;
        this.roofingId = roofingId;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
    }

    public Order(int orderId, int userId, int statusId, double price, Timestamp timeCreated, Timestamp timeUpdated, int carportLength, int carportWidth, int claddingId, int roofingId, int shedWidth, int shedLength) {
        this.orderId = orderId;
        this.userId = userId;
        this.statusId = statusId;
        this.price = price;
        this.timeCreated = timeCreated;
        this.timeUpdated = timeUpdated;
        this.carportLength = carportLength;
        this.carportWidth = carportWidth;
        this.claddingId = claddingId;
        this.roofingId = roofingId;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
    }

    //<editor-fold desc="Getters and setters">
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Timestamp getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Timestamp timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Timestamp getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(Timestamp timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCarportLength() {
        return carportLength;
    }

    public void setCarportLength(int carportLength) {
        this.carportLength = carportLength;
    }

    public int getCarportWidth() {
        return carportWidth;
    }

    public void setCarportWidth(int carportWidth) {
        this.carportWidth = carportWidth;
    }

    public int getCladdingId() {
        return claddingId;
    }

    public void setCladdingId(int claddingId) {
        this.claddingId = claddingId;
    }

    public int getRoofingId() {
        return roofingId;
    }

    public void setRoofingId(int roofingId) {
        this.roofingId = roofingId;
    }

    public int getShedWidth() {
        return shedWidth;
    }

    public void setShedWidth(int shedWidth) {
        this.shedWidth = shedWidth;
    }

    public int getShedLength() {
        return shedLength;
    }

    public void setShedLength(int shedLength) {
        this.shedLength = shedLength;
    }
    //</editor-fold>
}
