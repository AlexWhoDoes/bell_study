package org.example.user.mapper;


import org.example.user.dto.UserDto;
import org.example.user.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapper {

    public List<UserDto> all(List<User> in) {
        List<UserDto> out = new ArrayList<>();
        for (User user : in) {
            UserDto userDto = new UserDto();

            userDto.setId(user.getId());
            userDto.setFirstName(user.getFirstName());
            userDto.setMiddleName(user.getMiddleName());
            userDto.setSecondName(user.getSecondName());
            userDto.setOccupation(user.getOccupation());
            userDto.setPhone(user.getPhone());

            userDto.setDocumentName(user.getDocument().getDocumentType().getDocumentName());
            userDto.setDocumentNumber(user.getDocument().getDocumentNumber());
            userDto.setDocumentDate(user.getDocument().getDocumentDate());

            userDto.setCitizenshipName(user.getCitizenship().getCitizenshipName());
            userDto.setCode(user.getCitizenship().getCode());

            userDto.setIsIdentified(user.getIsIdentified());

            out.add(userDto);
        }
        return out;
    }


    public UserDto getById(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setSecondName(user.getSecondName());
        userDto.setOccupation(user.getOccupation());
        userDto.setPhone(user.getPhone());

        userDto.setDocumentName(user.getDocument().getDocumentType().getDocumentName());
        userDto.setDocumentNumber(user.getDocument().getDocumentNumber());
        userDto.setDocumentDate(user.getDocument().getDocumentDate());

        userDto.setCitizenshipName(user.getCitizenship().getCitizenshipName());
        userDto.setCode(user.getCitizenship().getCode());

        userDto.setIsIdentified(user.getIsIdentified());

        return userDto;
    }
}
