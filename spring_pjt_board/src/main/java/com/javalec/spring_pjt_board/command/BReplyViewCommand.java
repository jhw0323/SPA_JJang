package com.javalec.spring_pjt_board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.javalec.spring_pjt_board.dao.IDao;

public class BReplyViewCommand implements BCommand {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String , Object > map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String bId = request.getParameter("bId");
		
		/*
		BDao dao = new BDao();
		BDto dto = dao.reply_view(bId);
				
		model.addAttribute("reply_view",dto);
		*/
		
		IDao dao = sqlSession.getMapper(IDao.class);
		model.addAttribute(dao.reply_view(bId));
	}

}
