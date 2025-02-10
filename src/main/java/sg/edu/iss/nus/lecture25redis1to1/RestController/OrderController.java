package sg.edu.iss.nus.lecture25redis1to1.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.validation.Valid;
import sg.edu.iss.nus.lecture25redis1to1.Model.OrderRequest;
import sg.edu.iss.nus.lecture25redis1to1.Service.OrderService;


@RestController
@RequestMapping(path="/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderSvc;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createOrder(@Valid @RequestBody OrderRequest request) {
        try {
            // Create order and get orderId
            String orderId = orderSvc.createOrder(request);

            // Create success response
            JsonObject response = Json.createObjectBuilder()
                    .add("orderId", orderId)
                    .add("message", "Order created successfully")
                    .add("status", "PENDING")
                    .build();

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response.toString());

        } catch (Exception e) {
            // Create error response
            JsonObject error = Json.createObjectBuilder()
                    .add("error", e.getMessage())
                    .build();

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(error.toString());
        }
    }

    @GetMapping(path="/queue/size",
               produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getQueueSize() {
        try {
            Long size = orderSvc.getQueueSize();

            JsonObject response = Json.createObjectBuilder()
                    .add("queueSize", size)
                    .build();

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response.toString());

        } catch (Exception e) {
            JsonObject error = Json.createObjectBuilder()
                    .add("error", e.getMessage())
                    .build();

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(error.toString());
        }
    }
}
