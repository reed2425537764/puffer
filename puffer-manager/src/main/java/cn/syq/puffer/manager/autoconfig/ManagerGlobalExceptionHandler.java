package cn.syq.puffer.manager.autoconfig;

import cn.syq.puffer.business.context.ManagerContext;
import cn.syq.puffer.business.exception.ManagerException;
import cn.syq.puffer.manager.model.controller.ProjectController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: shiyuqin
 * @Date: 2022/7/17 16:01
 */
@ControllerAdvice(basePackageClasses = {
        ProjectController.class,
})
public class ManagerGlobalExceptionHandler {

    @ModelAttribute
    public void initContext(Model model) {
        ManagerContext context = ManagerContext.getContext();
        context.setReqSeq((String) model.getAttribute("reqSeq"));
    }

    @ExceptionHandler(ManagerException.class)
    public @ResponseBody String managerExceptionHandler(ManagerException managerException) {
        return managerException.getCode() + managerException.getDesc();
    }
}
