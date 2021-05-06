package org.example.citizenship.service;

import org.example.citizenship.ctizenshipview.CitizenshipView;
import org.example.citizenship.dao.CitizenshipDao;
import org.example.citizenship.model.Citizenship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

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
    public List<CitizenshipView> all() {

        log.info("A request to retrieve a list of citizenship is received");

        List<Citizenship> citizenshipList = citizenshipDao.findAll();
        List<CitizenshipView> out = mapper(citizenshipList);

        log.info("A list of citizenship has been retrieved successfully");

        return out;
    }

    private List<CitizenshipView> mapper(List<Citizenship> citizenshipList) {
        List<CitizenshipView> out = new ArrayList<>();

        for (Citizenship citizenship: citizenshipList) {

            CitizenshipView citizenshipView = new CitizenshipView();
            citizenshipView.setName(citizenship.getCitizenshipName());
            citizenshipView.setCode(citizenship.getCode());
            out.add(citizenshipView);
        }
        return out;
    }
}
