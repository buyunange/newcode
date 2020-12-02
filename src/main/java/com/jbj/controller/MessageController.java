package com.jbj.controller;

import com.jbj.model.HostHolder;
import com.jbj.model.Message;
import com.jbj.model.User;
import com.jbj.model.ViewObject;
import com.jbj.service.MessageService;
import com.jbj.service.UserService;
import com.jbj.util.WendaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class  MessageController {
    private static final Logger logger = LoggerFactory.getLogger(Message.class);
    @Autowired
    MessageService messageService;
    @Autowired
    HostHolder hostHolder;
    @Autowired
    UserService userService;

    //返回回答问题列表
    @RequestMapping(value = "/msg/list",method = RequestMethod.GET)
    public String getConversationList(Model model){
        try{
            if(hostHolder.getUser()==null){
                return "redirect:/reglogin";
            }else{
                int userId = hostHolder.getUser().getId();

                List<ViewObject> list = new ArrayList<>();
                List<Message> messageList = messageService.getConversationList(userId,0,10);

                for(Message message:messageList){
                    ViewObject vos = new ViewObject();
                    vos.set("message",message);
                    int targetId = message.getFromId() == userId ? message.getToId() : message.getFromId();
                    vos.set("user",userService.selectById(targetId));
                    vos.set("unread", messageService.getConvesationUnreadCount(userId, message.getConversationId()));
                    list.add(vos);
                }
                model.addAttribute("conversations",list);
            }

        }
        catch (Exception e){
            logger.error("私信未响应"+e.getMessage());
        }
        return "letter";
    }

    @RequestMapping(value = "/msg/detail",method = RequestMethod.GET)
    public String getConversationDeatil(Model model, @RequestParam("conversationId") String conversationId){
        try{
            List<ViewObject> list = new ArrayList<>();
            List<Message> messageList = messageService.getConversationDetail(conversationId,0,10);
            messageService.updateById(hostHolder.getUser().getId(),conversationId,1);
            for(Message message:messageList){
                ViewObject vos = new ViewObject();
                vos.set("message",message);
                vos.set("user",userService.selectById(message.getFromId()));
                list.add(vos);
            }
            model.addAttribute("messages",list);
            model.addAttribute("test",5);
        }
        catch (Exception e){
            logger.error("查询失败"+e.getMessage());
        }
        return "letterDetail";
    }
    @RequestMapping(value = "/msg/addMessage", method = RequestMethod.POST)
    @ResponseBody
    public String addMessage(@RequestParam("toName") String toName,
                             @RequestParam("content") String content) {
        try {
            if (hostHolder.getUser() == null) {
                return WendaUtil.getJSONString(999, "未登录");
            } else {
                User user = userService.selectByName(toName);
                if(user == null){
                    return WendaUtil.getJSONString(1,"用户不存在");
                }
                Message message = new Message();
                message.setContent(content);
                message.setCreatedDate(new Date());
                message.setFromId(hostHolder.getUser().getId());
                message.setToId(user.getId());
                messageService.addMessage(message);
                return WendaUtil.getJSONString(0,"发送成功");
            }
        }catch (Exception e){
            logger.error("添加评论失败"+e.getMessage());
            return WendaUtil.getJSONString(1,"发送失败");
        }
    }

}
