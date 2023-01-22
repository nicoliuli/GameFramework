package com.game.core.call;

import com.game.core.util.Param;

/**
 * 封装回调结果
 */
public class ServiceCallback {

    private Integer portId;

    private String serviceId;

    /**
     * 1：根据methodKey分派到manager
     * 2：根据method分派到service
     */
    private Integer callbackType;

    /**
     * 回调的方法
     */
    private String methodKey;

    /**
     * 上下文
     */
    private Param context;

    /**
     * 返回值
     */
    private Object result;

    public ServiceCallback() {
    }

    /**
     * 针对manager回调
     */
    public ServiceCallback(Integer portId, Integer callbackType, String methodKey, Param context) {
        this.portId = portId;
        this.callbackType = callbackType;
        this.methodKey = methodKey;
        this.context = context;
    }

    /**
     * 针对service回调
     */
    public ServiceCallback(Integer portId, String serviceId, Integer callbackType, String methodKey, Param context) {
        this.portId = portId;
        this.serviceId = serviceId;
        this.callbackType = callbackType;
        this.methodKey = methodKey;
        this.context = context;
    }

    public Integer getPortId() {
        return portId;
    }

    public void setPortId(Integer portId) {
        this.portId = portId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getCallbackType() {
        return callbackType;
    }

    public void setCallbackType(Integer callbackType) {
        this.callbackType = callbackType;
    }

    public String getMethodKey() {
        return methodKey;
    }

    public void setMethodKey(String methodKey) {
        this.methodKey = methodKey;
    }

    public Param getContext() {
        return context;
    }

    public void setContext(Param context) {
        this.context = context;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
