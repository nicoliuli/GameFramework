package com.game.core.obj;

/**
 * 全局用户信息
 */
public class GlobalHumanObject {
    private Integer humanId;

    private Integer nodeId;

    private Integer portId;

    private Connection connection;

    public GlobalHumanObject() {
    }

    public GlobalHumanObject(Integer humanId, Integer nodeId, Integer portId, Connection connection) {
        this.humanId = humanId;
        this.nodeId = nodeId;
        this.portId = portId;
        this.connection = connection;
    }

    public Integer getHumanId() {
        return humanId;
    }
}
