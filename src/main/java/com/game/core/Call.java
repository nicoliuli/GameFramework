package com.game.core;

public class Call {

    private Integer fromNodeId;
    private Integer fromPortId;

    private Integer toNodeId;
    private Integer toPortId;

    private Integer portId;

    /**
     * ws->node 1
     * node->port   2
     * port->service    3
     *
     */
    private Integer queueType;

    // msgHandlerId
    private String msgHandlerId;

    private Object [] params;

    private Integer humanId;

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

    public Integer getPortId() {
        return portId;
    }

    public void setPortId(Integer portId) {
        this.portId = portId;
    }

    public Integer getHumanId() {
        return humanId;
    }

    public void setHumanId(Integer humanId) {
        this.humanId = humanId;
    }

    public String getMsgHandlerId() {
        return msgHandlerId;
    }

    public void setMsgHandlerId(String msgHandlerId) {
        this.msgHandlerId = msgHandlerId;
    }
}
