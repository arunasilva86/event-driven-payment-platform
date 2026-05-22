package example.kafka.consumer.service;

import example.kafka.common.PaymentEvent;
import example.kafka.consumer.model.Payment;
import example.kafka.consumer.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @KafkaListener(
            topics = "${kafka.topics.payment-topic}", concurrency = "4")
    public void consumePaymentEvents(ConsumerRecord<String, PaymentEvent> message, Acknowledgment ack) {

        log.info("Processing message.. key: {} value: {} partition: {} offset: {}", message.key(), message.value(), message.partition(), message.offset());

        Payment payment = new Payment(message.value().getAmount(), message.value().getPaymentProvider());
        payment = paymentRepository.save(payment);

        log.info("Message processed and saved to database. payment.id: {}", payment.getId());

        ack.acknowledge(); // default behavior is auto commit (configurable in application.yml) But for at least one delivery manual commit like this is required

    }
}
