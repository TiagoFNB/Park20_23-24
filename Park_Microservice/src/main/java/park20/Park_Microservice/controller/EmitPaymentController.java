package park20.Park_Microservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import park20.Park_Microservice.dto.PaymentRequestDTO;
import park20.Park_Microservice.service.EmitPaymentService;

@RestController
public class EmitPaymentController {

    EmitPaymentService emitPaymentService;

    public EmitPaymentController(EmitPaymentService emitPaymentService) {
        this.emitPaymentService = emitPaymentService;
    }

    @PostMapping(value = "/emit-payment")
    public Double detect(@RequestBody PaymentRequestDTO dto) {
        try{

            return this.emitPaymentService.emit(dto.stayStartTime,dto.stayEndTime,dto.category,dto.parkDesignation);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
