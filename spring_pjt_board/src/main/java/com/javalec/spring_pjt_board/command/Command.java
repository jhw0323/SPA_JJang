package com.javalec.spring_pjt_board.command;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;

public class Command {
	
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
}
