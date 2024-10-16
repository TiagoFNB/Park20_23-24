package park20.Customer_Microservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import park20.Customer_Microservice.dto.PaymentMethodListDTO;
import park20.Customer_Microservice.service.SavePaymentMethodListService;

@RestController
public class SavePaymentMethodListController {

    SavePaymentMethodListService savePaymentMethodListService;

    public SavePaymentMethodListController(SavePaymentMethodListService savePaymentMethodListService){
        this.savePaymentMethodListService = savePaymentMethodListService;
    }

    @PostMapping(value = "/paymentMethod")
    public void save(@RequestBody PaymentMethodListDTO paymentsDTO){
        try{
            this.savePaymentMethodListService.save(paymentsDTO);
            return;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
