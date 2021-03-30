package com.metmit.xp.register;

import com.metmit.xp.constraint.PackageHandler;
import com.metmit.xp.constraint.Register;

import com.metmit.xp.hooks.awemea.UserHook;

@PackageHandler(name = "com.ss.android.ugc.aweme", alias = "抖音")
public class Aweme implements Register {

    public void register() {
        (new UserHook()).register();
    }
}
