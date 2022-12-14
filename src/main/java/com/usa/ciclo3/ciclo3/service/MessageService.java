package com.usa.ciclo3.ciclo3.service;

import com.usa.ciclo3.ciclo3.model.Message;
import com.usa.ciclo3.ciclo3.repository.MessageRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll() {
        return messageRepository.getAll();
    }

    public Optional<Message> getMessage(int id) {
        return messageRepository.getMessage(id);
    }

    public Message save(Message m) {
        if (m.getIdMessage() == null) {
            return messageRepository.save(m);
        } else {
            Optional<Message> maux = messageRepository.getMessage(m.getIdMessage());
            if (maux.isEmpty()) {
                return messageRepository.save(m);
            } else {
                return m;
            }
        }
    }

    public Message update(Message m) {
        if (m.getIdMessage() != null) {
            Optional<Message> maux = messageRepository.getMessage(m.getIdMessage());
            if (!maux.isEmpty()) {
                if (m.getMessageText() != null) {
                    maux.get().setMessageText(m.getMessageText());
                }
                if (m.getCabin() != null) {
                    maux.get().setCabin(m.getCabin());
                }
                if (m.getClient() != null) {
                    maux.get().setClient(m.getClient());
                }

                messageRepository.save(maux.get());
                return maux.get();
            } else {
                return m;
            }
        } else {
            return m;
        }
    }

    public boolean delete(int id) {
        Boolean mbool = false;
        Optional<Message> c = messageRepository.getMessage(id);
        if (c.isPresent()) {
            messageRepository.delete(c.get());
            mbool = true;
        }
        return mbool;
    }
}
