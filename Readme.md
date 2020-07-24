# Edge Node Management System
## 记录
### 2020-7-22
* docker-py里是一些简单的操作docker的功能，逻辑还不是太完整，为了方便我分开写了，后期可以合并到一个.py里。
### 2020-7-24
* 使用select *获取image对象，报错2020-07-24 14:37:21.082 ERROR 11532 --- [nio-3001-exec-1] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.executor.result.ResultMapException: Error attempting to get column 'imageTag' from result set.  Cause: java.lang.NumberFormatException: For input string: "latest"] with root cause。
  抛出异常java.lang.NumberFormatException: For input string: "latest"，不知原因，暂时先指定select属性，不使用select *
---


