package org.example.user.mapper;

import org.example.user.requestobject.UserSaveRequest;
import org.example.user.model.User;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Component
public class UserMapper {

    /**
     * Converts List of User to responseMap
     * @param in
     * @return
     */
    public List<Map<String, String>> all(List<User> in) {
        if(in.isEmpty()) {
            throw new RuntimeException("no users have found with such parameters");
        }

        List<Map<String, String>> out = new ArrayList<>();

        for (User user : in) {
            Map<String, String> responseMap = responseUserMapBuilder(user);;
            out.add(responseMap);
        }
        return out;
    }

    /**
     * Converts User to UserDtoApiId object
     * @param user
     * @return
     */
    public Map<String, String> getById(User user) {

        Map<String, String> responseMap = responseUserMapBuilder(user);

        responseMap.put("phone", user.getPhone());
        responseMap.put("docName", user.getDocument().getDocumentType().getDocumentName());
        responseMap.put("docNumber", user.getDocument().getDocumentNumber());
        responseMap.put("docDate", String.valueOf(user.getDocument().getDocumentDate()));
        responseMap.put("citizenshipName", user.getCitizenship().getCitizenshipName());
        responseMap.put("citizenshipCode", user.getCitizenship().getCode());
        responseMap.put("isIdentified", String.valueOf(user.getIsIdentified()));

        return responseMap;
    }

    /**
     * Converts userSaveRequestObject to User
     * @param userSaveRequest
     * @return
     */
    public User save(UserSaveRequest userSaveRequest) {

        if (userSaveRequest == null) {
            throw new RuntimeException("Incorrect parameters have been passed, please try again"); //According to recommendations should be return Enum like userSaveRequestObject.NOT_EMPTY
        }

        User user = new User();
        if (userSaveRequest.getFirstName()!= null) {
            user.setFirstName(userSaveRequest.getFirstName());
        }
        if(userSaveRequest.getSecondName() != null) {
            user.setSecondName(userSaveRequest.getSecondName());
        }
        if (userSaveRequest.getMiddleName() != null) {
            user.setMiddleName(userSaveRequest.getMiddleName());
        }
        if(userSaveRequest.getPosition() != null) {
            user.setPosition(userSaveRequest.getPosition());
        }
        if(userSaveRequest.getPhone() != null) {
            user.setPhone(userSaveRequest.getPhone());
        }
        if(userSaveRequest.getIsIdentified() != null) {
            user.setIsIdentified(userSaveRequest.getIsIdentified());
        }

        return user;
    }

    private Map<String, String> responseUserMapBuilder(User user) {
        Map<String, String> responseUserMap = new LinkedHashMap<>();

        responseUserMap.put("id", String.valueOf(user.getId()));
        responseUserMap.put("firstName", user.getFirstName());
        responseUserMap.put("secondName", user.getSecondName());
        responseUserMap.put("middleName", user.getMiddleName());
        responseUserMap.put("position", user.getPosition());

        return responseUserMap;
    }
}
