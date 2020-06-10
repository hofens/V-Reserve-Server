# V-Reserve-Server
微预约小程序服务端


## 环境
#### 小程序
请看另一个仓库 V-Reserve-Applet

#### 服务器

SpringBoot 2.3.1

mysql 5.6

Ubuntu 18.04




## 项目说明

### 一、需求分析

#### 1.1 客户端1——业务预约Web端

将不同的可预约业务展示在web端，用户根据需求，选择业务类型和时间，用户输入身份证号、手机号后即可预约成功。同时，可在界面上显示已预约的人数、余号数量等，

#### 1.2 客户端2——模拟现场取号设备

该设备具有预约取号和现场取号两大功能。

预约取号时要求输入身份证号码，如系统匹配成功，则显示办事编号，否则显示无预约等信息。

现场取号功能与客户端1的预约功能基本相同，但只需选择业务类型，无需选择时间，按系统默认分配办事编号。

#### 1.3 服务器端

搭建Web服务器、数据库，并做好数据库表的设计，支持多线程。

将客户端1传输过来的预约信息存储至数据库中。

客户端2预约取号时，验证数据信息请求，决定是否有办事编号，并将相应信息显示在客户端2中，现场取号同理。



### 二、数据库设计

#### 2.1 业务表

业务表包含id、服务名、已预约人数、剩余数量字段组成。4个业务，医社保办理、护照通行证办理、房产证办理、水电煤气业务办理；每个业务初始化总数量为50个

![](https://gitee.com/hofe/graph/raw/master/img/20200610150019.png)



#### 2.2 预定表

预定表包含id、身份证号、电话号码、服务类别id、预定取票时间、办事编号字段组成。

![](https://gitee.com/hofe/graph/raw/master/img/20200610150215.png)



#### 2.3 用户表

用户表包含id、用户名、身份证号、电话号码字段。

![](https://gitee.com/hofe/graph/raw/master/img/20200610150700.png)



### 三、详细设计

#### 3.1 预约功能

用户选择具体的要办理的业务类型，在业务详情页显示当前预约人数，以及预约表单，当余号数量足够时可进行提交表单，余量不足时则表单提交失败。前端发送身份证号、电话号码、以及预约的日期时间；后端收到请求后，将记录存入数据库，同时也会以预约的时间生成办事编号。

**接口**

![](https://gitee.com/hofe/graph/raw/master/img/20200610154053.png)



![](https://gitee.com/hofe/graph/raw/master/img/20200610155229.png)



#### 3.2 取号功能

##### 3.2.1 预约取号

用户输入身份证即可获取之前预约的记录。前端携带业务类型、用户身份证号发请求至后端，后端通过取号类型得知是预约取号，则从数据库中通过身份证号查出记录，再返回给前端；返回结果包含办事编号（预约时存入）。

##### 3.2.2 现场取号

用户选择要办理的业务类型即可获取记录。前端携带业务类型id与取号类型发送请求至后端，后端判断是现场取号后，不用从数据库中读取数据，直接以当前时间生成办事编号，发送给前端。

**接口**

![](https://gitee.com/hofe/graph/raw/master/img/20200610155925.png)

![](https://gitee.com/hofe/graph/raw/master/img/20200610155955.png)



#### 3.3 预约记录

用户可查看自己预约的记录，包含预约编号、预约的身份证号码、预定取票时间信息。当预约成功或取票成功，预约历史页下拉刷新即可看到变化。

**接口**

![](https://gitee.com/hofe/graph/raw/master/img/20200610160452.png)





### 四、演示

#### 4.1 预约客户端



<img src="https://gitee.com/hofe/graph/raw/master/img/20200610143821.png" style="zoom: 50%;" />

点击要办理的具体业务，顶部会显示业务类型、已预约人数、余票，余票数量为0则不可再进行预约

<img src="https://gitee.com/hofe/graph/raw/master/img/20200610144123.png" style="zoom:50%;" />



余量充足的情况下可进行预约，并得到预约编号

<img src="https://gitee.com/hofe/graph/raw/master/img/20200610144243.png" style="zoom:50%;" />

#### 4.2 预约历史

点击底部菜单最右边可查看预约历史

<img src="https://gitee.com/hofe/graph/raw/master/img/20200610144400.png" style="zoom:50%;" />



#### 4.3 取票客户端

分为现场取票部分和预约取票两部分

现场取票通过选择业务进行取号

<img src="https://gitee.com/hofe/graph/raw/master/img/20200610144601.png" style="zoom:50%;" />

预约取票通过输入身份证号码进行取票

<img src="https://gitee.com/hofe/graph/raw/master/img/20200610144754.png" style="zoom:50%;" />



未预约则提示无预约记录

<img src="https://gitee.com/hofe/graph/raw/master/img/20200610144850.png" style="zoom:50%;" />



取票成功之后可发现，记录已经删除

<img src="https://gitee.com/hofe/graph/raw/master/img/20200610145000.png" style="zoom:50%;" />





