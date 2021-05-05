package org.example.citizenship.service;

import org.example.citizenship.dao.CitizenshipDao;
import org.example.citizenship.model.Citizenship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceCitizenshipImp implements ServiceCitizenship{
    private final CitizenshipDao citizenshipDao;
    private final Logger log;

    @Autowired
    public ServiceCitizenshipImp(CitizenshipDao citizenshipDao) {
        this.citizenshipDao = citizenshipDao;
        this.log = LoggerFactory.getLogger(getClass());
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<Map<String, String>> all() {

        log.info("A request to retrieve a list of citizenship is received");

        List<Citizenship> citizenshipList = citizenshipDao.findAll();
        List<Map<String, String>> out = mapper(citizenshipList);

        log.info("A list of citizenship has been retrieved successfully");

        return out;

    }

    private List<Map<String, String>> mapper(List<Citizenship> citizenshipList) {
        List<Map<String, String>> out = new ArrayList<>();
        for (Citizenship citizenship: citizenshipList) {
            Map<String, String> responseBody = new LinkedHashMap<>();
            responseBody.put("name", citizenship.getCitizenshipName());
            responseBody.put("code", citizenship.getCode());
            out.add(responseBody);
        }
        return out;
    }
}
