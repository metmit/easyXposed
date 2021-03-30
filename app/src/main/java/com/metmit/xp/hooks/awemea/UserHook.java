package com.metmit.xp.hooks.awemea;

import com.metmit.xp.utils.Container;
import com.metmit.xp.utils.Helper;
import com.metmit.xp.utils.Sposed;

import java.util.Arrays;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class UserHook extends DyBaseHook {

    public void register() {
        this.profile();
        this.posts();
        this.favorite();
    }

    private void profile() {
        XposedHelpers.findAndHookMethod("com.ss.android.ugc.aweme.profile.api.j",
                Container.loadPackageParam.classLoader, "b",
                String.class, boolean.class, String.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                        super.afterHookedMethod(methodHookParam);
                        Object UserResponse = methodHookParam.getResult();
                        Object UserModel = Sposed.callMethod(UserResponse, "getUser");

                        Helper.log("profile 参数: " + Arrays.toString(methodHookParam.args));
                        Helper.log("profile 昵称: " + XposedHelpers.getObjectField(UserModel, "nickname").toString());
                        Helper.log("profile 粉丝: " + XposedHelpers.getObjectField(UserModel, "followerCount").toString());
                    }
                }
        );
    }

    private void posts() {
        //boolean z, String str, String str2, int i, long j, int i2, String str3, int i3, int i4, Integer num
        XposedHelpers.findAndHookMethod("com.ss.android.ugc.aweme.profile.api.AwemeApi",
                Container.loadPackageParam.classLoader, "a",
                boolean.class, String.class, String.class, int.class, long.class, int.class,
                String.class, int.class, int.class, Integer.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                        super.afterHookedMethod(methodHookParam);

                        Object itemList = methodHookParam.getResult();

                        Helper.log("posts 参数: " + Arrays.toString(methodHookParam.args));

                        List<?> objectList = (List<?>) XposedHelpers.getObjectField(itemList, "items");
                        for (int i = 0; i < objectList.size(); i++) {
                            Object item = objectList.get(i);
                            Helper.log(String.format("posts[%s]: %s", i, XposedHelpers.getObjectField(item, "desc").toString()));
                        }
                    }
                }
        );
    }

    private void favorite() {
        // boolean z, String str, String str2, int i, long j, int i2, String str3, int i3, int i4
        XposedHelpers.findAndHookMethod("com.ss.android.ugc.aweme.profile.api.AwemeApi",
                Container.loadPackageParam.classLoader, "a",
                boolean.class, String.class, String.class, int.class, long.class, int.class,
                String.class, int.class, int.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                        super.afterHookedMethod(methodHookParam);
                        Object itemList = methodHookParam.getResult();

                        Helper.log("favorite 参数: " + Arrays.toString(methodHookParam.args));

                        List<?> objectList = (List<?>) XposedHelpers.getObjectField(itemList, "items");
                        for (int i = 0; i < objectList.size(); i++) {
                            Object item = objectList.get(i);
                            Helper.log(String.format("favorite[%s]: %s", i, XposedHelpers.getObjectField(item, "desc").toString()));
                        }
                    }
                }
        );
    }
}
