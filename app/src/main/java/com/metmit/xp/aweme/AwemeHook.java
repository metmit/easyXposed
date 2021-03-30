package com.metmit.xp.aweme;

import com.metmit.xp.aweme.hooks.UserHook;

public class AwemeHook {

    public void register() {
        (new UserHook()).register();
    }
}
