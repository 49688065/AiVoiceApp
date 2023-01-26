# 项目讲解文档

##1.项目核心思想(技术)

kotlin + 组件化 + kotlin Gradle DSL

##2 kotlin Gradle DSL 

传统 Gradle - Groovy

kotlin Gradle - kotlin

##3.组件化
app空壳 + 若干组件

远古的应用 :app(一堆代码)
远古的应用升级 :app + base(抽取通用的代码)
远古应用 pro :app + base + lib(lib_log,lib_network,lib_map的模块)

MVC
MVP (MVC升级版本) m(data) v)(ui) p(impl) ->逻辑

组件化 App空壳 +(N个module(也是一个app,可以单独调试)) - N个Library

如何动态构建组件化

## 4.构建组件化app

App
Module 
    - 笑话 地图 星座 语音设置 系统设置 天气 应用管理 开发者模式
lib  lib_base lib_network lib_voice  

eventBus原理是通过post发送事件到事件栈池里面,他就会发送给还存在注册关系的MessageEvent对象里面去,对象里面有个onMessageEvent方法接收

## 5.服务保活
-1.像素保活,通过服务中启动一个窗口像素1px,来达到保活的手段[欺诈系统]

-2.系统自带,系统做了一些友好的保活 - FLAG
START_STICKY:当系统内存不足的时候,杀掉了服务,那么在系统内存不再紧张的时候,启动服务
START_NOT_STICKY:当系统内存不足的时候,杀掉了服务,直到下一次StartService才启动
START_REDELIVER_INTENT:重新传递Intent值
START_STICKY_COMPATIBILITY :START_STICKY兼容版本,但是它也不能保证系统kill掉服务一定能重启
-3.JobScheduler
    -工作服务,标记着这个服务一直在工作,是作为一种进程死后复活的手段
    -缺点:耗电,高版本不兼容
-4.进程相互唤醒,双进程保活
    --QQ -微信
-5.前台服务
    -我在前台运行,我绑定通知栏,在服务中创建通知栏
