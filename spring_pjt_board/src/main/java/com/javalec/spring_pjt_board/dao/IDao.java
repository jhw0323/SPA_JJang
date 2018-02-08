package com.javalec.spring_pjt_board.dao;

import java.util.ArrayList;

import com.javalec.spring_pjt_board.dto.BDto;

public interface IDao {
	public BDto contentView(String strId);
	public void write(String bName, String bTitle, String bContent);
	public ArrayList<BDto> list();
	public void modify(String bId, String bName, String bTitle, String bContent);
	public void delete(String strId);
	public BDto reply_view(String strId);
	public void reply( String bId,  String bName,  String bTitle,  String bContent,  String bGroup,  String bStep,  String bIndent);
	public void upHit(String bId);
	public void replyShape(String strGroup,String strStep);
}
