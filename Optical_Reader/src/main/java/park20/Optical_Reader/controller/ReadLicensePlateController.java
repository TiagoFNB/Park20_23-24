package park20.Optical_Reader.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import park20.Optical_Reader.dto.ReadDTO;
import park20.Optical_Reader.service.ReadLicensePlateService;

@RestController
public class ReadLicensePlateController {

    ReadLicensePlateService readLicensePlateService;

    public ReadLicensePlateController(ReadLicensePlateService readLicensePlateService) {
        this.readLicensePlateService = readLicensePlateService;
    }

    @PostMapping(value = "/optical-read")
    public Object read(@RequestBody ReadDTO readDTO) {
        try{
            return this.readLicensePlateService.read(readDTO);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
