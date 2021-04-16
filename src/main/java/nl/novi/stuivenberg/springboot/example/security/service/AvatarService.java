package nl.novi.stuivenberg.springboot.example.security.service;

//specifieke import
import nl.novi.stuivenberg.springboot.example.security.domain.Avatar;

//algemene import
import java.util.List;

//extra Nick imports erbij



public interface AvatarService {

    List<Avatar>getAllAvatars();
    Avatar getAvatarById(long id);
    //Dit komt allemaal overeen met wat we in de repository hebben gezegd
    //Omdat dit een interface is, is dit ook het enige wat hier hoeft te staan
    void deleteAvatar(long id);
    long saveAvatar(Avatar avatar);
    void updateAvatar(long id, Avatar avatar); //die dan implementeren in ClientServiceImpl
    //Extra dingen

    Avatar getAvatarByLastName(String lastName);
}
