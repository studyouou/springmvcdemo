package org.ougen.application.asny;

import org.ougen.application.model.Model;
import org.ougen.application.util.ModelUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

import java.util.concurrent.Callable;

/**
 * Author: OuGen
 * Discription:
 * Date: 11:04 2019/7/22
 */
@RestController
public class AsnyController extends MappingJackson2JsonView {
    @RequestMapping("/asyn")
    public DeferredResult<Model> asyncTask(){
        DeferredResult<Model> deferredResult = new DeferredResult<Model>(15000L);
        System.out.println("请求线程:"+Thread.currentThread().getId());
        doSometh(deferredResult);
        deferredResult.onTimeout(new Runnable() {
            @Override
            public void run() {
                System.out.println("超时执行了,执行线程是:"+Thread.currentThread().getId());
            }
        });
        return deferredResult;
//        return (()-> {  //第一种方式是返回callable
//            for (int i = 0; i < 20; i++) {
//                Thread.sleep(500);
//                System.out.println(i);
//            }
//            Model model = ModelUtil.getModel("异步处理线程:" + Thread.currentThread().getId() + "处理完成", 1, "chuli");
//            return model;
//        });
    }
    private void doSometh(DeferredResult<Model> deferredResult){
        new Thread(()->{
            try {
                for (int i=0;i<20;i++){
                    Thread.sleep(500);
                    System.out.println(i);
                }
                Model model = ModelUtil.getModel("异步处理线程:"+Thread.currentThread().getId()+"处理完成",1,"chuli");
                deferredResult.setResult(model);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
