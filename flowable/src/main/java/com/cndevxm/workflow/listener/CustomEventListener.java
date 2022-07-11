package com.cndevxm.workflow.listener;

import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;
import org.springframework.stereotype.Component;

/**
 * 全局事件监听器
 */
@Slf4j
@Component
public class CustomEventListener implements FlowableEventListener {

    @Override
    public void onEvent(FlowableEvent event) {

        log.info("事件监听器执行开始...");
        log.info("");


        log.info("事件监听器执行结束...");
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }

    @Override
    public boolean isFireOnTransactionLifecycleEvent() {
        return false;
    }

    @Override
    public String getOnTransaction() {
        return null;
    }
}
