package sg.edu.iss.nus.lecture25redis1to1.Repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
    
    private static final String ORDER_QUEUE = "orders";
    
    @Autowired
    @Qualifier("orderRedisTemplate")
    private RedisTemplate<String, String> template;

    // Add order to queue
    public void addToQueue(String orderJson) {
        template.opsForList().leftPush(ORDER_QUEUE, orderJson);
    }

    // Get order from queue (for processing)
    public String getNextOrder() {
        return template.opsForList().rightPop(ORDER_QUEUE);
    }

    // Check queue length
    public Long getQueueLength() {
        return template.opsForList().size(ORDER_QUEUE);
    }
}
