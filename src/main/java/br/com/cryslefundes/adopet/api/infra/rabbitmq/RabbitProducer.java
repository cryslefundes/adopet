package br.com.cryslefundes.adopet.api.infra.rabbitmq;

import br.com.cryslefundes.adopet.api.adapter.MessageGateway;
import br.com.cryslefundes.adopet.api.core.dto.EmailDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitProducer implements MessageGateway {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value(value = "${queue.name}")
    private String routingKey;

    @Override
    public void publishMessageEmail(EmailDTO dto) {
        rabbitTemplate.convertAndSend(routingKey, dto);
    }
}
