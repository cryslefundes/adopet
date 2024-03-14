package br.com.cryslefundes.adopet.api.service.validation;

public interface IValidation<T extends Record> {
    void validate(T dto);
}
