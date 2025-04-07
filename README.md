# 北洋园外卖平台

## 项目介绍

这是一款为餐饮类企业定制的软件产品，分为管理端和用户端。实现用户端点单，管理端处理订单的简易外卖软件

## 技术选型
![技术选型](https://github.com/user-attachments/assets/5e1c5ffe-d8cb-46b9-ae8b-1e528f321399)

### 用户层 

商家端
     --- 简单的网页，使用了前端三件套，以及ElementUI， Echarts 等技术
用户端
    --- 使用微信小程序框架进行开发

### 网关层
NGINX:
     --- 使用nginx负载均衡来合理请求到多态服务器，减轻服务器压力

### 应用层
Springboot:
     --- Springboot是基于Spring的开源框架，简化了各种配置，方便开发人员工作
SpringMVC:
     --- SpringMVC是基于 MVC（Model-View-Controller）模式的 Web 应用程序开发框架。它提供了一种结构清晰、模块化的方式来构建可扩展的 Web 应用程序
HttpClient:
     ---  Http请求的开源库，常用于后端请求各种接口的时候使用
SpringTask
     ---  Spring框架中用于处理定时任务的工具
SpringCache:
     ---  SpringCache是Spring框架中用于缓存处理的，可以把用户频繁访问的数据放入缓存当中，减少对数据库的IO操作，减轻数据库的压力
JWT:
     ---  校验用户身份是否过期，是否存在等等
阿里云OSS:
     ---  用于存储项目内的图片文件等等
Swagger:
     ---  Swagger是一款RESTFUL接口的文档在线自动生成+功能测试功能软件。Swagger是一个规范和完整的框架，用于生成、描述、调用和可视化RESTfu风格的web服务。目标是使客户端和文件系统作为服务器一同样的速度来更新文件的方法，参数和模型紧密集成到服务器。这个解释简单点来讲就是说，swagger是一款可以根据restful风格生成的接口开发文档，并且支持做测试的一款中间软件。
POI:
     ---  读取和写入Microsoft Office格式文件，如Word文档、Excel电子表格和PowerPoint演示文稿
Websocket:
     ---  实时通信会话技术，用于商家管理端和服务器保持实时通信。
PageHelper:
     ---  分页查询工具
spring data redis:
     ---  Spring框架提供的操作Redis数据库的工具

### 数据层
MYSQL:
     ---  关系型数据库，以表的形式将数据存入到硬盘当中
Redis:
     ---  键值型存储系统，基于键值对的形式对数据进行存储，数据会被存储到内存当中
