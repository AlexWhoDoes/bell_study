package org.example.citizenship.service;

import java.util.List;
import java.util.Map;

public interface ServiceCitizenship {
    /**
     * Return list of existing citizenship in a response representation
     * @return
     */
    List<Map<String, String>> all();
}
