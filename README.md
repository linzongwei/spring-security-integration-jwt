# spring-security-integration-jwt
spring security认证授权框架 结合jwt签名实现登录鉴权
# 启动
  - 程序内置H2内存数据库，不需要连接外部数据库
  - 无需修改配置文件，即可启动程序
# 测试
  - 登录请求，获取token
    - POST方式请求接口：http://localhost:8080/login?username=admin&password=12345678
	- 返回结果：token存储在头部信息header中的Authorization键中
  - 接口测试
    - 未授权接口： 
	  - POST请求：http://localhost:8080/test
	  - 返回结果：{
			"timestamp": "2021-01-25T03:09:35.159+00:00",
			"status": 401,
			"error": "Unauthorized",
			"message": "",
			"path": "/test"
		}
    - 已授权接口
	  - POST请求：http://localhost:8080/test
	  - 请求头部信息添加Authorization键，值为token
	  - 返回结果：success
