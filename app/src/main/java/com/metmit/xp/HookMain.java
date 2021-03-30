package com.metmit.xp;


import com.metmit.xp.aweme.AwemeHook;
import com.metmit.xp.kwai.KwaiHook;
import com.metmit.xp.utils.Container;

import java.util.Arrays;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * 服务入口
 */
public class HookMain implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        // 如果不是要处理的包，直接返回
        if (!Arrays.asList(Container.HANDLE_APP_NAME).contains(loadPackageParam.packageName)) {
            return;
        }

        // 将 loadPackageParam 变量放入容器
        if (Container.loadPackageParam == null) {
            Container.loadPackageParam = loadPackageParam;
        }

        if (loadPackageParam.packageName.equals(Container.AWEME_NAME)) {
            new AwemeHook().register();
        }

        if (loadPackageParam.packageName.equals(Container.KWAI_NAME)) {
            new KwaiHook().register();
        }

    }
}
