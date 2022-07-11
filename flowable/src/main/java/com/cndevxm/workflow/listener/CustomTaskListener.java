package com.cndevxm.workflow.listener;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 任务监听器，只能出现在人机任务中
 * <p>
 * event：
 * assignment：当任务已经指派给某人时触发
 * create：当任务已经创建，并且所有任务参数都已经设置时触发
 * complete：当任务已经完成，从运行时数据中删除前触发
 * delete：在任务即将被删除前触发
 * <p>
 * 监听器实现：
 * 类：com.cndevxm.workflow.listener.CustomTaskListener，此类需实现TaskListener接口
 * UEL：${customTaskListener.notify(task)}，customExecutionListener为spring-bean，此时task为当前任务，由流程引擎默认提供
 * 委托表达式：${customTaskListener}，customTaskListener为spring-bean，此类需实现TaskListener接口
 * <p>
 * 字段注入：
 * 只有类实现与委托表达式才能完成字段的注入，字段通过定义org.flowable.engine.delegate.Expression类型的变量和setter方法来接收，UEL只能通过方法参数来完成字段的注入
 * <p>
 * 线程安全问题：
 * 1、同个流程定义下无论是多个流程实例还是单个流程实例的服务任务的多任务实例，其类实现都是线程安全的
 * 2、原型作用域的spring-bean是线程安全的，UEL使用方法参数传参，委托表达式使用字段注入传参
 * <p>
 * 多实例下触发：
 * 每一个任务实例都会单独触发任务监听器
 * <p>
 * 执行监听器与任务监听器执行顺序：
 * 先触发执行监听器再触发任务监听器
 */
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CustomTaskListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {

        log.info("任务监听器执行开始...");
        log.info("任务id为：{}", delegateTask.getId());
        log.info("任务名称为：{}", delegateTask.getName());
//        log.info("任务描述为：{}", delegateTask.getDescription());
//        log.info("任务优先级为：{}", delegateTask.getPriority());
//        log.info("流程实例id为：{}", delegateTask.getProcessInstanceId());
//        log.info("getExecutionId：{}", delegateTask.getExecutionId());
//        log.info("流程定义id为：{}", delegateTask.getProcessDefinitionId());
//        log.info("任务创建时间为：{}", delegateTask.getCreateTime().toString());
//        log.info("流程定义任务id为：{}", delegateTask.getTaskDefinitionKey());
//        log.info("任务是否暂停：{}", delegateTask.isSuspended());
        log.info("监听器触发事件：{}", delegateTask.getEventName());
//        log.info("getEventHandlerId：{}", delegateTask.getEventHandlerId());
//        log.info("getDelegationState：{}", delegateTask.getDelegationState());
//        log.info("任务所有者：{}", delegateTask.getOwner());
//        log.info("任务处理人：{}", delegateTask.getAssignee());
//        log.info("任务超时时间：{}", delegateTask.getDueDate().toString());
//        log.info("任务标记为：{}", delegateTask.getCategory());
        log.info("任务监听器的hashcode：{}", this.hashCode()); // 判断监听器实现类是不是出现了共用
        log.info("任务监听器执行结束...");
    }
}
