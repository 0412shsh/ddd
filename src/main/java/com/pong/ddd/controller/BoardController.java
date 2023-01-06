package com.pong.ddd.controller;

import com.pong.ddd.model.Board;
import com.pong.ddd.repository.BoardRepository;
import com.pong.ddd.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    // 의존성 주입
    @Autowired
    private BoardRepository boardRepository;

    // validator 의존성 주입
    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 9) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText){
        //list.html에 데이터를 넘겨 주고 싶다. → BoardRepository 사용
        //Page<Board> boards = boardRepository.findAll(PageRequest.of(0, 20)); // 데이터를 다 가져올수 있다.
        //boards.getTotalElements();
        //Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText,searchText,pageable);

        int startPage = Math.max(1, boards.getPageable().getPageNumber() -4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber()+4);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("boards",boards); // model에 담아 html로 보내버리기

        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long bno){
        //@RequestParam 파라미터를 통해 값을 전달
        if(bno == null) {
            //bno가 없으면 form 작성하도록 빈 객체 주기 
            model.addAttribute("board", new Board());
        } else {
            //bno가 있으면 jpa를 통해서 bno를 키값으로 해당 글 가져오기
            // 조회되는 값이 없을 수 있음 orElse 처리
            Board board = boardRepository.findById(bno).orElse(null);
            model.addAttribute("board",board);
            
        }


        return "board/form";
    }

    @PostMapping("/form")
    public String form(@Valid Board board, BindingResult bindingResult){
        //post 경로를 타게 되면 스프링에서 자동적으로 Board board 안에 자동으로 데이터가 채워지고 이 정보를 가지고 다음 로직 실행
        boardValidator.validate(board,bindingResult); //체크할 클래스명, bindingResult 전달
        if (bindingResult.hasErrors()) {
            return "board/form";
        }
        //키값에 따라 이미 존재-update, 존재x-insert
        boardRepository.save(board);
        return "redirect:/board/list";
    }


}
