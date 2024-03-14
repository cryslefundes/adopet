package br.com.cryslefundes.adopet.api.adapter;

import br.com.cryslefundes.adopet.api.core.dto.EmailDTO;

public interface MessageGateway {
    void publishMessageEmail(EmailDTO dto);
}
