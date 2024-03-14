package br.com.cryslefundes.adopet.api.producer;

import br.com.cryslefundes.adopet.api.adapter.MessageGateway;
import br.com.cryslefundes.adopet.api.core.dto.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailProducer {
    @Autowired
    private MessageGateway gateway;

    public void publishMessageEmail(EmailDTO dto) {
        gateway.publishMessageEmail(dto);
    }
}
