package com.project.service;

import com.project.domain.User;
import com.project.domain.dto.MessageDto;
import com.project.reps.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    public Page<MessageDto> messageList(Pageable pageable, String filter, User user){
        if (filter==null || filter.isEmpty()){
            return messageRepo.findAll(pageable, user);
        } else {
            return messageRepo.findByTag(pageable, filter, user  );
        }
    }

    public Page<MessageDto> messageListForUser(Pageable pageable, User currentUser, User author) {
        return messageRepo.findByUser(pageable, author, currentUser);
    }
}
