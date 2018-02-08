package com.javalec.spring_pjt_board.command;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.javalec.spring_pjt_board.dao.IDao;
	
public class BListCommand extends Command implements BCommand {
	
	@Autowired	
	private SqlSession sqlSession;
	
	public BListCommand() {
	}

	public BListCommand(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		/*
		BDao dao = new BDao();
		
		ArrayList<BDto> dtos = dao.list();
		model.addAttribute("list",dtos);
		*/
		IDao dao = sqlSession.getMapper(IDao.class);
		System.out.println("bbbb");
		
		model.addAttribute("list",dao.list());
		
	}
	@Override
	public void setSqlSession(SqlSession sqlSession) {
		super.setSqlSession(sqlSession);
	}	
}
