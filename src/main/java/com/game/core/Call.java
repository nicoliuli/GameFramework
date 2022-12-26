package com.game.core;

public class Call {

    private Integer fromNodeId;
    private Integer fromPortId;

    private Integer toNodeId;
    private Integer toPortId;

    /**
     * ws->node
     * node->port
     * port->service
     *
     */
    private Integer queueType;

    private Object [] params;

    // 方法引用

    // callBack 方法引用


    public Integer getFromNodeId() {
        return fromNodeId;
    }

    public void setFromNodeId(Integer fromNodeId) {
        this.fromNodeId = fromNodeId;
    }

    public Integer getFromPortId() {
        return fromPortId;
    }

    public void setFromPortId(Integer fromPortId) {
        this.fromPortId = fromPortId;
    }

    public Integer getToNodeId() {
        return toNodeId;
    }

    public void setToNodeId(Integer toNodeId) {
        this.toNodeId = toNodeId;
    }

    public Integer getToPortId() {
        return toPortId;
    }

    public void setToPortId(Integer toPortId) {
        this.toPortId = toPortId;
    }

    public Integer getQueueType() {
        return queueType;
    }

    public void setQueueType(Integer queueType) {
        this.queueType = queueType;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
