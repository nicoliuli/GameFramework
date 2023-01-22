package com.game.core.call;

import com.game.core.HumanObject;

/**
 * port -> msgHandler
 */
public class MsgCall {
    private WSCall WSCall;
    private HumanObject humanObj;


    public WSCall getWSCall() {
        return WSCall;
    }

    public void setWSCall(WSCall WSCall) {
        this.WSCall = WSCall;
    }

    public HumanObject getHumanObj() {
        return humanObj;
    }

    public void setHumanObj(HumanObject humanObj) {
        this.humanObj = humanObj;
    }
}
