package com.game.core.call;

import com.game.core.util.Param;

public class ServiceCall implements SCall{

    // service
    public final Integer type = 1;

    private Integer portId;

    private String serviceId;


    /**
     * service method id
     */
    private String methodKey;

    /**
     * 字段
     */
    private Param field;

    @Override
    public Integer getType() {
        return this.type;
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

    public void setField(Param field) {
        this.field = field;
    }

    public String getMethodKey() {
        return methodKey;
    }

    public Param getField() {
        return field;
    }
}
