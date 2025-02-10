package sg.edu.iss.nus.lecture25redis1to1.Model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class OrderRequest {
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    @NotBlank(message = "Address is required")
    private String address;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[89]\\d{7}$", message = "Invalid phone number format")
    private String phoneNumber;
    
    @Positive(message = "Total must be greater than 0")
    private Double total;

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    // Convert to Order
    public Order toOrder() {
        Order order = new Order();
        order.setName(this.name);
        order.setEmail(this.email);
        order.setAddress(this.address);
        order.setPhoneNumber(this.phoneNumber);
        order.setTotal(this.total);
        return order;
    }
}