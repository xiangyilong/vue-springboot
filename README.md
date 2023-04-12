# vue-springboot

[TOC]

## Vue框架说明

Vue是一个响应式渐进式框架，负责前端的页面处理，vue和springboot前后端分离开发

## Springboot框架说明

后端Springboot项目框架，主要分为Dao层，Service层，Entity层，以及Controller层，Dao层（Interface)完成项目与数据库的交互，定义基础的操作，Service层对Dao层进一步封装，完成具体的操作，提供服务，Entity层主要包括一些实体类，定义类的属性，方法，Controller层主要复杂处理后端的逻辑。

DAO 用于与数据库的直接交互，定义增删改查等操作

Service 负责业务逻辑，跟功能相关的代码一般写在这里，编写、调用各种方法对 DAO 取得的数据进行操作

Controller 负责数据交互，即接收前端发送的数据，通过调用 Service 获得处理后的数据并返回

## 应用框架

![image-20230406093012092](C:\Users\201\AppData\Roaming\Typora\typora-user-images\image-20230406093012092.png)

采用前后端分离框架，前端使用Vue框架负责页面操作，后端使用Springboot框架负责数据处理，使用ElemenUI库来进行前端界面设计，使用Axios库进行前后端的联动。使用Mysql数据库，以及Spring DataJPA完成数据库的相关操作。

## 技术框架

![image-20230406093220676](C:\Users\201\AppData\Roaming\Typora\typora-user-images\image-20230406093220676.png)

![](C:\Users\201\AppData\Roaming\Typora\typora-user-images\image-20230406093827228.png)

## Vue项目目录及说明

![image-20230406093249620](C:\Users\201\AppData\Roaming\Typora\typora-user-images\image-20230406093249620.png)

VueCli项目框架，index.html是首页初始代码，即整个项目只包含这一个index文件，也就是项目的所有元素都是在这个文件的div下进行

App.vue根组件，Vue具有模块化的特性，其余的子组件组成根组件的内部结构，main.js文件用作index.html与App.vue的关联

项目的前后端是通过反向代理的方式进行联动，使用Axios模块，同时为了让后端能够接收到前端的资源，需要配置跨域支持

## 后端结构及说明

![image-20230406093936430](C:\Users\201\AppData\Roaming\Typora\typora-user-images\image-20230406093936430.png)

## 系统操作流程

IDEA创建Vue项目，并初始化npm init，并进行安装那npm install；创建Springboot项目，并配置依赖项。

创建登录拦截器：对于项目而言，需要配置拦截器以此达到用户在未登录的情况下，无法对系统进行操作，主要分为两种方式，后端拦截器（只适用于前后端整合框架），前端拦截器（适用于前后端分离框架）。对于后端拦截器，将用户的信息保存在Session对象当中，然后访问页面时，通过判断是否存在用户变量来判断用户是否登录，从而判断是否需要进行拦截。对于前端拦截器，使用Vuex模块，同样需要存储用户的信息，但使用的时localStorage，即本地存储，然后通过判断路径是否为被拦截对象以及是否保存了用户的信息来判断是否需要进行拦截，从而进行页面的跳转。

