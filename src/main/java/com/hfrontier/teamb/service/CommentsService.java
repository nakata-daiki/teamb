package com.hfrontier.teamb.service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hfrontier.teamb.common.constant.SessionKeyConstant;
import com.hfrontier.teamb.mapper.CommentsMapper;
import com.hfrontier.teamb.repository.Comments;
import com.hfrontier.teamb.ui.CommentListModel.CommentsData;

@Service
@Configuration

public class CommentsService {
    @Autowired
    private CommentsMapper commentsMapper;

    /**
     * 投稿回数取得
     *
     * @param userID
     * @return countLog
     * @throws UnknownHostException
     */
    @Transactional
    public int getCountLog(String userID) throws UnknownHostException {
        int countLog = 0;

        if (userID != null) {
            countLog = commentsMapper.getCountLog(userID);
        }

        return countLog;

    }

    public void insertComment(String userID,int countLog,String comment){
        Comments comments = new Comments();
        comments.setComment(comment);
        comments.setCountLog(countLog);
        comments.setUserId(userID);
       insert(comments);



        //レコードの登録が完了したら1が返るようにしたい

    }

    /**
     * コメント登録
     *
     * @param comments
     */
    @Transactional
    public void insert(Comments comments) {
        commentsMapper.insert(comments);
    }

    public List<CommentsData> getCommentList(HttpSession session) {
        //すべてのコメント？
        List<Comments> al = commentsMapper.selectAll();
        List<CommentsData> list = new ArrayList<>();

        //すべてのコメントを一つ一つ確認
        for (Comments coment : al) {
            //表示用のコメントに加工していく
            CommentsData cd = new CommentsData();
            BeanUtils.copyProperties(coment, cd);
            Object userId = session.getAttribute(SessionKeyConstant.LOGIN_MEMBER_DATA);
            if(Objects.nonNull(userId)) cd.setMyComment(StringUtils.equals(coment.getUserId(), userId.toString()));
            list.add(cd);
        }
        return list;
    }

}