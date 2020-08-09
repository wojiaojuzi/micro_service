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
### 2020-8-9
* 在添加了一个周期调用python代码来对容器和服务进行状态监控的定时任务后，发现其他定时任务的效率受到极大影响，前端操作反省迟钝。
  原因：Schedule定时任务默认将所有定时任务运行在同一个线程池的同一个线程中，其中一个任务由于运行较慢导致其他任务受到一定阻塞。
  解决方法：通过配置，将不同定时任务设置成多线程并发执行，参考https://blog.csdn.net/lchq1995/article/details/83308424
---


