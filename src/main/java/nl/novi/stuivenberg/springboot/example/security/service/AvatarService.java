package nl.novi.stuivenberg.springboot.example.security.service;

import nl.novi.stuivenberg.springboot.example.security.domain.Avatar;

import java.util.List;


public interface AvatarService {

    List<Avatar>getAllAvatars();
    Avatar getAvatarById(long id);
    void deleteAvatar(long id);
    long saveAvatar(Avatar avatar);
    void updateAvatar(long id, Avatar avatar);


    Avatar getAvatarByLastName(String lastName);
}
