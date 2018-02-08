package com.javalec.spring_pjt_board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javalec.spring_pjt_board.command.BCommand;
import com.javalec.spring_pjt_board.dto.BDto;
import com.javalec.spring_pjt_board.dto.MDto;
import com.javalec.spring_pjt_board.util.Constant;

@Controller
public class BController {

	BCommand command;
	
	@Autowired
	public SqlSession sqlSession;
	
	public JdbcTemplate template;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}
	/*회원정보 시작*/
	@RequestMapping("/")
	public String login(Model model){
		System.out.println("login() start");
		System.out.println("form :: " + model.containsAttribute("loginChk"));
		return "index";
	}
	
	@RequestMapping(value="/login" , method=RequestMethod.POST)
	public String main(HttpServletRequest request, Model model){
		System.out.println("main() start");
		String resultUrl = "";
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		MDto dto = new MDto();
		dto.setId(id);
		dto.setPassword(pw);
		parameters.put("dto", dto);
		String chkYn = sqlSession.selectOne("loginChk",parameters);
		if(chkYn.equals("Y")){
			MDto resultDto = sqlSession.selectOne("login",parameters);
			model.addAttribute("member",resultDto);
			resultUrl = "main";
		}else{
			resultUrl = "login";
			model.addAttribute("loginChk",chkYn);
		}
		return resultUrl;
	}
	
	@RequestMapping("/member_insertView")
	public String member_insertView(Model model){
		System.out.println("member_insertView() start");
		return "member_insert";
	}
	
	/*회원가입*/
	@RequestMapping(value="/member_insert" , method=RequestMethod.POST)
	public String member_insert(HttpServletRequest request , Model model){
		System.out.println("member_insert() start");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		MDto dto = new MDto();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("pw");
		String birth = request.getParameter("yyyy")+""+request.getParameter("mm")+""+request.getParameter("dd");
		String email = request.getParameter("email");		
		String phone = request.getParameter("phone1")+""+request.getParameter("phone2")+""+request.getParameter("phone3");
		String homePhone = request.getParameter("homePhone1")+""+request.getParameter("homePhone2")+""+request.getParameter("homePhone3");
		String address = request.getParameter("address");
		dto.setId(id);
		dto.setName(name);
		dto.setPassword(password);
		dto.setBirth(birth);
		dto.setEmail(email);
		dto.setPhone(phone);
		dto.setHomePhone(homePhone);
		dto.setAddress(address);
		parameters.put("dto", dto);
		sqlSession.insert("memberInsert",parameters);
		return "redirect:/";
	}
	
	/*회원가입 시 아이디 중복체크*/
	@RequestMapping(value = "/member_chk" , method=RequestMethod.GET)
	public void member_chk(@RequestParam("id") String id , HttpServletResponse response){
		String personJson;
		Map<String, Object> parameters = new HashMap<String, Object>();
		MDto dto = new MDto();
		dto.setId(id);
		parameters.put("dto", dto);
		String chkYn = sqlSession.selectOne("loginChk",parameters);
		
		personJson = "{\"chkYn\":\""+chkYn+"\"}";
		try{
			response.getWriter().print(personJson);
		}catch(IOException e) {
			System.out.println("member_chk 오류발생");
		}
	}
	
	/*회원정보 끝*/
	
	
	
	/*게시판 시작*/
	@RequestMapping("/list")
	public String list(Model model){
		System.out.println("list() start");
		
		//command = new BListCommand(sqlSession);
		//command.execute(model);
		List<BDto> list= sqlSession.selectList("list");
		model.addAttribute("list",list);
		return "list";
	
	}
	
	@RequestMapping("/write_view")
	public String write_view(Model model){
		System.out.println("write_view() start");
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model){
		System.out.println("write() start");
		/*
		model.addAttribute("request",request);
		command = new BWriteCommand();
		command.execute(model);
		*/
		Map<String, Object> parameters = new HashMap<String, Object>();
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		BDto dto = new BDto();
		
		dto.setbName(bName);
		dto.setbTitle(bTitle);
		dto.setbContent(bContent);
		parameters.put("dto", dto);
		sqlSession.update("write", parameters);
		
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model){
		System.out.println("content_view() start");

		Map<String, Object> parameters = new HashMap<String, Object>();
		String bId = request.getParameter("bId");
		BDto dto = new BDto();
		dto.setbId(Integer.parseInt(bId));
		parameters.put("dto", dto);
		sqlSession.update("upHit",parameters);
		BDto resultDto = sqlSession.selectOne("contentView",parameters);
		
		model.addAttribute("content_view",resultDto);
		return "content_view";
		
	}
	
	@RequestMapping(value="/modify" , method=RequestMethod.POST)
	public String modify(HttpServletRequest request , Model model){
		System.out.println("content_view() start");
		/*
		model.addAttribute("request",request);
		command = new BModifyCommand();
		command.execute(model);
		*/
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		BDto dto = new BDto();
		dto.setbId(Integer.parseInt(bId));
		dto.setbName(bName);
		dto.setbTitle(bTitle);
		dto.setbContent(bContent);	
		parameters.put("dto", dto);
		sqlSession.update("modify",parameters);
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request , Model model){
		System.out.println("reply_view() start");
		/*
		model.addAttribute("request",request);
		command = new BReplyViewCommand();
		command.execute(model);
		*/

		Map<String, Object> parameters = new HashMap<String, Object>();
		String bId = request.getParameter("bId");
		BDto dto = new BDto();
		dto.setbId(Integer.parseInt(bId));
		parameters.put("dto", dto);
		BDto resultDto = sqlSession.selectOne("replyView",parameters);
		
		model.addAttribute("reply_view",resultDto);
		
		return "reply_view";
	}

	@RequestMapping("/reply")
	public String reply(HttpServletRequest request , Model model){
		System.out.println("reply() start");
		/*
		model.addAttribute("request",request);
		command = new BReplyCommand();
		command.execute(model);
		*/
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String bGroup = request.getParameter("bGroup");
		String bStep = request.getParameter("bStep");
		String bIndent = request.getParameter("bIndent");
		BDto dto = new BDto();
		dto.setbId(Integer.parseInt(bId));
		dto.setbName(bName);
		dto.setbTitle(bTitle);
		dto.setbContent(bContent);	
		dto.setbGroup(Integer.parseInt(bGroup));
		dto.setbStep(Integer.parseInt(bStep));
		dto.setbIndent(Integer.parseInt(bIndent));
		parameters.put("dto", dto);
		
		sqlSession.update("replyShape",parameters);
		sqlSession.update("reply",parameters);
			
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request , Model model){
		System.out.println("delete() start");
		/*
		model.addAttribute("request",request);
		command = new BDeleteCommand();
		command.execute(model);
		*/
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String bId = request.getParameter("bId");
		BDto dto = new BDto();
		dto.setbId(Integer.parseInt(bId));
		parameters.put("dto",dto);
		sqlSession.delete("delete",parameters);
		
		return "redirect:list";
	}
	/*게시판 끝*/
}
