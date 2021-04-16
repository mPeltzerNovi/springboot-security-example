package nl.novi.stuivenberg.springboot.example.security.service;

import nl.novi.stuivenberg.springboot.example.security.exception.DatabaseErrorException;
import nl.novi.stuivenberg.springboot.example.security.exception.RecordNotFoundException;
import nl.novi.stuivenberg.springboot.example.security.domain.Avatar;
import nl.novi.stuivenberg.springboot.example.security.repository.AvatarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AvatarServiceImpl implements AvatarService {

    @Autowired
    AvatarRepository avatarRepository;


    @Override
    public List<Avatar> getAllAvatars() {
        return avatarRepository.findAll();
    }

    @Override
    public Avatar getAvatarById(long id) {
        if(avatarRepository.existsById(id)) {
            return avatarRepository.findById(id).orElse(null);
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void deleteAvatar(long id) {
        if (avatarRepository.existsById(id)) {
            avatarRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();

        }

    }

    @Override
    public long saveAvatar(Avatar avatar) {
        Avatar newAvatar = avatarRepository.save(avatar);
        return newAvatar.getId();
    }

    @Override
    public void updateAvatar(long id, Avatar avatar) {
        if(avatarRepository.existsById(id)) {
            try {
                //het id moet worden opgehaald
                Avatar existingAvatar = avatarRepository.findById(id).orElse(null);
                existingAvatar.setFutureOne(avatar.getFutureOne());
                existingAvatar.setLastName(avatar.getLastName());
                existingAvatar.setFutureTwo(avatar.getFutureTwo());
                //AvatarImage er tussen
                existingAvatar.setAvatarImage(avatar.getAvatarImage());
                avatarRepository.save(existingAvatar);
            }
            catch (Exception ex) {
                throw new DatabaseErrorException();
            }
            //Evt nog foutcode toevoegen

        }
        else {
            throw new RecordNotFoundException();
        }

    }


    @Override
    public Avatar getAvatarByLastName(String lastName) {
        try {
            return avatarRepository.findByLastNameIgnoreCase(lastName);
        } catch (Exception ex) {
            throw new RecordNotFoundException();
        }
    }

    public Avatar getAvatarByLastName2(String lastName) {
        Avatar avatar = avatarRepository.findByLastNameIgnoreCase(lastName);
        if(avatar == null) {
            throw new RecordNotFoundException();
        }
        else {
            return avatar;
        }
    }
}



