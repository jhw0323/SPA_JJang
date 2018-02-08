package com.javalec.spring_pjt_board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.javalec.spring_pjt_board.dao.IDao;

public class BReplyCommand implements BCommand {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String,Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String bGroup = request.getParameter("bGroup");
		String bStep = request.getParameter("bStep");
		String bIndent = request.getParameter("bIndent");
		
		/*
		BDao dao = new BDao();
		dao.reply(bId,bName,bTitle,bContent,bGroup,bStep,bIndent);
		*/
		IDao dao = sqlSession.getMapper(IDao.class);
		
		dao.replyShape(bGroup, bStep);
		
		dao.reply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent);
	}

}
