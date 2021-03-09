package nl.novi.stuivenberg.springboot.example.security.service;

import nl.novi.stuivenberg.springboot.example.security.exception.DatabaseErrorException;
import nl.novi.stuivenberg.springboot.example.security.exception.RecordNotFoundException;
import nl.novi.stuivenberg.springboot.example.security.domain.Message;
import nl.novi.stuivenberg.springboot.example.security.repository.MessageRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageRepository messageRepository;

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message getMessageById(long id) {
        if(messageRepository.existsById(id)) {
            return messageRepository.findById(id).orElse(null);
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void deleteMessage(long id) { //deleteClient gaat dus praten met de clientRepository
        //Aanpassen-->Exception foutmelding invoeren:
        if (messageRepository.existsById(id)) {
            messageRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();
            //RuntimeException naar RecordNotFoundException na aanmaken exception map met inhoud!
        }

    }

    @Override
    public long saveMessage(Message message) {
        Message newMessage = messageRepository.save(message);
        return newMessage.getId();
    }

    @Override
    public void updateMessage(long id, Message message) {
        if(messageRepository.existsById(id)) {
            try {
                //het id moet worden opgehaald
                Message existingMessage = messageRepository.findById(id).orElse(null);
                existingMessage.setClientImage(message.getClientImage());
                existingMessage.setClientName(message.getClientName());
                existingMessage.setClientText(message.getClientText());
                //Wat raar voor de immage zat hij er niet bij; nu erbij gezt
                existingMessage.setContestImage(message.getContestImage());
                messageRepository.save(existingMessage);
            }
            catch (Exception ex) {
                throw new DatabaseErrorException();
            }
            //Hier zie je nu hij kan twee verschillende foutcodes teruggeven
            //afhankelijk van wat er gebeurd is!!!

        }
        else {
            throw new RecordNotFoundException();
        }

    }

    //Nu met try catch blok een exception maken.Er onder als 2 de eerdere manier
    @Override
    public Message getMessageByClientName(String clientName) {
        try {
            return messageRepository.findByClientNameIgnoreCase(clientName);
        } catch (Exception ex) {
            throw new RecordNotFoundException();
        }
    }

    //Eerdere manier voor fout afvangen:
    public Message getMessageByClientName2(String clientName) {
        Message message = messageRepository.findByClientNameIgnoreCase(clientName);
        if(message == null) {
            throw new RecordNotFoundException();
        }
        else {
            return message;
        }
    }
}

