package sg.edu.iss.nus.lecture25redis1to1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import sg.edu.iss.nus.lecture25redis1to1.Model.Order;
import sg.edu.iss.nus.lecture25redis1to1.Model.OrderRequest;
import sg.edu.iss.nus.lecture25redis1to1.Repo.OrderRepository;

  @Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    public String createOrder(OrderRequest request) {
        // Convert request to order
        Order order = request.toOrder();
        
        try {
            // Convert order to JSON and add to queue
            String orderJson = order.toJson().toString();
            orderRepo.addToQueue(orderJson);
            
            return order.getOrderId();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create order: " + e.getMessage());
        }
    }

    public Long getQueueSize() {
        return orderRepo.getQueueLength();
    }

   @Autowired
    @Qualifier("orderRedisTemplate")
    private RedisTemplate<String, String> template;
  }
