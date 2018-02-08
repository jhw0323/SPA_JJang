package com.javalec.spring_pjt_board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.javalec.spring_pjt_board.dto.BDto;
import com.javalec.spring_pjt_board.util.Constant;

public class BDao implements IDao{
	
	DataSource dataSource;
	
	JdbcTemplate template = null;
	
	public BDao() {
		template = Constant.template;
	}

	public BDto contentView(String strId) {
		
		upHit(strId);
		
		String query = "select * from mvc_board where bid= " + strId;
		return (BDto)template.queryForObject(query,new BeanPropertyRowMapper<BDto>(BDto.class));
	
	}

	public void write(final String bName, final String bTitle, final String bContent){

		template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String query = "INSERT INTO MVC_BOARD(bId, bName, bTitle , bContent , bHit , bGroup , bStep , bIndent) VALUES(mvc_board_seq.nextval , ? , ? , ? , 0 , mvc_board_seq.currval , 0 , 0)";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, bName);
				pstmt.setString(2, bTitle);
				pstmt.setString(3, bContent);
				return pstmt;
			}
		});
		
	}
	
	public ArrayList<BDto> list(){
		
		String query = "SELECT bId , bName, bTitle , bContent , bDate , bHit , bGroup , bStep , bIndent FROM MVC_BOARD ORDER BY bGroup desc , bStep asc"; 
		return (ArrayList<BDto>)template.query(query, new BeanPropertyRowMapper<BDto>(BDto.class));
		
	}

	public void modify(final String bId, final String bName, final String bTitle, final String bContent) {
		String query = "UPDATE MVC_BOARD SET bName = ? , bTitle = ? , bContent = ? WHERE bId = ?";
		template.update(query,new PreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1,bName);
				ps.setString(2,bTitle);
				ps.setString(3,bContent);
				ps.setInt(4,Integer.parseInt(bId));
			}
		});
		
	}
	
	public void delete(final String strId) {
		String query = "DELETE FROM MVC_BOARD WHERE bId = ?";
		template.update(query, new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, Integer.parseInt(strId));
			}
			
		});
		
	}

	public BDto reply_view(String strId) {
		String query = "select * from mvc_board where bId = " + strId;
		return (BDto)template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));
		
	}
	
	public void reply(final String bId, final String bName, final String bTitle, final String bContent, final String bGroup, final String bStep, final String bIndent){
		// TODO Auto-generated method stub		
		
		replyShape(bGroup,bStep);
		
		String query = "INSERT INTO MVC_BOARD(bId, bName,bTitle,bContent,bGroup,bStep,bIndent) values(mvc_board_seq.nextval , ?, ? ,?, ?, ?, ?)";
		template.update(query , new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bName);
				ps.setString(2, bTitle);
				ps.setString(3, bContent);
				ps.setInt(4, Integer.parseInt(bGroup));
				ps.setInt(5, Integer.parseInt(bStep)+1);
				ps.setInt(6, Integer.parseInt(bIndent)+1);
			}
		});
		
	}
	
	public void upHit( final String bId) {
		// TODO Auto-generated method stub
		
		String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
		template.update(query, new PreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1	, Integer.parseInt(bId));
			}
		});
		
	}

	public void replyShape( final String strGroup,final String strStep) {

		String query = "update mvc_board set bStep = bStep + 1 where bGroup = ? and bStep > ?";
		template.update(query, new PreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, Integer.parseInt(strGroup));
				ps.setInt(2, Integer.parseInt(strStep));
			}
		});
		
	}

}
