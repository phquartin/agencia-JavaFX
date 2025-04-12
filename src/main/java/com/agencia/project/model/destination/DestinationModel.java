package com.agencia.project.model.destination;

import com.agencia.project.model.country.CountryModel;
import lombok.Data;

@Data
public class DestinationModel {

    private final Long id;
    private String name;
    private String description;
    private CountryModel country;

}
