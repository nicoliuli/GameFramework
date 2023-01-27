package com.game.manager;

import com.game.core.Connection;
import com.game.core.Event;
import com.game.core.HumanObject;
import com.game.core.Port;
import com.game.core.anno.CallBack;
import com.game.core.anno.Listener;
import com.game.core.call.ServiceCallback;
import com.game.core.constant.EventKey;
import com.game.core.dto.User;
import com.game.core.func.Func2;
import com.game.core.util.Param;
import com.game.service.proxy.AccountServiceProxy;

public class AccountManager extends ManagerBase {

    public static AccountManager inst() {
        return inst(AccountManager.class);
    }

    public void verify(HumanObject humanObj, User user) {
        Integer humanId = humanObj.getHumanId();
        Connection conn = humanObj.getConnection();
        int portId = conn.getPort().getPortId();
        AccountServiceProxy prx = new AccountServiceProxy(conn.getPort());
        ServiceCallback context = new ServiceCallback(portId, 1, (Func2<Object, Param>) this::_verify_result, new Param(humanObj));
        prx.verify(user.getName(), user.getAge(), context);

        String result = "verify return portId = " + portId + ", humanId = " + humanId + ",param = " + user;
        humanObj.sendMsg(result);
    }

    public void event(HumanObject humanObj, User user) {
        Integer humanId = humanObj.getHumanId();
        Event.fire(EventKey.TEST_EVENT,new Param(humanObj));
        Event.fire(EventKey.TEST_EVENT1,new Param(humanObj));

    }

    @CallBack
    public void _verify_result(Object result, Param context) {
        System.out.println("result = " + result + "thread = " + Thread.currentThread().getId());
        Object[] ctx = context.getParam();
        HumanObject humanObj = (HumanObject) ctx[0];
        humanObj.sendMsg("收到回调 humanId = " + humanObj.getHumanId() + ", result = " + (String) result);
    }


    @Listener(eventKey = {EventKey.TEST_EVENT})
    public void testEvent(Param param) {
        Object[] params = param.getParam();
        HumanObject humanObj = (HumanObject) params[0];
        Integer humanId = humanObj.getHumanId();
        Connection connection = humanObj.getConnection();
        Port port = connection.getPort();
        String result = EventKey.TEST_EVENT + ":" + humanId + ":" + port.getPortId();

        humanObj.sendMsg(result);
    }

    @Listener(eventKey = {EventKey.TEST_EVENT1})
    public void testEvent1(Param param) {
        Object[] params = param.getParam();
        HumanObject humanObj = (HumanObject) params[0];
        Integer humanId = humanObj.getHumanId();
        Connection connection = humanObj.getConnection();
        Port port = connection.getPort();
        String result = EventKey.TEST_EVENT1 + ":" + humanId + ":" + port.getPortId();

        humanObj.sendMsg(result);
    }

    @Listener(eventKey = {EventKey.TEST_EVENT,EventKey.TEST_EVENT1})
    public void testEvent2(Param param) {
        Object[] params = param.getParam();
        HumanObject humanObj = (HumanObject) params[0];
        Integer humanId = humanObj.getHumanId();
        Connection connection = humanObj.getConnection();
        Port port = connection.getPort();
        String result = EventKey.TEST_EVENT + ":" + EventKey.TEST_EVENT1 + ":" + humanId + ":" + port.getPortId();

        humanObj.sendMsg(result);
    }


}
