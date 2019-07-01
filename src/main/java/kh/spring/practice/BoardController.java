package kh.spring.practice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.dao.BoardDAO;
import kh.spring.dao.MemberDAO;
import kh.spring.dto.BoardDTO;

@Controller
public class BoardController {
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private BoardDAO bdao;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping("boardProc")
	public ModelAndView board() {
		ModelAndView mav = new ModelAndView();
		int currentPage = 0;
		String currentPageResult = request.getParameter("currentPage");
		
		if(currentPageResult!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}else {
			currentPage = 1;
		}
		
		int recordCountPerPage=5;
		
		int end = currentPage * recordCountPerPage;
		int start = end - (recordCountPerPage-1);
		
		List<BoardDTO> BoardList = bdao.selectAll(start, end);
		mav.addObject("BoardList", BoardList);
		String resultNavi = bdao.getNavi(currentPage, recordCountPerPage);
		mav.addObject("navi", resultNavi);
		mav.setViewName("board");
		return mav;
	}
	
	@RequestMapping("boardWrite")
	public String boardWrite() {
		return "boardWriteFrom";
	}
	
	@RequestMapping("boardWriteProc")
	public ModelAndView  boardWriteProc(BoardDTO bdto){
		ModelAndView mav = new ModelAndView();
		int result = bdao.write(bdto);
		if(result==1) {
			mav.addObject("result", "작성 되었습니다.");
			mav.setViewName("boardWriteResult");
		}else {
			mav.addObject("result", "오류가 발생하였습니다.");
			mav.setViewName("boardWriteResult");
		}
		return mav;
	}
	
	@RequestMapping("boardDetail")
	public ModelAndView boardDetail() {
		ModelAndView mav = new ModelAndView();
		int no = Integer.parseInt(request.getParameter("no"));
		List<BoardDTO> list = bdao.selectDetail(no);
		mav.addObject("listDetail", list);
		mav.setViewName("boardDetail");
		return mav;
	}
	
	@RequestMapping("boardModifyProc")
	public ModelAndView boardModify(BoardDTO dto) {
		ModelAndView mav = new ModelAndView();
			int result = bdao.update(dto);
			String msg=null;
			if(result==1) {
				msg = "게시글이 수정 되었습니다."; 
			}else {
				msg = "게시글 수정 실패";
			}
				mav.addObject("msg", msg);
				mav.setViewName("boardModifyResult");
		return mav;
	}
	
}
