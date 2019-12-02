# blog——Web课程博客项目

## 功能
### 基础功能：
- 游客：注册、登录、查看文章、查看作者、查看专题、搜索
- 登录用户：评论文章、喜欢文章/取消喜欢、关注用户/取消关注、修改个人资料、查看个人中心数据

### 进阶功能：
- 注册时验证手机号是否可用
- 手机验证码登录
- 忘记密码功能
- 图片验证码、人机验证等安全限制
- 绑定邮箱（用来重置密码）
- 数据的无刷新分页
- 批量操作


## 技术栈

### 后端
- 构建工具：maven(Gradle)
- 实体类entity：lombok
- 底层dao：原生的JDBC（JdbcTemplate、Mybatis、JPA）
- serivce层：注意事务、异常处理（logback打Log日志）
- controller层：Servlet、Gson、RESTful的API规约
- 开发工具：IDEA、Navicat、Tomcat、Postman

### 前端
- 构建工具：webpack
- Vue-cli、nodejs、npm、vue-router、axios
- 分职责来划分项目结构
- 开发工具：HBuilderX(VS code、webstorm)、chrome开发者工具
