package org.example.citizenship.service;

import org.example.citizenship.ctizenshipview.CitizenshipView;
import java.util.List;


public interface ServiceCitizenship {
    /**
     * Return list of existing citizenship in a response representation
     * @return
     */
    List<CitizenshipView> all();
}
