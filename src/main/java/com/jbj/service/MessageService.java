package com.jbj.service;

import com.jbj.dao.MessageDao;
import com.jbj.model.Message;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageDao messageDao;
    @Autowired
    SensitiveService sensitiveService;
    public int addMessage(Message message){
        message.setContent(sensitiveService.fileter(message.getContent()));
        return messageDao.addMessage(message);
    }
    public  List<Message> getConversationDetail(String conversationId,int offset,int limit){
            return messageDao.getConversationDetail(conversationId,offset,limit);
    }
    public List<Message> getConversationList(int userId,int offset,int limit){
        return messageDao.getConversationList(userId,offset,limit);
    }
    public int getConvesationUnreadCount(int userId, String conversationId) {
        return messageDao.getConvesationUnreadCount(userId, conversationId);
    }

    public   void updateById(int userId, String  conversitionId,int has_read){
        messageDao.updateById(userId,conversitionId,has_read);
    }
}
