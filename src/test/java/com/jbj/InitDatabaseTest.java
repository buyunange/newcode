package com.jbj;

import ch.qos.logback.classic.pattern.ClassNameOnlyAbbreviator;
import com.jbj.dao.CommentDao;
import com.jbj.dao.MessageDao;
import com.jbj.dao.NewsDao;
import com.jbj.dao.QuestionDao;
import com.jbj.model.*;
import com.jbj.service.CommentService;
import com.jbj.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
//@Sql("/init-schema.sql")
public class InitDatabaseTest {
    /**
     * Rigorous Test :-)
     */
    @Autowired
    UserService userDao;
    @Autowired
    NewsDao newsDao;
    @Autowired
    QuestionDao questionDao;
    @Autowired
    CommentService commentService;
    @Autowired
    MessageDao messageDao;

    @Test
    public void shouldAnswerWithTrue() {
        Random random = new Random();
        Date date = new Date();
       /* for (int i = 0; i < 11; i++) {
            //注解方式操纵User表
            User user = new User();
            user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
            user.setName(String.format("USER%d", i+1));
            user.setPassword("");
            user.setSalt("");
            userDao.save(user);
            user.setPassword("jbj520psh");
            userDao.updateById(user);
            //操作question
            Question question = new Question();
            question.setCommentCount(i);
            question.setContent(String.format("dsadsadas %d",i));
            question.setCommentCount(i);
            question.setTitle(String.format("TITLE{%d}",i));
            date.setTime(date.getTime()+1000*60*60*i);
            question.setCreatedDate(date);
            question.setUserId(i);
            questionDao.addQuestion(question);
            //操作News表
            *//*News news = new News();
            news.setComment_count(i);
            date.setTime(date.getTime()+ 1000*3600*5*i);
            news.setCreated_date(date);
            news.setImage(String.format("http://images.nowcoder.com/head/%dt.png",random.nextInt(1000)));
            news.setLike_count(i+1);
            news.setUser_id(i+1);
            news.setTitle(String.format("TITLE{%d}",i));
            news.setLink(String.format("http://www.newcode.com/%d.html",i));
            newsDao.addNews(news);*//*
        }*/
       //userDao.deleteById(1);
        //Assert.assertEquals("USER2",userDao.selectById(2).getName());
        //System.out.println(questionDao.selectLatestQuestions(0,9,10));

        System.out.println(messageDao.getConversationList(21,0,10));
    }
}
