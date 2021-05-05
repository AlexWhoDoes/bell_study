package org.example;

import org.example.office.requestobject.OfficeListRequest;
import org.example.office.requestobject.OfficeSaveRequest;
import org.example.office.requestobject.OfficeUpdateRequest;
import org.example.office.service.OfficeService;
import org.example.organization.requestobject.OrganizationListRequest;
import org.example.organization.requestobject.OrganizationSaveRequest;
import org.example.organization.requestobject.OrganizationUpdateRequest;
import org.example.user.requestobject.UserListRequest;
import org.example.utils.exeptionhandler.ErrorObject;
import org.example.utils.responsebodyhandler.ResponseObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = Main.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OfficeTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void getOfficeById() {
        ResponseObject response = restTemplate.getForObject("http://localhost:"
                + port
                + "/office/2"
                , ResponseObject.class);
        Map<String, String> responseMap = (Map<String, String>) response.getData();
        assertEquals(responseMap.get("id"), "2");
    }

    @Test
    public void getOfficeList() {
        OfficeListRequest officeListRequest = new OfficeListRequest();
        officeListRequest.setOrgId(1L);

        ResponseEntity<ResponseObject> response = restTemplate.postForEntity("http://localhost:"
                        + port
                        + "/office/list"
                , officeListRequest
                , ResponseObject.class);

        List<Map<String, String>> responseList = (List<Map<String, String>>) response.getBody().getData();
        assertTrue(responseList.size() > 0);
    }

    @Test
    public void saveOffice() {
        OfficeSaveRequest officeSaveRequest = new OfficeSaveRequest();
        officeSaveRequest.setOrgId(1L);


        ResponseEntity<ResponseObject> response = restTemplate.postForEntity("http://localhost:"
                        + port
                        + "/office/save"
                , officeSaveRequest
                , ResponseObject.class);

        Map<String, String> massage = (Map<String, String>) response.getBody().getData();
        assertEquals(massage.get("result"), "success");
    }

    @Test
    public void updateOffice() {
        OfficeUpdateRequest officeUpdateRequest = new OfficeUpdateRequest();
        officeUpdateRequest.setId(1L);
        officeUpdateRequest.setName("Test Name");
        officeUpdateRequest.setAddress("Test address");

        ResponseEntity<ResponseObject> response = restTemplate.postForEntity("http://localhost:"
                        + port
                        + "/office/update"
                , officeUpdateRequest
                , ResponseObject.class);

        Map<String, String> massage = (Map<String, String>) response.getBody().getData();
        assertEquals(massage.get("result"), "success");
    }

    @Test
    public void incorrectRequest() {

        ResponseEntity<ErrorObject> response = restTemplate.postForEntity("http://localhost:"
                        + port
                        + "/office/list"
                , ""
                , ErrorObject.class);

        String massage = (String) response.getBody().getError();
        assertTrue(massage.contains("An unexpected exception has occurred."));
    }

}
