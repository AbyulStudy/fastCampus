package org.example.mvc;

import org.example.mvc.controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping implements HandlerMapping {
    // /users 로 접속 할 경우 UserController 접속 해줘 형태로 작성하기 위함
    // key : /user 
    // value : UserController
    private Map<HandlerKey, Controller> mapping = new HashMap<>();

    void init() {
//        mapping.put(new HandlerKey(RequestMathod.GET, "/"), new HomeController());
        mapping.put(new HandlerKey(RequestMathod.GET, "/users"), new UserListController());
        mapping.put(new HandlerKey(RequestMathod.POST, "/users"), new UserCreateController());
        mapping.put(new HandlerKey(RequestMathod.GET, "/user/form"), new ForwardController("/user/form"));
    }

    public Controller findHandler(HandlerKey handlerKey) {
        return mapping.get(handlerKey);
    }

}
