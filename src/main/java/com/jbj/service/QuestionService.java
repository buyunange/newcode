package com.jbj.service;

import com.jbj.dao.QuestionDao;
import com.jbj.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.security.PublicKey;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    @Autowired
    SensitiveService sensitiveService;
    public List<Question> getLatestQuestions(int userId,int offset,int limit){

        return questionDao.selectLatestQuestions(userId,offset,limit);
    }
    public int add(Question question){
        question.setContent(HtmlUtils.htmlEscape(question.getContent()));
        question.setTitle(HtmlUtils.htmlEscape(question.getTitle()));
        question.setTitle(sensitiveService.fileter(question.getTitle()));
        question.setContent(sensitiveService.fileter(question.getContent()));
        return questionDao.addQuestion(question)>0?question.getId():0;
    }

    public Question selectById(int id){
        return questionDao.selectById(id);
    }
    public int updateCommentCount(int id, int count) {
        return questionDao.updateCommentCount(id, count);
    }
}
