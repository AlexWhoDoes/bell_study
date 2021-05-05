package org.example;

import org.example.user.requestobject.UserListRequest;
import org.example.user.requestobject.UserSaveRequest;
import org.example.user.requestobject.UserUpdateRequest;
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
public class UserTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void getUserById() {
        ResponseObject response = restTemplate.getForObject("http://localhost:" + port + "/user/1", ResponseObject.class);
        Map<String, String> responseMap = (Map<String, String>) response.getData();
        assertEquals(responseMap.get("id"), "1");
    }

    @Test
    public void getUserList() {
        UserListRequest userListRequest = new UserListRequest();
        userListRequest.setOfficeId(1L);

        ResponseEntity<ResponseObject> response = restTemplate.postForEntity("http://localhost:"
                + port
                + "/user/list"
                , userListRequest
                , ResponseObject.class);

        List<Map<String, String>> responseList = (List<Map<String, String>>) response.getBody().getData();
        assertTrue(responseList.size() > 0);

    }

    @Test
    public void saveUser() {
        UserSaveRequest userSaveRequest = new UserSaveRequest();
        userSaveRequest.setOfficeId(1L);
        userSaveRequest.setFirstName("Bob");
        userSaveRequest.setPosition("Doctor");

        ResponseEntity<ResponseObject> response = restTemplate.postForEntity("http://localhost:"
                        + port
                        + "/user/save"
                , userSaveRequest
                , ResponseObject.class);

        Map<String, String> massage = (Map<String, String>) response.getBody().getData();
        assertEquals(massage.get("result"), "success");
    }

    @Test
    public void updateUser() {

        UserUpdateRequest userUpdateRequest =  new UserUpdateRequest();

        userUpdateRequest.setId(1L);
        userUpdateRequest.setFirstName("Jack");
        userUpdateRequest.setPosition("Office worker");

        ResponseEntity<ResponseObject> response = restTemplate.postForEntity("http://localhost:"
                        + port
                        + "/user/update"
                , userUpdateRequest
                , ResponseObject.class);

        Map<String, String> massage = (Map<String, String>) response.getBody().getData();
        assertEquals(massage.get("result"), "success");
    }

    @Test
    public void incorrectRequest() {
        UserListRequest userListRequest = new UserListRequest();

        ResponseEntity<ErrorObject> response = restTemplate.postForEntity("http://localhost:"
                        + port
                        + "/user/list"
                , userListRequest
                , ErrorObject.class);

        String massage = (String) response.getBody().getError();
        assertTrue(massage.contains("Failed to process a request due to requirements for a request is not met"));
    }

}
