package com.lhzt.monitor.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * Created by pansen on 2017/10/25.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

  /**
   * 没有权限 异常
   * <p/>
   * 后续根据不同的需求定制即可
   */
  @ExceptionHandler({Exception.class})
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ModelAndView processUnauthenticatedException(NativeWebRequest request, Exception e) {
    String xRequestedWith = request.getHeader("X-Requested-With");
    ModelAndView mv = new ModelAndView();
    //对于ajax请求,如果没有权限,返回Json串
    if (xRequestedWith != null && xRequestedWith.endsWith("XMLHttpRequest")) {
      mv = new ModelAndView(new MappingJackson2JsonView());//自动转成JSON串
      mv.addObject("exception", e);
    } else {
      mv.addObject("exception", e);
      mv.setViewName("exception");
    }
    return mv;
  }
}
