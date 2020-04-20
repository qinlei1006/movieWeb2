package com.lovo.web.controller;

import com.lovo.web.entity.UserEntity;
import com.lovo.web.service.IUserService;
import com.lovo.web.util.StringUtil;
import com.lovo.web.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Usercontroller {
 @Autowired
 private IUserService userService;

   @RequestMapping("login")
    public ModelAndView login(UserVo vo, HttpServletRequest request){
         ModelAndView mv=new ModelAndView();
           //查询是否存在
      UserEntity user= userService.userInfo(vo.getUserName(),vo.getPassword());

             if(null!=user){
                 //如果登录成功就放入session
              request.getSession().setAttribute("user",vo);
              mv.addObject("loginInfo", StringUtil.LOGIN_OK);
               //跳转到主页
                 RedirectView rv=new RedirectView("getListTicket");
                 mv.setView(rv);
             }else {
                 //失败调整到登录页面
                 mv.setViewName("login");
                 mv.addObject("loginInfo",StringUtil.LOGIN_NO);

             }

         return  mv;
    }




}
