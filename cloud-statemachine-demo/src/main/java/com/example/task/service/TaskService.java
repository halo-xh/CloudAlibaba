package com.example.task.service;

import com.example.task.controller.request.TaskCreateRequest;

/**
 * <p>
 * 状态机demo任务表 服务类
 * </p>
 *
 *
 SUBMIT("提交"),

 DISPATCHED("调派"),

 TRANS("转交"),

 ACCEPT("接受单据"),

 HANDLE("处理单据"),

 HANG_UP("挂起"),

 HANG_DOWN("解挂"),

 REJECT("回退"),

 FINISH("结束单据"),

 EVALUATE("审核"),

 PASS_EVALUATE("审核通过"),

 REJECT_EVALUATE("审核拒绝"),

 CLOSE("关闭单据");
 *
 * @author xh
 * @since 2024-08-18
 */
public interface TaskService {

    Long submitTask(TaskCreateRequest request);

    void acceptTask(Long id);

    void rejectTask(Long id);

    void finishTask(Long id);

    void evaluateTask(Long id);

    void passEvaluateTask(Long id);

    void rejectEvaluateTask(Long id);

    void closeTask(Long id);

    void hangUpTask(Long id);

    void hangDownTask(Long id);

    void transTask(Long id);

    void handleTask(Long id);

    void dispatchTask(Long id);

}
