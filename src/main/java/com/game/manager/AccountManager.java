package com.game.manager;

import com.game.core.Connection;
import com.game.core.HumanObject;
import com.game.core.dto.User;

public class AccountManager {
    public void verify(HumanObject humanObj, User user){
        Integer humanId = humanObj.getHumanId();
        Connection conn = humanObj.getConnection();
        int portId = conn.getPort().getPortId();
        String result = "verify return portId = " + portId +", humanId = " + humanId + ",param = " + user;
        humanObj.sendMsg(result);
    }
}
