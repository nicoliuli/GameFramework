package com.game.core.call;

public class ServiceCall implements SCall{

    public Integer type = 1;
    private Integer portId;

    private String serviceId;


    /**
     * service method id
     */
    private String methodKey;

    /**
     * 字段
     */
    private Object[] field;

    @Override
    public Integer getType() {
        return 1;
    }

    public void setPortId(Integer portId) {
        this.portId = portId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }


    public void setMethodKey(String methodKey) {
        this.methodKey = methodKey;
    }

    public void setField(Object[] field) {
        this.field = field;
    }

    public String getMethodKey() {
        return methodKey;
    }

    public Object[] getField() {
        return field;
    }
}
