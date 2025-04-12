package com.agencia.project.model.country;

import com.agencia.project.model.continent.ContinentModel;
import lombok.Data;

@Data
public class CountryModel {

    private final Long id;
    private final String name;
    private String language;
    private ContinentModel continent;

}
