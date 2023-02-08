package com.game.core.obj;

/**
 * 客户端消息上行参数
 * {"msgHandlerId":"AccountMsgHandler.verify","jsonParam":{"name":"zhangsan","age":18}}
 */
public class ClientParamWrapper {
    /**
     * 处理器id
     */
    private String msgHandlerId;

    /**
     * 业务请求参数
     */
    private String jsonParam;

    public String getMsgHandlerId() {
        return msgHandlerId;
    }

    public void setMsgHandlerId(String msgHandlerId) {
        this.msgHandlerId = msgHandlerId;
    }

    public String getJsonParam() {
        return jsonParam;
    }

    public void setJsonParam(String jsonParam) {
        this.jsonParam = jsonParam;
    }
}
