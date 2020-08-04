# Edge Node Management System
## 记录
### 2020-7-22
* docker-py里是一些简单的操作docker的功能，逻辑还不是太完整，为了方便我分开写了，后期可以合并到一个.py里。
### 2020-7-24
* 使用select *获取image对象，报错2020-07-24 14:37:21.082 ERROR 11532 --- [nio-3001-exec-1] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.executor.result.ResultMapException: Error attempting to get column 'imageTag' from result set.  Cause: java.lang.NumberFormatException: For input string: "latest"] with root cause。
  抛出异常java.lang.NumberFormatException: For input string: "latest"。
  原因：model类对象的构造函数的参数顺序没有和数据库column顺序一致，使boolean类型的属性对应到了String类型的属性，导致报错。
  解决方法：修改构造函数的参数顺序。
### 2020-8-4
* 日志服务使用RabbitMQ消息队列，实现异步日志生成，降低服务之间耦合以及消除日志服务故障造成的错误。
---


