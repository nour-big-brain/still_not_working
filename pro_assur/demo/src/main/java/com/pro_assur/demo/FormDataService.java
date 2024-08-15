package com.pro_assur.demo;

import org.springframework.stereotype.Service;

@Service
public class FormDataService {

    private final FormDataRepository formDataRepository;

    public FormDataService(FormDataRepository formDataRepository) {
        this.formDataRepository = formDataRepository;
    }

    public void saveFormData(FormData formData) {
        formDataRepository.save(formData);
    }
}
