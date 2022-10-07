package org.example.mvc;

import org.example.mvc.controller.RequestMathod;
import org.example.mvc.view.JspViewResolver;
import org.example.mvc.view.ModelAndView;
import org.example.mvc.view.View;
import org.example.mvc.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    private List<HandlerMapping> handlerMappings;
    private List<HandlerAdapter> handlerAdapters;
    private List<ViewResolver> viewResolvers;

    @Override
    public void init() throws ServletException {
        RequestMappingHandlerMapping requestMappingHandlerMapping = new RequestMappingHandlerMapping();
        requestMappingHandlerMapping.init();

        AnnotationHandlerMapping annotationHandlerMapping = new AnnotationHandlerMapping("org.example");
        annotationHandlerMapping.initialize();

        handlerMappings = List.of(requestMappingHandlerMapping, annotationHandlerMapping);
        handlerAdapters = List.of(new SimpleControllerHandlerAdapter(), new AnnotationHandlerAdapter());
        viewResolvers = Collections.singletonList(new JspViewResolver());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("[DispatcherServlet] service stared");
        String requestURI = req.getRequestURI();
        RequestMathod requestMathod = RequestMathod.valueOf(req.getMethod());

        try {

            // UserCreateController 측에서 return "redirect:/users" 할 경우
            // DispatcherServlet 측에서는 "/users" 가 아닌 "redirect:/users"로 받아 들임
//            System.out.println(req.getRequestURI());
            // 20:02:10.357 [INFO ] [http-nio-8080-exec-1] [org.example.mvc.DispatcherServlet] - [DispatcherServlet] service stared
            // /redirect:/users

            Object handler = handlerMappings.stream()
                    .filter(hm -> hm.findHandler(new HandlerKey(requestMathod, requestURI)) != null)
                    .map(hm -> hm.findHandler(new HandlerKey(requestMathod, requestURI)))
                    .findFirst()
                    .orElseThrow(() -> new ServletException("No handler for [ " + requestMathod + ", " + requestURI + "]"));


            HandlerAdapter handlerAdapter = handlerAdapters.stream()
                    .filter(ha -> ha.supports(handler))
                    .findFirst()
                    .orElseThrow(() -> new ServletException("No adapter for [" + handler + "]"));
            ModelAndView modelAndView = handlerAdapter.handler(req, resp, handler);

            // /redirect:/users 로 forward 할 경우 에러 발생
            // redirect 일 경우는 redirect 를 해주어야 하고, forward 일 경우는 forward 를 해주는 분기점이 필요함
            /**
             * 변경전 코드
             * RequestDispatcher requestDispatcher = req.getRequestDispatcher(viewName);
             * requestDispatcher.forward(req, resp);
             */

            for (ViewResolver viewResolver : viewResolvers) {
                View view = viewResolver.resolveView(modelAndView.getViewName());
                view.render(modelAndView.getModel(), req, resp);
            }
        } catch (Exception e) {
            log.error("exception occurred: [{}]", e.getMessage(), e);
            throw new ServletException(e);
        }
    }
}
