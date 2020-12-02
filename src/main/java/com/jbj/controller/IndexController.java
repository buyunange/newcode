package com.jbj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@Controller
public class IndexController {
    @RequestMapping(path ={"/index/{id}/{groupId}"},method ={ RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String index(@PathVariable("id") int id,
                        @PathVariable("groupId") String groupId,
                        @RequestParam("type") String type,
                        @RequestParam("key") int key){
        return String.format("success %d / %s , t: %d / %s",id,groupId,key,type);
    }

    @RequestMapping("/vm")
    public  String template(Model model){
        model.addAttribute("name","zhangshan");
        List<String> list = new ArrayList<String>(3);
        list.add("red");
        list.add("yellow");
        list.add("blue");
        model.addAttribute("color",list);
        Map<String,Double> map = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            map.put(String.valueOf(i),Math.pow(i,2));
        }

        model.addAttribute("map",map);
        return "home";
    }
}
