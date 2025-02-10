package sg.edu.iss.nus.lecture25redis1to1.Service;

import java.time.Duration;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import sg.edu.iss.nus.lecture25redis1to1.Model.Order;
import sg.edu.iss.nus.lecture25redis1to1.Repo.OrderRepository;

@Component
public class OrderProcessor {
    
    @Autowired
    private OrderRepository orderRepo;

    @Async
    public void start() {
        Runnable processor = () -> {
            while(true) {
                try {
                    // Try to get next order from queue
                    String orderJson = orderRepo.getNextOrder();
                    
                    if (orderJson != null) {
                        processOrder(orderJson);
                    } else {
                        // Wait a bit before next attempt
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        
        // Start processing in new thread
        Executors.newSingleThreadExecutor().execute(processor);
    }

    private void processOrder(String orderJson) {
        try {
            // Convert JSON back to Order
            Order order = Order.fromJson(orderJson);
            
            // Log processing start
            System.out.println("Processing order: " + order.getOrderId());
            
            // Simulate processing time
            Thread.sleep(2000);
            
            // Update order status
            order.setStatus("COMPLETED");
            
            // Log completion
            System.out.println("Completed order: " + order.getOrderId());
            
        } catch (Exception e) {
            System.err.println("Error processing order: " + e.getMessage());
        }
    }
}
