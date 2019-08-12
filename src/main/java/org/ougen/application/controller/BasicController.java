package org.ougen.application.controller;


import org.ougen.application.model.Model;
import org.ougen.application.model.User;
import org.ougen.application.myannotation.TypeOneAnnotation;
import org.ougen.application.util.ModelUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.view.json.AbstractJackson2View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.beans.PropertyEditor;
import java.io.*;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

/**
 * Author: OuGen
 * Discription:
 * Date: 13:26 2019/7/19
 */
@Controller
@TypeOneAnnotation
//@SessionAttributes("user")
public class BasicController  extends MappingJackson2JsonView {
    /**
     spring mvc 中
     */
    @RequestMapping(value = "/start/{id}/{name}/{user}")
    @ResponseBody
    public String start(HttpServletRequest request, @RequestHeader("Accept") String accpt,
//                        @MatrixVariable(name = "q",pathVar = "id",required = false) int q,
//                        @MatrixVariable(name = "p",pathVar = "name",required = false) String s,
                        @RequestParam Map<String,String> map,
                        @ModelAttribute("user")User user){
        System.out.println(map);
        System.out.println(user);
//        System.out.println(q+s);
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()){
//            String name = headerNames.nextElement();
//            System.out.println(name+":"+request.getHeader(name));
//        }
        System.out.println(accpt);
        return "name:ougen";
    }
    @ModelAttribute
    public User sets(){
        User user =  new User();
        user.setName("xxxxx");
        user.setAge(3333);
        return user;
    }

    @RequestMapping("/sessions/{user}")
    @ResponseBody
    public String sessions(@Valid @ModelAttribute(value = "user",binding = false)User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "error";
        }
        System.out.println(user);
        return "getsession";
    }
    @RequestMapping("/login")
    public String login(HttpServletRequest request,HttpServletResponse response,@RequestParam("date")Date date){
        Cookie[] cookies = request.getCookies();
        System.out.println(date);
        for (Cookie cookie : cookies){
            if ("phone".equals(cookie.getName())){
                System.out.println( "您已经无需注册");
            }
        }
        Cookie c = new Cookie("phone",request.getParameter("phone"));
        c.setMaxAge(1000);
        c.setPath("/");
        response.addCookie(c);
        return "login1";
    }

    @RequestMapping(value = "/upload",consumes = "multipart/form-data")
    @ResponseBody
    public String upload(@RequestParam("file")MultipartFile file) throws IOException {
        if (!file.isEmpty()){
            byte[] by = file.getBytes();
            String name = file.getOriginalFilename();
            file.transferTo(new File("/data/"+name));
            return "its  ok";
        }
        return "its no ok";
    }

//    @InitBinder
//    public void format(WebDataBinder webDataBinder){
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        simpleDateFormat.setLenient(false);
////        webDataBinder.addCustomFormatter(new DateFormatter());
//        webDataBinder.registerCustomEditor(Date.class,new CustomDateEditor(simpleDateFormat,false));
//    }
//    @InitBinder
//    public void otherformat(WebDataBinder binder){
//        binder.addCustomFormatter(new DateFormatter("yyyy/MM/dd"));
//    }

    @RequestMapping("/adviceTest")
    @ResponseBody
    public String adviceTest(@RequestParam("flag")boolean flag){
        if (flag){
            throw new RuntimeException();
        }else {
            return "it is ok";
        }
    }

    @RequestMapping("/il8n/test")
    @ResponseBody
    public Model il8ntest(HttpServletRequest request,HttpServletResponse response,
                          @RequestParam(value = "lang",defaultValue = "ZH")String lang){
        /**
        RequestContext requestContext = new RequestContext(request);
        String title = requestContext.getMessage("title");
        String date = requestContext.getMessage("date");
        return ModelUtil.getModel("il8n",1,"title:"+title+"and date:"+date);
         */
//        if (lang.equals("ZH")){
//            Locale locale = Locale.CHINA;
//            (new CookieLocaleResolver()).setLocale(request,response,locale);
//        } else if (lang.equals("US")){
//            Locale locale = Locale.US;
//            (new CookieLocaleResolver()).setLocale(request,response,locale);
//        }else {
//            (new CookieLocaleResolver()).setLocale(request,response,LocaleContextHolder.getLocale());
//        }
//        (new CookieLocaleResolver()).setLocale (request, response, Locale.US);
        RequestContext requestContext = new RequestContext(request);
        String title = requestContext.getMessage("title");
        String date = requestContext.getMessage("date");

        Model model =  ModelUtil.getModel("il8n",1,"title:"+title+"and date:"+date);
        request.setAttribute("model",model);
        return model;
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> download() throws IOException {
        InputStream is = new FileInputStream(this.getClass().getClassLoader().getResource("/spring/dispacher-servlet.xml").getFile());
        byte[] body = new byte[is.available()];
        is.read(body);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","attchement;filename=dispacher-servlet.xml");
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<byte[]>(body,headers,httpStatus);
    }

    @RequestMapping("/beanNameUrl")
    public ModelAndView getBean(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("asnyController");
        return modelAndView;
    }
}
