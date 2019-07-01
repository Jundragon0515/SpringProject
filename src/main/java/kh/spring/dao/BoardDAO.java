package kh.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import kh.spring.dto.BoardDTO;

@Component
public class BoardDAO {
	@Autowired
	private JdbcTemplate template;
	
	public int write(BoardDTO dto) {
		String sql="insert into board values(board_seq.nextval, ?, ?, ?, default)";
		return template.update(sql, dto.getId(), dto.getTitle(), dto.getText());
	}
	
	public List<BoardDTO> selectAll(int start, int end){
		String sql = "select * from (select row_number() over(order by seq desc) as rown, board.* from board) where rown between ? and ?";
		List<BoardDTO> list = template.query(sql, new Object[] {start, end} ,new RowMapper<BoardDTO>() {

			@Override
			public BoardDTO mapRow(ResultSet rs, int rn) throws SQLException {
				BoardDTO bdto = new BoardDTO(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
				return bdto;
			}
		});
		return list;
	}
	
	public List<BoardDTO> selectDetail(int seq){
		String sql = "select * from board where seq = ?";
		List<BoardDTO> list = template.query(sql, new Object[] {seq}, new RowMapper<BoardDTO>() {
			@Override
			public BoardDTO mapRow(ResultSet rs, int rn) throws SQLException {
				BoardDTO bdto = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
				return bdto;
			}
		});
		return list ;
	}
	
	public int update(BoardDTO dto) {
		String sql = "update board set title = ?, text=?  where seq=?";
		return template.update(sql, dto.getTitle(), dto.getText(), dto.getNo());
	}
	
	
	public int recordTotalCount() {
		String sql = "select count(*) from board";
		return template.queryForObject(sql, new Object[] {}, Integer.class);
	}
	
	
	public String getNavi(int currentPage, int recordCountPerPage) {
		
		int recordTotalCount = this.recordTotalCount(); //레코드 수
		
		int naviCountPerPage = 5; // 한 페이지 네비 개수

		int pageTotalCount=0;


		if(recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		}else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}


		if(currentPage < 1) {
			currentPage = 1;
		}else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}


		int startNavi = (currentPage -1) / naviCountPerPage * naviCountPerPage + 1;
		int endNavi = startNavi + (naviCountPerPage-1);
		// 현재 위치에 따른 네비 시작과 끝을 구하기

		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		//네비 끝값이 최대 페이지 번호를 넘어가면 최대 페이지번호로 네비 끝값을 설정한다.


		boolean needPrev = true;
		boolean needNext = true;

		if(startNavi == 1) {
			needPrev=false;
		}

		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		
	
		StringBuilder sb = new StringBuilder();
		String bootTag = "<button type='button' class='btn btn-outline-secondary'>";
		String link = "<a href='boardProc?currentPage=";
		
		if(needPrev) {
			int prev = startNavi -1;
			sb.append(bootTag + link + prev + "'" + ">" + "<< Prev " + "</a></button>");
		}

		for(int i = startNavi; i <= endNavi; i++) {
			sb.append(bootTag + link + i + "'" + ">" + i + "</a></button>");
		}
		if(needNext) {
			int next = endNavi + 1;
			sb.append(bootTag + link + next + "'" + ">" + " Next >> " + "</a></button>");
		}
		return sb.toString();
	}
	
}
