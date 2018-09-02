1.natapp 配置端口号比如 8080
2.API去验证微信服务器(接口配置信息)
    http://xx.xx.xx/wechat
3.(网页授权获取用户基本信息),里面填写"客户端需要回调的地址"(ccc.ccc.cc)
4.客户端发送ajax请求,获取微信服务器重定向过来的code,拼接API的地址带上该code(userinfo)的地址
