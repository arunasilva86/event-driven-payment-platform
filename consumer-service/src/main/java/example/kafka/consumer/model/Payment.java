package example.kafka.consumer.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Payment")
@Getter
@Setter
public class Payment {

    @Id
    private String id;
    private double amount;
    private String paymentProvider;


    public Payment(double amount, String paymentProvider) {
        this.amount = amount;
        this.paymentProvider = paymentProvider;
    }
}
