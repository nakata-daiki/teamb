package com.hfrontier.teamb.ui;

import java.util.List;

import com.hfrontier.teamb.repository.Comments;

import lombok.Data;

@Data
public class CommentListModel {

	private List<CommentsData> list;

	public class CommentsData extends Comments {
		private boolean myComment;

		public boolean isMyComment() {
			return myComment;
		}

		public void setMyComment(boolean myComment) {
			this.myComment = myComment;
		}

	}

}
