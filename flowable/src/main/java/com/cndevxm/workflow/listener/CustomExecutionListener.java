package com.cndevxm.workflow.listener;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 执行监听器
 * <p>
 * 监听事件：
 * start：开始
 * end：结束
 * take：执行
 * <p>
 * 目前可以被捕获的事件：
 * 流程实例的启动和结束
 * 流程执行转移，也就是执行监听器绑定在sequenceFlow上，且监听事件为take，虽然监听事件类型会被忽略
 * 活动的启动和结束
 * 网关的启动和结束
 * 中间事件的启动和结束
 * 启动事件的结束
 * 结束事件的启动
 * <p>
 * 监听器实现：
 * 类：com.cndevxm.workflow.listener.CustomExecutionListener，此类需实现ExecutionListener接口
 * UEL：${customExecutionListener.notify(execution)}，customExecutionListener为spring-bean，此时execution为当前执行体，由流程引擎默认提供
 * 委托表达式：${customExecutionListener}，customExecutionListener为spring-bean，此类需实现ExecutionListener接口
 * <p>
 * 字段注入：
 * 只有类实现与委托表达式才能完成字段的注入，字段通过定义org.flowable.engine.delegate.Expression类型的变量和setter方法来接收，UEL只能通过方法参数来完成字段的注入
 * <p>
 * 线程安全问题：
 * 1、同个流程定义下无论是多个流程实例还是单个流程实例的服务任务的多任务实例，其类实现都是线程安全的
 * 2、原型作用域的spring-bean是线程安全的，UEL使用方法参数传参，委托表达式使用字段注入传参
 * <p>
 * 多实例下触发：
 * 执行次数：loop+1，start时触发一个总的监听器，然后每个任务实例有一套单独的监听器，end时触发一个总的监听器
 */
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CustomExecutionListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) {

        log.info("执行监听器执行开始...");
        log.info("执行监听事件：{}", execution.getEventName());
        log.info("myVar：{}", ObjectUtils.isEmpty(execution.getVariable("myVar")) ? "" : execution.getVariable("myVar"));
        log.info("执行监听器的hashcode：{}", this.hashCode()); // 判断监听器实现类是不是出现了共用
        log.info("执行监听器执行结束...");
    }
}
