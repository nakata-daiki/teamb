package com.hfrontier.teamb.service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hfrontier.teamb.mapper.CommentsMapper;
import com.hfrontier.teamb.repository.Comments;
import com.hfrontier.teamb.ui.CommentListModel.CommentsData;

import lombok.Data;

@Data
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

	/**
	 * コメント登録
	 *
	 * @param comments
	 */
	@Transactional
	public void update(String id, String comment) {
		Comments c = new Comments();
		c.setId(Integer.parseInt(id));
		c.setComment(comment);
		commentsMapper.updateByPrimaryKeySelective(c);
	}

	public Comments getComment(String id) {
		return commentsMapper.selectByPrimaryKey(Integer.parseInt(id));
	}

	public List<CommentsData> getCommentList() {
		//すべてのコメント？
		List<Comments> al = commentsMapper.selectAll();
		List<CommentsData> list = new ArrayList<>();

		//すべてのコメントを一つ一つ確認
		for (Comments coment : al) {
			//表示用のコメントに加工していく
			CommentsData cd = new CommentsData();
			cd.setId(coment.getId());
			cd.setComment(coment.getComment());
//			cd.setMyComment(coment.getUserId())
			list.add(cd);
		}
		return list;
	}

}
