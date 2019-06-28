package kh.spring.practice;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.dao.MemberDAO;
import kh.spring.dto.MemberDTO;

@Controller
public class HomeController {
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private HttpSession session;

	@RequestMapping("/")
	public String index() {
		return "home";
	}

	@RequestMapping("joinForm")
	public String join() {
		return "join";
	}

	@RequestMapping("joinProc")
	public String join(MemberDTO dto , MultipartFile profile) {
		
		String filePath = session.getServletContext().getRealPath("/resources");
		System.out.println(filePath);
		try {
			File f = new File(filePath+"/"+profile.getOriginalFilename());
			profile.transferTo(f);
			String path = "resources/"+f.getName();	
			System.out.println(path);
			dao.insert(dto,path);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
			return "home";
	}

		@RequestMapping("loginProc")
		public String login(MemberDTO dto) {
			try {
				 int result = dao.login(dto);
				if(result > 0) {
					session.setAttribute("loginId", dto.getId());
					String profile = dao.profile(dto);
					System.out.println(profile);
/*					new ModelAndView().setViewName("index");
					new ModelAndView().addObject("profile", profile);*/
					
					return "index";
				}else {
					return "sdfsdfsdf";
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "sfsdf";
			}
		}

		@ResponseBody
		@RequestMapping(value="idCheck.do",  produces = "application/text; charset=utf8")
		public String idCheck(MemberDTO dto) {
			try {
				int result = dao.idCheck(dto);
				System.out.println("result :" + result);
				if(result==1) {
					System.out.println("중복");
					return "ID가 중복되었습니다.";

				}else {
					System.out.println("사용가능");
					return "사용가능한 ID입니다.";
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "오류";
			}
		}
		
		@RequestMapping("logoutProc")
		public String logout(HttpSession session) {
			session.removeAttribute("loginId");
			return "home";
		}
		
	}
