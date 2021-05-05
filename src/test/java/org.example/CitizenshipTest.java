package org.example;

import org.example.utils.responsebodyhandler.ResponseObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = Main.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CitizenshipTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void getOfficeById() {
        ResponseObject responseObject = restTemplate.getForObject("http://localhost:"
                        + port
                        + "/countries"
                , ResponseObject.class);

        List<Map<String, String>> responseList = (List<Map<String, String>>) responseObject.getData();
        assertTrue(responseList.size() > 0);
    }

}
