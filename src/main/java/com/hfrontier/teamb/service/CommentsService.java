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
@Transactional
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
	public int getCountLog(String userID) throws UnknownHostException {
		int countLog = 0;

		if (userID != null) {
			countLog = commentsMapper.getCountLog(userID);
		}

		return countLog;

	}

	public void insertComment(String userID,int countLog,String comment){
		int insertNumber = 0;

		//レコードの登録が完了したら1が返るようにしたい

	}

	/**
	 * コメント登録
	 *
	 * @param comments
	 */
	public void insert(Comments comments) {
		commentsMapper.insert(comments);
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
