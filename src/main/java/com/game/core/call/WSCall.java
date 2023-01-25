package com.game.core.call;

/**
 * ws到port
 */
public class WSCall {

    private Integer fromNodeId;
    private Integer fromPortId;

    private Integer toNodeId;
    private Integer toPortId;

    private Integer portId;

    // msgHandlerId
    private String msgHandlerId;

    private String jsonParam;

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


    public String getJsonParam() {
        return jsonParam;
    }

    public void setJsonParam(String jsonParam) {
        this.jsonParam = jsonParam;
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
