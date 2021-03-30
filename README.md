# easyXposed

快速上手开发`Xposed`模块。

## 使用

1. 复制`./app/src/main/assets/config.example.properties`为`config.properties`，在此自定义配置
2. 更改`com.metmit.xp.Register`的`classArrayList`属性值，增加要hook的包的入口类
3. 添加对应的hook入口类，实现`com.metmit.xp.constraint.Register`接口








