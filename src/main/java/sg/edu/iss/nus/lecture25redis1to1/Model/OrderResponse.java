package sg.edu.iss.nus.lecture25redis1to1.Model;

public class OrderResponse {
  private String orderId;
  private String message;
  private String status;

  public OrderResponse(String orderId, String message, String status) {
      this.orderId = orderId;
      this.message = message;
      this.status = status;
  }

  // Getters and setters
  public String getOrderId() { return orderId; }
  public void setOrderId(String orderId) { this.orderId = orderId; }
  
  public String getMessage() { return message; }
  public void setMessage(String message) { this.message = message; }
  
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
}