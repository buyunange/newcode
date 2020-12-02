package com.jbj.service;

import com.jbj.dao.CommentDao;
import com.jbj.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    public int addComment(Comment comment) {
        return commentDao.addComment(comment);
    }
    public List<Comment> selectCommentByEntity(int entityId,int entityType){
        return commentDao.selectCommentByEntity(entityId,entityType);
    }
    public boolean deleteCommen(int id){
        return commentDao.update(1,id)>0;
    }
    public int getCommentCount(int entityId,int entityType){
        return commentDao.getCommentCount(entityId,entityType);
    }
    public Comment getCommentById(int id) {
        return commentDao.getCommentById(id);
    }
    public int getUserCommentCount(int userId) {
        return commentDao.getUserCommentCount(userId);
    }

}
