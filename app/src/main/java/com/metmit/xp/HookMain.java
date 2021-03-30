package com.metmit.xp;

import com.metmit.xp.utils.Container;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * 服务入口
 */
public class HookMain implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        Register register = Register.getInstance();

        // 如果不是要处理的包，直接返回
        if (!register.hasResolved(loadPackageParam.packageName)) {
            return;
        }

        // 将 loadPackageParam 变量放入容器
        if (Container.loadPackageParam == null) {
            Container.loadPackageParam = loadPackageParam;
        }

        register.register(loadPackageParam.packageName);
    }
}
