package com.example.demo.controller;

import com.example.demo.mapper.Mapper;
import com.example.demo.model.LOGIN;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class Controller_sm {
    @Autowired
    Mapper postMapper;


    @GetMapping("/first")
    public String Login(Model model) {

        List<LOGIN> a = postMapper.getAll();
        model.addAttribute("data", a);

        return "Posts";
    }

    @GetMapping("/first/{postId}")
    public String read(Model model, @PathVariable("postId") int postId) {

        LOGIN post = postMapper.getPost(postId);

        model.addAttribute("post", post);
        return "Post";
    }

    @PostMapping(value = "/create")
    public String create(Model model, @RequestParam("title") String title, @RequestParam("body") String body) {

        // System.out.println(title + " " + body);

        postMapper.insertData(title, body);

        return "redirect:/test/first";
    }

    @GetMapping("/delete/{postId}")
    public String delete(Model model,@PathVariable("postId") int postId){
        postMapper.deleteData(postId);
        return "redirect:/test/first";
    }

    @GetMapping("/Update/{postId}")
    public String Updating(Model model,@PathVariable("postId")int postId){
        LOGIN post = postMapper.getPost(postId);
        model.addAttribute("post", post);
        return "Update";
    }

    @PostMapping(value = "/Update")
    public String Updated(Model model, @PathVariable("title") String title, @PathVariable("body") String body,@PathVariable("postId") int postId){
        postMapper.updateData(title,body,postId);
        return "redirect:/test/first/{postId}";
    }



}
