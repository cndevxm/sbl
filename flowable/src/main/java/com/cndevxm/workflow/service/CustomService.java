package com.cndevxm.workflow.service;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 服务任务的实现
 * 监听器实现：
 * 类：com.cndevxm.workflow.service.CustomService，此类需实现JavaDelegate接口
 * UEL：${customService.execute(execution)}，customService为spring-bean，此时execution为当前执行体，由流程引擎默认提供
 * 委托表达式：${customService}，customService为spring-bean，此类需实现JavaDelegate接口
 * <p>
 * 字段注入：
 * 只有类实现与委托表达式才能完成字段的注入，字段通过定义org.flowable.engine.delegate.Expression类型的变量和setter方法来接收，UEL只能通过方法参数来完成字段的注入
 * <p>
 * <p>
 * 线程安全问题：
 * 1、同个流程定义下无论是多个流程实例还是单个流程实例的服务任务的多任务实例，其类实现都是使用同一个类实例，所以绝对是非线程安全的
 * 2、原型作用域的spring-bean是线程安全的，UEL使用方法参数传参，委托表达式使用字段注入传参
 */
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CustomService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {

        log.info("服务任务委托执行开始...");
        log.info("name:{}", execution.getVariable("myVar"));
        log.info("服务任务实现的hashcode：{}", this.hashCode()); // 判断服务任务实现是不是出现了共用
        log.info("服务任务委托执行结束...");
    }
}
