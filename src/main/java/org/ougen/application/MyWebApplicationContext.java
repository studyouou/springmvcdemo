//package org.ougen.application;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.context.support.XmlWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
//
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//
///**
// * Author: OuGen
// * Discription:
// * Date: 11:36 2019/7/19
// */
//public class MyWebApplicationContext implements WebApplicationInitializer {
//    @Override
//    public void onStartup(ServletContext context) throws ServletException {
//        XmlWebApplicationContext configWebApplicationContext = new XmlWebApplicationContext();
//        configWebApplicationContext.setConfigLocation("classpath:spring/dispacher-servlet.xml");
//        configWebApplicationContext.refresh();
//        DispatcherServlet dispatcherServlet = new DispatcherServlet(configWebApplicationContext);
//        ServletRegistration.Dynamic registration = context.addServlet("servlet",dispatcherServlet);
//        registration.setLoadOnStartup(1);
//        registration.addMapping("/");
//
//    }
//
////    @Override
////    protected String[] getServletMappings() {
////        return new String[]{"/"};
////    }
////
////    @Override
////    protected Class<?>[] getRootConfigClasses() {
////        return null;
////    }
////
////    @Override
////    protected Class<?>[] getServletConfigClasses() {
////        return null;
////    }
//}
