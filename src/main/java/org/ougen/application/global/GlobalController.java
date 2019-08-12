package org.ougen.application.global;

import org.ougen.application.myannotation.TypeOneAnnotation;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: OuGen
 * Discription:
 * Date: 0:40 2019/7/20
 */
@ControllerAdvice(annotations = TypeOneAnnotation.class)
public class GlobalController {
    @InitBinder
    public void edit(WebDataBinder binder){
        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy-hh-mm-ss"),false));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public String handler(RuntimeException e){
        System.out.println(e);
        return "has a exception to da";
    }


}
