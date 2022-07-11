package com.cndevxm.controller;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class TestController {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @PostMapping("/test")
    public String test() {

        List<Task> taskList = taskService.createTaskQuery().taskAssignee("user02").list();

        for (Task task : taskList) {
            log.info("taskId为：", task.getId());
            taskService.complete(task.getId());
        }
        return "success";
    }
}
