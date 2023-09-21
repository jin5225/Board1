package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	
	@Autowired
	private BoardService service;
	
	@Test
	public void TestGetList() {
		log.info("리스트서비스:"+service.getList(new Criteria(2,4)));
	}
	
	@Test
	public void Testregister() {
		BoardVO vo = new BoardVO();
		vo.setTitle("111");
		vo.setContent("냉무");
		vo.setWriter("나다");
		log.info("게시글 작성서비스:");
		service.register(vo);
	}
	
	@Test
	public void TestModify() {
		BoardVO vo = new BoardVO();
		vo.setTitle("111");
		vo.setContent("냉무");
		vo.setBno(41L);
		log.info("수정 서비스:"+service.modify(vo));
	}
	
	@Test
	public void TestRemove() {
		log.info("삭제 서비스:"+service.remove(22L));
	}
	
	@Test
	public void TestGet() {
		log.info("상세보기 서비스:"+service.get(3L));
	}
	
	@Test
	public void TestGood()	{
		log.info("좋아요 +1");
		service.good(11L);
	}
	
}
