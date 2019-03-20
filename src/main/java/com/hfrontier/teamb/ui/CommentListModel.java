package com.hfrontier.teamb.ui;

import java.util.List;

import com.hfrontier.teamb.repository.Comments;

import lombok.Data;

@Data
public class CommentListModel {

	private List<CommentsData> list;

	public static class CommentsData extends Comments {
		private boolean myComment;

		public boolean isMyComment() {
			return myComment;
		}

		public void setMyComment(boolean myComment) {
			this.myComment = myComment;
		}

	}

	public List<CommentsData> getList() {
		return list;
	}

	public void setList(List<CommentsData> list) {
		this.list = list;
	}

}
