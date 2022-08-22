package com.ll.example.getTwoGetter.login.controller;

import com.ll.example.getTwoGetter.Board.domain.entity.Board;
import com.ll.example.getTwoGetter.Board.dto.BoardDto;
import com.ll.example.getTwoGetter.Board.service.BoardService;
import com.ll.example.getTwoGetter.login.Service.UserService;
import com.ll.example.getTwoGetter.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    UserService userService;

    @Autowired
    private BoardService boardService;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model, HttpSession session){
        if(userDetails != null){
            String username = userDetails.getUsername();
            User user = userService.findByUsename(username);
            model.addAttribute("user",user);
        }
        if(session.getAttribute("message")!=null){
            String message = (String) session.getAttribute("message");
            model.addAttribute("message", message);
        }

        // 마커띄우려고
        List<BoardDto> boardDtoList = boardService.getBoardList();
        model.addAttribute("boards",boardDtoList);
        return "index";

    }


//    @GetMapping("/assets/demo/chart-area-demo.js")
//    public String redirectHome(){
//        return "redirect:/";
//    }
}
