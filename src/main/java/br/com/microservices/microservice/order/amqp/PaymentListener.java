package br.com.microservices.microservice.order.amqp;

import br.com.microservices.microservice.order.dto.PaymentDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentListener {

    @RabbitListener(queues = "payments.order-details")
    public void receiverMessage(PaymentDTO paymentDTO) {
        var message = """
            Dados do pagamento: %s
            Número do pedido: %s
            Valor R$: %s
            Status: %s 
            """.formatted(paymentDTO.getId(),
            paymentDTO.getOrderId(),
            paymentDTO.getValue(),
            paymentDTO.getStatus());

        System.out.println("Recebi a mensagem " + message);
    }
}
