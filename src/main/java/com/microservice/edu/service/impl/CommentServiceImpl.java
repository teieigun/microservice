package com.microservice.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.edu.form.comments.CommentsReply;
import com.microservice.edu.form.comments.CommentsRoot;
import com.microservice.edu.form.comments.Liked;
import com.microservice.edu.mapper.CommentsMapper;
import com.microservice.edu.service.CommentService;

/**
 * 评论的service类，衔接控制层与持久化层
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentsMapper commentsMapper;

    @Override
    public List<CommentsRoot> findByOwnerIdService(String ownerId) {
        if(ownerId == null || ownerId.equals("")){
            return null;
        }
        return commentsMapper.findByOwnerId(ownerId);
    }

    @Override
    public List<CommentsRoot> findByPkService(String questionId) {
        if(questionId == null){
            return null;
        }
        return commentsMapper.findByOwnerPk(questionId);
    }

    @Override
    public List<CommentsRoot> findByLessonChapter(int lessonId, int chapterNo) {

        return commentsMapper.findByLessonChapter(lessonId,chapterNo);
    }

    @Override
    public List<CommentsRoot> findByLessonChapterPage(int lessonId, int chapterNo,int offset,int row) {

        return commentsMapper.findByLessonChapterPage(lessonId,chapterNo,offset,row);
    }


    @Override
    public List<CommentsReply> findByQuestionId(String questionId) {

        return commentsMapper.findByQuestionId(questionId);
    }

    @Override
    public boolean addRootCommentsService(CommentsRoot commentsRoot) {
        if(commentsRoot == null){
            return false;
        }
        return commentsMapper.addRootComments(commentsRoot);
    }

    @Override
    public boolean addSonCommentsService(CommentsReply commentsReply) {
        if(commentsReply == null){
            return false;
        }
        return commentsMapper.addSonComments(commentsReply);
    }

    @Override
    public boolean addLikedService(Liked liked) {
        if(liked == null)
            return false;
        return commentsMapper.addLiked(liked);
    }

    @Override
    public List<Liked> getListLikedService(String userId) {
        return commentsMapper.getListLiked(userId);
    }

    @Override
    public boolean updateLikedService(Liked liked) {
        return commentsMapper.updateLiked(liked);
    }

    @Override
    public Liked checkedLikeService(Liked liked) {
        return commentsMapper.checkedLike(liked);
    }

    @Override
    public boolean updateRootLikeNumService(Liked liked) {
        return commentsMapper.updateRootLikeNum(liked);
    }

    @Override
    public boolean updateReplyLikeNumService(Liked liked) {
        return commentsMapper.updateReplyLikeNum(liked);
    }
}
