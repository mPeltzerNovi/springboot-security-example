package nl.novi.stuivenberg.springboot.example.security.service;

//specifieke import
import nl.novi.stuivenberg.springboot.example.security.domain.Message;

//algemene import
import java.util.List;

public interface MessageService {

    List<Message>getAllMessages();
    Message getMessageById(long id);
    //Dit komt allemaal overeen met wat we in de repository hebben gezegd
    //Omdat dit een interface is, is dit ook het enige wat hier hoeft te staan
    void deleteMessage(long id);
    long saveMessage(Message message);
    void updateMessage(long id, Message message); //die dan implementeren in ClientServiceImpl
    //Extra dingen

    Message getMessageByClientName(String clientName);
    //Verbeterd naar lastName in de Backend3!!!!!!!
}
