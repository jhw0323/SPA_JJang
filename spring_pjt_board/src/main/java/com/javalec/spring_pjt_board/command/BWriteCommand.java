package com.javalec.spring_pjt_board.command;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.javalec.spring_pjt_board.dao.BDao;
import com.javalec.spring_pjt_board.dao.IDao;

public class BWriteCommand implements BCommand {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		Map<String , Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");	
		
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		/*
		BDao dao = new BDao();
		dao.write(bName, bTitle , bContent );
		*/
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.write(bName, bTitle, bContent);
	}

}
