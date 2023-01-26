package com.imooic.lib_voice.error

/**
 * 语音错误码
 */
object VoiceErrorCode {

    //1.网络超时

    //DNS连接超时
    const val CODE_DNS_CONNECT_TIME_OUT =1000

    //网络连接超时
    const val CODE_NET_CONNECT_TIME_OUT =1001

    //网络读取超时
    const val CODE_NET_READ_TIME_OUT =1002

    //上行网络连接超时
    const val CODE_NET_UP_CONNECT_TIME_OUT =1003

    //上行网络连接超时
    const val CODE_NET_UP_READ_TIME_OUT =1004

    //下行网络连接超时
    const val CODE_NET_DOWN_CONNECT_TIME_OUT =1005

    //下行网络读取超时
    const val CODE_NET_DOWN_READ_TIME_OUT =1006

    //2.网络连接失败

    //网络连接失败
    const val CODE_NET_CONNECT_FAIL =2000

    //网络读取失败
    const val CODE_NET_READ_FAIL =2001

    //上行网络连接失败
    const val CODE_NET_UP_CONNECT_FAIL =2003

    //下行网络连接失败
    const val CODE_NET_DOWN_CONNECT_FAIL =2004

    //下行网络读取失败
    const val CODE_NET_DOWN_READ_FAIL =2005

    //下行数据异常
    const val CODE_DATA_DOWN_EXCEPTION = 2006

    //本地网络不可用
    const val CODE_NET_LOCAL_DISABLED = 2100

    //3.音频错误

    //录音机打不开
    const val CODE_AUDIO_OPEN_FAIL = 3001

    //录音机参数错误
    const val CODE_AUDIO_PARAMS_ERROR = 3002

    //录音机不可用
    const val CODE_AUDIO_DISABLED = 3003

    //录音机读取失败
    const val CODE_AUDIO_READ_FAIL = 3006

    //录音机关闭失败
    const val CODE_AUDIO_CLOSE_FAIL = 3007

    //文件打开失败
    const val CODE_AUDIO_FILE_OPEN_FAIL = 3008

    //文件读取失败
    const val CODE_AUDIO_FILE_READ_FAIL = 3009

    //文件关闭失败
    const val CODE_AUDIO_FILE_CLOSE_FAIL = 3010

    //VAD异常，通常是VAD资源设置不正确
    const val CODE_AUDIO_VAD_EXCEPTION = 3100

    //长时间未检测到人说话，请重新识别
    const val CODE_AUDIO_LONG_TIME_NO_SPEAK = 3101

    //检测到人说话，但语音过短
    const val CODE_AUDIO_SPEAK_SHORT = 3102

    //4.协议错误 出现原因可能是appid和appkey的鉴权失败

    //协议出错
    const val CODE_PROTOCAL_ERROR = 4001

    //协议出错
    const val CODE_AUDIO_PROTOCAL_ERROR = 4002

    //识别出错
    const val CODE_ASR_ERROR = 4003

    //鉴权错误 ，一般情况是pid appkey secretkey不正确权限 。见下表”4004"鉴权子错误码
    const val CODE_AAA_ERROR = 4004

    //5.客户端调用错误		一般是开发阶段的调用错误，需要开发者检测调用逻辑或对照文档和demo进行修复。

    //无法加载so库
    const val CODE_CLIENT_NOT_LOAD_JNI =5001

    //识别参数有误
    const val CODE_CLIENT_ASR_PARAMS_ERROR =5002

    //获取token失败
    const val CODE_GET_TOKEN_ERROR =5003

    //客户端DNS解析失败
    const val CODE_CLIENT_DNS_PARSING_ERROR =5004

    //其他
    const val CODE_CLIENT_OTHER_ERROR =5005

    //6.超时		语音过长，请配合语音识别的使用场景，如避开嘈杂的环境等
    //客户端DNS解析失败 未开启长语音时，当输入语音超过60s时，会报此错误
    const val CODE_LONG_AUDIO_TIME_OUT =6001


    //7.没有识别结果		信噪比差，请配合语音识别的使用场景，如避开嘈杂的环境等
    // 没有匹配的识别结果。当检测到语音结束，或手动结束时，服务端收到的音频数据质量有问题，导致没有识别结果
    const val CODE_ASR_NO_RESULT =7001

    // 8.	引擎忙		一般是开发阶段的调用错误，出现原因是上一个会话尚未结束，就让SDK开始下一次识别。SDK目前只支持单任务运行，即便创建多个实例，也只能有一个实例处于工作状态
    //识别引擎繁忙 。当识别正在进行时，再次启动识别，会报busy
    const val CODE_ASR_BUSY =8001

    //9.缺少权限		参见demo中的权限设置
    //没有录音权限 通常是没有配置录音权限：android.permission.RECORD_AUDIO
    const val CODE_AUDIO_NOT_PERMISSION =9001

