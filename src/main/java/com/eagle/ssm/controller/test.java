package com.eagle.ssm.controller;

import com.eagle.ssm.model.User;
import com.eagle.ssm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */
@Controller
@RequestMapping("/user")
public class test {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private UserService userService;

    @RequestMapping("/getUser")
    public String getUser(HttpServletRequest request, Model model) {
        try {
            logger.debug("查询所有用户信息");
            User user = userService.getUserById(2L);
            List<User> userList = userService.getAllUser();
            model.addAttribute("userList", userList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return "showUser";
    }
}
