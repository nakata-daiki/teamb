package com.hfrontier.teamb.service;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hfrontier.teamb.mapper.CommentsMapper;
import com.hfrontier.teamb.repository.Comments;

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

}
