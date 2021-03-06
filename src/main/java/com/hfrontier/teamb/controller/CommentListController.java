package com.hfrontier.teamb.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hfrontier.teamb.common.constant.Constant;
import com.hfrontier.teamb.service.CommentsService;
import com.hfrontier.teamb.ui.CommentListModel;
@Controller
public class CommentListController {
    @Autowired
    private AbstractApplicationContext context;
    @Autowired
    private HttpSession session;
    @Transactional
    @RequestMapping(value = { Constant.COMMENTLIST }, method = RequestMethod.GET)
    public @ResponseBody ModelAndView getCommentList(
            ModelAndView model,
            HttpServletRequest request,
            HttpServletResponse response) {
        //サービス生成
        CommentsService commentservice = context
                .getBean(CommentsService.class);
        //モデルのインスタンス作成
        CommentListModel commentListModel = new CommentListModel();
        //サービスからとってきたコメントリストをモデルに詰める
        commentListModel.setList(commentservice.getCommentList(session));
        HttpSession session = request.getSession(true);

        commentListModel.setUserId(String.valueOf(session.getAttribute("LOGIN_MEMBER_DATA")));
        model.setViewName("HTML/board");
        model.addObject("commentListModel", commentListModel);

        return model;
    }
}