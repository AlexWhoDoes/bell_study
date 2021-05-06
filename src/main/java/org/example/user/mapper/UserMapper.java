package org.example.user.mapper;

import org.example.user.requestobject.UserSaveRequest;
import org.example.user.model.User;
import org.example.user.userview.UserView;
import org.example.user.userview.UserViewShort;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;


@Component
public class UserMapper {

    /**
     * Converts List of User to responseMap
     * @param in
     * @return
     */
    public List<UserViewShort> all(List<User> in) {
        if(in.isEmpty()) {
            throw new RuntimeException("no users have found with such parameters");
        }

        List<UserViewShort> out = new ArrayList<>();

        for (User user : in) {
            UserViewShort userView= responseUserMapBuilder(user);
            out.add(userView);
        }
        return out;
    }

    /**
     * Converts User to UserDtoApiId object
     * @param user
     * @return
     */
    public UserView getById(User user) {

        UserView out = new UserView();

        out.setId(user.getId());
        out.setFirstName(user.getFirstName());
        out.setSecondName(user.getSecondName());
        out.setMiddleName(user.getMiddleName());
        out.setPosition(user.getPosition());
        out.setPhone(user.getPhone());
        out.setDocName(user.getDocument().getDocumentType().getDocumentName());
        out.setDocNumber(user.getDocument().getDocumentNumber());
        out.setDocDate(String.valueOf(user.getDocument().getDocumentDate()));
        out.setCitizenshipCode(user.getCitizenship().getCode());
        out.setCitizenshipName(user.getCitizenship().getCitizenshipName());
        out.setIsIdentified(String.valueOf(user.getIsIdentified()));

        return out;
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

    private UserViewShort responseUserMapBuilder(User user) {
        UserViewShort out = new UserViewShort();

        out.setId(user.getId());
        out.setFirstName(user.getFirstName());
        out.setSecondName(user.getSecondName());
        out.setMiddleName(user.getMiddleName());
        out.setPosition(user.getPosition());

        return out;
    }
}
