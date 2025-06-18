package com.rai69.literalura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class ControllerInvoker {
    @Autowired
    private ApplicationContext context;

    public void invokeAllControllers() {
        Map<String, Object> controllers = context.getBeansWithAnnotation(org.springframework.web.bind.annotation.RestController.class);
        System.out.println("--- Controllers found: ---");
        controllers.forEach((name, bean) -> {
            System.out.println("Controller: " + bean.getClass().getSimpleName());
        });
    }
}
