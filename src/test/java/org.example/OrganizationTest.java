package org.example;

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
public class OrganizationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void getOrganizationById() {
        ResponseObject response = restTemplate.getForObject("http://localhost:"
                + port
                + "/organization/1"
                , ResponseObject.class);
        Map<String, String> responseMap = (Map<String, String>) response.getData();
        assertEquals(responseMap.get("id"), "1");
    }

    @Test
    public void getOrganizationList() {
        OrganizationListRequest organizationListRequest = new OrganizationListRequest();
        organizationListRequest.setName("Secondbank");

        ResponseEntity<ResponseObject> response = restTemplate.postForEntity("http://localhost:"
                        + port
                        + "/organization/list"
                , organizationListRequest
                , ResponseObject.class);

        List<Map<String, String>> responseList = (List<Map<String, String>>) response.getBody().getData();
        assertTrue(responseList.size() > 0);

    }

    @Test
    public void saveOrganization() {
        OrganizationSaveRequest organizationSaveRequest = new OrganizationSaveRequest();
        organizationSaveRequest.setName("TestOrg");
        organizationSaveRequest.setFullName("LLC TestOrg");
        organizationSaveRequest.setInn("999999");
        organizationSaveRequest.setKpp("0123");
        organizationSaveRequest.setAddress("MOSCOW TEST");

        ResponseEntity<ResponseObject> response = restTemplate.postForEntity("http://localhost:"
                        + port
                        + "/organization/save"
                , organizationSaveRequest
                , ResponseObject.class);

        Map<String, String> massage = (Map<String, String>) response.getBody().getData();
        assertEquals(massage.get("result"), "success");
    }

    @Test
    public void updateOrganization() {

        OrganizationUpdateRequest organizationUpdateRequest = new OrganizationUpdateRequest();
        organizationUpdateRequest.setId(1L);
        organizationUpdateRequest.setName("TestOrg");
        organizationUpdateRequest.setFullName("LLC TestOrg");
        organizationUpdateRequest.setInn("999999");
        organizationUpdateRequest.setKpp("0123");
        organizationUpdateRequest.setAddress("MOSCOW TEST");

        ResponseEntity<ResponseObject> response = restTemplate.postForEntity("http://localhost:"
                        + port
                        + "/organization/update"
                , organizationUpdateRequest
                , ResponseObject.class);

        Map<String, String> massage = (Map<String, String>) response.getBody().getData();
        assertEquals(massage.get("result"), "success");
    }

    @Test
    public void incorrectRequest() {
        UserListRequest userListRequest = new UserListRequest();

        ResponseEntity<ErrorObject> response = restTemplate.postForEntity("http://localhost:"
                        + port
                        + "/organization/list"
                , userListRequest
                , ErrorObject.class);

        String massage = (String) response.getBody().getError();
        assertTrue(massage.contains("Failed to process a request due to requirements for a request is not met"));
    }

}
