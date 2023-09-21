package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/**")
@AllArgsConstructor
public class BoardController {
	
	BoardService service; //생성자 주입

	@GetMapping("/list") //전체 목록 board/list (get)
	public void list(Model model,Criteria cri) {
		log.info("list url 요청..");
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, service.count(cri)));
		// -> board/list.jsp
	}
	
	@GetMapping("/chart")
	public void chart()	{
		log.info("chart url 요청");
	}
	
	
	// 등록(작성글-BoardVO) board/register (post)   <- 입력화면 (get)
	@PostMapping("/register")
	public String register(BoardVO vo,RedirectAttributes rttr, Criteria cri) {
		log.info("register url post 요청");
		service.register(vo);
		rttr.addFlashAttribute("oper", "create");
		rttr.addFlashAttribute("result", vo.getBno()); // why? 데이터(작성글번호) 한번만 전송
		return "redirect:/board/list?pageNum="+cri.getPageNum()+"&amount="+cri.getAmount();	 //why? redirect가 없으면 jsp이고 
									   //redirect 가 있으면 요청
	}
	
	@GetMapping({"/register","/remove"})
	public void register() {
		
	}

	@GetMapping("/modify")
	public void modify(Long bno,Model model, Criteria cri) {
		log.info("수정화면 요청");
		model.addAttribute("board",service.get(bno));
		model.addAttribute("cri", cri);
	}
	

	// 조회(글번호-bno) board/get (get) //p218
	@GetMapping("/get")
	public void get(Long bno,Model model, Criteria cri) {
		log.info("글상세보기 url 요청");
		model.addAttribute("board", service.get(bno));
		model.addAttribute("cri", cri);
		// -> board/get.jsp
	}
	
	
	// 삭제(글번호-bno) board/remove (post)  <- 입력화면(get)
	@PostMapping("/remove")
	public String remove(Long bno,RedirectAttributes rttr, Criteria cri) {
		log.info("삭제 url 요청");
		if(service.remove(bno)) { //이상없으면 result 이름으로 success 라는 문자 전송
			rttr.addFlashAttribute("oper", "remove");
			rttr.addFlashAttribute("result", bno); 
		}
		return "redirect:/board/list?pageNum="+cri.getPageNum()+"&amount="+cri.getAmount();	
	}

	
	// 수정(수정글-BoardV) board/modify (post) <- 입력화면(get)
	@PostMapping("/modify")
	public String modify(BoardVO vo,RedirectAttributes rttr, Criteria cri) {
		log.info("수정 url 요청:");
		if(service.modify(vo)) {
			rttr.addFlashAttribute("oper", "modify");
			rttr.addFlashAttribute("result", vo.getBno()); 
		}
		return "redirect:/board/list?pageNum="+cri.getPageNum()+"&amount="+cri.getAmount();	
	}
	
	@GetMapping("/good")
	public String good(BoardVO vo, Criteria cri, Long bno)	{
		service.good(bno);
		return "redirect:/board/get?bno="+vo.getBno()+"&pageNum="+cri.getPageNum()+"&amount="+cri.getAmount();
	}
 
}





