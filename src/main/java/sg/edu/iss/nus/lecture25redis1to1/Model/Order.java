package sg.edu.iss.nus.lecture25redis1to1.Model;

import java.io.StringReader;
import java.util.UUID;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Order {

    private String orderId;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String status;
    private Double total;

    public Order() {
        this.orderId = generateId();
        this.status = "Pending";
    }

    private String generateId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    //convert Order to Json
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("orderId", this.getOrderId())
                .add("name", this.getName())
                .add("email", this.getAddress())
                .add("address", this.getAddress())
                .add("phoneNumber", this.getPhoneNumber())
                .add("status", this.getStatus())
                .add("total", this.getTotal())
                .build();
    }

// Create Order from JSON string
    public static Order fromJson(String jsonString) {
        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();

        Order order = new Order();
        order.setOrderId(jsonObject.getString("orderId"));
        order.setName(jsonObject.getString("name"));
        order.setEmail(jsonObject.getString("email"));
        order.setAddress(jsonObject.getString("address"));
        order.setPhoneNumber(jsonObject.getString("phoneNumber"));
        order.setStatus(jsonObject.getString("status"));
        order.setTotal(jsonObject.getJsonNumber("total").doubleValue());

        return order;
    }

    // Create order from JsonObject
    public static Order fromJson(JsonObject jsonObject) {
        Order order = new Order();
        order.setOrderId(jsonObject.getString("orderId"));
        order.setName(jsonObject.getString("name"));
        order.setEmail(jsonObject.getString("email"));
        order.setAddress(jsonObject.getString("address"));
        order.setPhoneNumber(jsonObject.getString("phoneNumber"));
        order.setStatus(jsonObject.getString("status"));
        order.setTotal(jsonObject.getJsonNumber("total").doubleValue());

        return order;
    }

}
