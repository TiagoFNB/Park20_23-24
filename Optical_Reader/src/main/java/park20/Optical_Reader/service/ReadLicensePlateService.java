package park20.Optical_Reader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import park20.Optical_Reader.dto.ReadDTO;
import park20.Optical_Reader.dto.SendDataDTO;

import java.util.Objects;

@Service
public class ReadLicensePlateService {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${park20.parkId}")
    private String parkId;

    @Value("${park20.isEntry}")
    private Boolean isEntry;

    @Autowired
    private ConfigurableEnvironment env;

    private String entryUrl = "http://localhost:9001/vehicle-entry";
    private String exitUrl = "http://localhost:9001/vehicle-exit";

    private final WebClient webClientEntry;
    private final WebClient webClientExit;

    public ReadLicensePlateService(WebClient.Builder webClientBuilder) {
        this.webClientEntry = webClientBuilder.baseUrl(entryUrl).build();
        this.webClientExit = webClientBuilder.baseUrl(exitUrl).build();
    }

    public Object read(ReadDTO readDTO) {
        String keyword = appName.substring(22);

        SendDataDTO sendDTO = new SendDataDTO();
        sendDTO.idPark = parkId;
        sendDTO.keyword = keyword;
        sendDTO.licensePlate = readDTO.licensePlate;


        WebClient webClient;
        if(Objects.equals(env.getProperty("isEntry"), "true")) {
            webClient = webClientEntry;
        } else {
            webClient = webClientExit;
        }

        return webClient.post()
                .body(BodyInserters.fromValue(sendDTO))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
