package com.park20.display;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class DisplayTextDTO {
    @JsonProperty("text")
    private String text;
}