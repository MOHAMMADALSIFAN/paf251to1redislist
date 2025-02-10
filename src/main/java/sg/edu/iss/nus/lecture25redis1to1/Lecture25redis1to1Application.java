package sg.edu.iss.nus.lecture25redis1to1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import sg.edu.iss.nus.lecture25redis1to1.Service.OrderProcessor;

@SpringBootApplication
@EnableAsync
public class Lecture25redis1to1Application implements CommandLineRunner{

	 @Autowired
    private OrderProcessor orderProcessor;

	public static void main(String[] args) {
		SpringApplication.run(Lecture25redis1to1Application.class, args);
	}
	@Override
	public void run(String... args) {
			orderProcessor.start();
	}
}

// OrderService receives new orders and adds them to Redis queue
// OrderProcessor runs in background, continuously checking for new orders
// When an order is found, OrderProcessor processes it
// OrderRepository handles all Redis operations