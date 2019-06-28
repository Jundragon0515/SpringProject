package kh.spring.dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.dto.MemberDTO;
@Component
public class MemberDAO {
	
	@Autowired
	private JdbcTemplate template;
	
	
	public int insert(MemberDTO dto , String path) {
		String sql = "insert into member values(member_seq.nextval,?,?,?,?)";
		return template.update(sql, dto.getId(), dto.getPw(), dto.getEmail(), path);
	}
	
	public int login(MemberDTO dto)throws Exception {
		String sql = "select count(*) from member where id=? and pw=?";
		return template.queryForObject(sql, new Object[] {dto.getId(), dto.getPw()}, Integer.class);
	}
	
	public int idCheck(MemberDTO dto)throws Exception {
		String sql = "select count(*) from member where id=?";
		System.out.println("id :"+ dto.getId());
			 int result = template.queryForObject(sql, new Object[] {dto.getId()}, Integer.class);
			 System.out.println("갯수 : " + result);
			 if(result==1) {
				 return 1;
			 }else {
				 return 0;
			 }
	}
	
	public String profile(MemberDTO dto)throws Exception {
		String sql = "select profile from member where id = ?";
			 return template.queryForObject(sql, new Object[] {dto.getId()}, String.class);
	}
}