    //10.其它错误		出现原因如：使用离线识别但未将EASR.so集成到程序中；离线授权的参数填写不正确；参数设置错误等。
    //离线引擎异常
    const val CODE_ASR_OFFLINE_EXCEPTION = 10001

    //没有授权文件
    const val CODE_ASR_NOT_AUTHORIZATION_FILE = 10002

    //授权文件不可用
    const val CODE_ASR_AUTHORIZATION_FILE_DISABLED = 10003

    //离线参数设置错误
    const val CODE_ASR_PARAMS_SET_ERROR = 10004


    //引擎没有被初始化
    const val CODE_ASR_NOT_INIT = 10005

    //模型文件不可用
    const val CODE_ASR_MODULE_FILE_DISABLED = 10006

    //语法文件不可用
    const val CODE_ASR_GRAMMAR_FILE_DISABLED = 10007

    //引擎重置失败
    const val CODE_ASR_RESET_INIT_ERROR = 10008

    //引擎初始化失败
    const val CODE_ASR_INIT_ERROR = 10009

    //引擎释放失败
    const val CODE_ASR_RELEASE_ERROR = 10010

    //引擎不支持
    const val CODE_ASR_NOT_SUPPORT = 10011

    //离线引擎识别失败 。离线识别引擎只能识别grammar文件中约定好的固定的话术，即使支持的话术，识别率也不如在线。请确保说的话清晰，是grammar中文件定义的，测试成功一次后，可以保存录音，便于测试。
    const val CODE_ASR_OFFLINE_PARSING_FAIL = 10012

    //"4004"鉴权子错误码  4004的子错误值 错误码描述	原因
    //4	pv超限	配额使用完毕，请购买或者申请
    const val CODE_APP_PV_NOT = 4

    //6	没勾权限	应用不存或者应用没有语音识别的权限
    const val CODE_APP_NOT_EXIST = 6

    //13	并发超限	并发超过限额，请购买或者申请
    const val CODE_APP_QPS_NOT = 13

    //101	API key错误	API Key 填错
    const val CODE_APP_KEY_ERROR = 101

    //唤醒错误码
    //错误领域	描述	错误码	错误描述
    //10.	录音设备出错

    //1	录音设备异常
    const val CODE_AUDIO_DEVICES_EXCEPTION = 1

    // 2	无录音权限
    const val CODE_AUDIO_DEVICES_NOT_PERMISSION = 2

    //	录音设备不可用
    const val CODE_AUDIO_DEVICES_DISABLED = 3

    //4	录音中断
    const val CODE_AUDIO_DEVICES_INTERRUPT = 4

    //11.	唤醒相关错误
    //没有授权文件	11002
    const val CODE_WAKEUP_NOT_AUTHORIZATION = 11002

    //授权文件不可用	11003
    const val CODE_WAKEUP_AUTHORIZATION_FILE_DISABLED = 11003

    //唤醒异常, 通常是唤醒词异常	11004
    const val CODE_WAKEUP_EXCEPTION = 11004

    //模型文件不可用	11005
    const val CODE_WAKEUP_MODULE_FILE_DISABLED = 11005

    //引擎初始化失败	11006
    const val CODE_WAKEUP_INIT_ERROR = 11006

    //内存分配失败	11007
    const val CODE_WAKEUP_MEMORY_ALLCATION_FAIL = 11007

    //引擎重置失败	11008
    const val CODE_WAKEUP_RESET_INIT_FAIL = 11008

    //引擎释放失败	11009
    const val CODE_WAKEUP_RELEASE_FAIL = 11009

    //引擎不支持该架构	11010
    const val CODE_WAKEUP_NOT_SUPPORT = 11010

    //38	引擎出错

    //1	唤醒引擎异常
    const val CODE_ENGINE_WAKE_ENGINE_EXCEPTION = 1

    //2	无授权文件
    const val CODE_ENGINE_NOT_AUTHORTZATION_FILE = 2

    //3	授权文件异常
    const val CODE_ENGINE_NOT_AUTHORTZATION_FILE_EXCEPTION = 3

    //4	唤醒异常
    const val CODE_ENGINE_WAKE_EXCEPTION = 4

    //5	模型文件异常
    const val CODE_ENGINE_MODULE_FILE_EXCEPTION = 5

    //6	引擎初始化失败
    const val CODE_ENGINE_INIT_FAIL = 6

    //7	内存分配失败
    const val CODE_ENGINE_MEMORY_ALLCATION_FAIL = 7

    //8	引擎重置失败
    const val CODE_ENGINE_RESET_INIT_FAIL = 8

    //9	引擎释放失败
    const val CODE_ENGINE_RELEASE_FAIL = 9

    //10	引擎不支持该架构
    const val CODE_ENGINE_NOT_SUPPORT = 10

    //11	无识别数据
    const val CODE_ENGINE_NOT_DATA = 11





}