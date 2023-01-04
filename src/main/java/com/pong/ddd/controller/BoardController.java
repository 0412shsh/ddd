package com.pong.ddd.controller;

import com.pong.ddd.model.Board;
import com.pong.ddd.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    // 의존성 주입
    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/list")
    public String list(Model model){
        //list.html에 데이터를 넘겨 주고 싶다. → BoardRepository 사용
        List<Board> boards = boardRepository.findAll(); // 데이터를 다 가져올수 있다.
        model.addAttribute("boards",boards); // model에 담아 html로 보내버리기

        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model){
        model.addAttribute("board", new Board());
        return "board/form";
    }

    @PostMapping("/form")
    public String form(@ModelAttribute Board board){
        //post 경로를 타게 되면 스프링에서 자동적으로 Board board 안에 자동으로 데이터가 채워지고 이 정보를 가지고 다음 로직 실행
        //키값에 따라 이미 존재-update, 존재x-insert
        boardRepository.save(board);
        return "redirect:/board/list";
    }


}
