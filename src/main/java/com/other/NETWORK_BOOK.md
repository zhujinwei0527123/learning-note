# 计算机网络-自顶而下
## 计算机网络与因特网

### 基本概念
因特网定义：
- 从因特网的基本硬件和软件组件描述
- 为分布式应用提供服务的联网基础设置来描述

**主机(端系统)**：与因特网连接的设备，包括客户PC和服务器\
**分组(package)**：当一台端系统要向另一台端系统发送数据时，发送端系统将数据分段，并为每段加上首部字节。形成的信息包用计算机网络的术语称为分组。\
**分组交换机(packet switch)**: 常见的类型有路由器(router)、链路层交换机(link-layer switch)\
**因特网服务提供商(Internet Service Provider, ISP)**：自身就是一个由多台分组交换机和多段通信链路组成的网络。
> 包括网线、住宅ISP、公司ISP、机场及公共场所提供WiFi接入的ISP

协议：定义了两个或多个通信实体之间交换的报文的格式和顺序，以及发送或接收一条报文或其他事件所采取的动作。
> 典型的陌生人之间的交互，可以用"您好？" 来发起一段对话，其实就是人之间交互的一个协议

带宽：连接期间链路为每条连接专用一个频段，该频段的宽度为带宽
### 接入网

![image](https://gitee.com/rbmon/file-storage/raw/main/learning-note/other/networkbook/connetNet.png)

**家庭接入**：数字用户线(Digital Subscriber Line, DSL)和电缆
> 住户从提供本地电话接入的电话公司获得DSL因特网接入。当使用DSL时，用户的本地电话公司是它的ISP。

家庭电话线同时承载了数据和传统的电话信号，他们用不同的频率进行编码，使得单根DSL线路像是有3跟独立线路，因此电话呼叫和互联网连接能够同时共享DSL链路。
- 高速下行信道，位于50kHz到1MHz频段
- 中速下行信道，位于4kHz到50kHz频段
- 普通的双线电话信道，位于0到4kHz频段

**光纤到户(FTTH)**：有源光纤网络(AON)和无源光纤网络(PON)\
**企业和家庭接入**：以太网和WiFi\
**广域无线接入**：3G和LTE

### 物理媒体
物理媒体分成两种类型：导引型媒体和非导引型媒体
- 导引型：电波沿着固体媒体前进，如光缆、双绞铜线、同轴电缆
- 非导引型：电波在空气或外层空间中传播，如无线局域网或数字卫星频道

### 网络核心
通过网络链路和交换机移动数据的两个基本方法：电路交换(circuit switching)和分组交换(package switching)

分组交换的性能优于电路交换的性能：
1. 分组交换提供了更好的带宽共享。电路交换不考虑需求，而是预先分配传输链路使用，使得**已分配而并不需要的链路时间**并未被利用。分组交换按需分配链路使用。
2. 分组交换的实现比电路交换更简单、更有效，实现成本更低。
#### 分组交换
分组交换：从源端系统向目的地端系统发送一个报文，源将长报文划分为较小的数据块。在源和目的地之间，每个分组都通过通信链路和分组交换机传输。

![image](https://gitee.com/rbmon/file-storage/raw/main/learning-note/other/networkbook/packetswitch.png)

**存储转发传输**：在交换机能够开始向输出链路传输该分组的第一个比特之前，必须接收到整组。

每台分组交换机有多条链路与之相连，对于每条相连的链路，该分组交换机具有一个输出缓存(输出队列)，用于存储路由器准备发往该链路的分组。
> 排队时延：链路正忙于传输其他分组，其后到达的分组必须等待。\
> 分组丢失：到达的分组发现缓存已经被其他分组充满。

#### 电路交换
电路交换中，在端系统间通信会话期间，预留了端系统间沿路径通信所需要的资源(缓存、链路传输速率)

![image](https://gitee.com/rbmon/file-storage/raw/main/learning-note/other/networkbook/circuitswitch.png)

电路交换网络中的复用：\
**频分复用**(Frequency-Division Multiplexing, FDM)：链路的频率域被划分为多个频段。\
**时分复用**(Time-Division Multiplexing, TDM)：一条TDM链路，时间被划分为固定期间的帧，并且每个帧又被划分为固定数量的时隙。

![image](https://gitee.com/rbmon/file-storage/raw/main/learning-note/other/networkbook/fdm-tdm.png)

#### 网络的网络
网络结构1：单一的全球传输ISP互联所有接入ISP。\
网络结构2：多个全球传输ISP互联所有接入ISP。\
网络结构3：多层的ISP结构，多个竞争的全球传输ISP、每个区域多个竞争的ISP(市级ISP、省级ISP、国家级ISP)并与上层区域连接。第一层ISP不向任何人付费，下层ISP向上层付费。\
网络结构4：在结构3基础上增加存在点(Point of Presence,PoP)、多宿、对等和因特网交换点。有接入ISP、区域ISP、PoP、多宿、对等和IXP组成。\
网络结构5：在结构4顶部增加内容提供商网络(content provider network)。内容服务商如Google，构建自己的网络与底层ISP对等(无结算)，对于较高层ISP可以通过IXP与其连接，减少其向顶层ISP支付的费用。

相关概念：
- 存在点(Point of Presence,PoP)：存在于登记结构的所有层次，但底层(接入ISP)等级除外。？？
- 多宿(multi-home)：任何一个ISP可以与两个或者多个提供商ISP连接。保证其中一个ISP故障，也可以通过其他ISP保障服务。
- 对等(peer)：位于相同等级结构层次的邻近一对ISP对等，而不是通过上游的ISP传输，节省通信流量。对等表示两个对等ISP之间无需进行流量结算。
- 因特网交换点(Internet Exchange Point, IXP)：IXP是一个汇总点，多个ISP能够在这里一起对等。

![image](https://gitee.com/rbmon/file-storage/raw/main/learning-note/other/networkbook/isbnetwork.png)

#### 分组交换中的时延、丢包和吞吐量
节点总时延 = 节点处理时延 + 排队时延 + 传输时延 + 传播时延
- 处理时延：检查分组首部和决定将该分组导向何处所需要的时间、检查比特级别的差错。
- 排队时延：分组在链路上等待传输。时间取决于先到达正在排队等待向链路传输的分组数量。
- 传输时延：将所有分组的比特推向链路所需要的时间。即推出分组所需要的时间，是分组长度和链路传输速率的函数，与两路由器的距离无关。
- 传播时延：从链路的起点到路由器B传播所需要的时间。

排队时延与丢包：一条链路钱的队列只有有限容量，随着流量强度接近1，到达的分组发现队列已满就会**丢弃**该分组，即该分组将丢失。

### 协议分层
5层因特网协议栈：
1. 应用层：HTTP、SMTP、FTP。位于应用层的信息分组称为报文(message)。
2. 运输层：TCP、UDP。因特网的运输层在应用程序端之间传送应用层报文。运输层的分组称为报文段(segment)
3. 网络层：IP。负责将数据报(datagram)的网络层分组从一台主机转移到另一台主机。
4. 链路层：为了将分组从一个节点移动到下一饿节点，网络层必须依靠链路层的服务。链路层的服务取决于应用于该链路的特定链路层协议，如可靠传递的协议(不同于TCP协议)。链路层的分组称为帧(frame)
5. 物理层：物理层的任务是将链路的帧中的一个个比特(bit)从一个节点移动到下一个节点。协议与链路相关，且进一步与传输媒体相关。

封装过程：
1. 发送主机端，一个**应用层报文**(application-layer message)被传输给运输层。
2. 运输层收取到报文并附上附加信息(运输层首部信息)，该报文将被**接收端**的运输层使用。应用层报文和运输层首部信息构成**运输层报文段(transport-layer segment)**，传输给网络层。
    > 运输层报文段：附加的信息包括应用层报文、差错检测位信息(接收方判断报文是否改变)
3. 网络层接收到报文段，增加了如源和目的端系统地址等网络层首部信息，生成**网络层数据报(network-layer datagram)**，发送链路层。
4. 链路层接收到数据报，增加链路层首部信息并生成**链路层帧(link-layer frame)**

在每一层一个分组都有两种类型的字段：首部字段和**有效荷载字段(payload field)**

![image](https://gitee.com/rbmon/file-storage/raw/main/learning-note/other/networkbook/layers.png)

### 网络攻击
- 病毒攻击：通过软件文件感染设备
- 分组嗅探器：如WiFi连接放置一台被动的接收器，接收器就能得到每个分组的副本。即网络传输的所有信息。
- IP哄骗
- 拒绝服务攻击(Denial-of-Service attack, DoS attack)

DoS攻击分为以下三类：
1. 弱点攻击：利用主机上软件或系统的漏洞，进行攻击，造成服务器停止运行或主机崩溃。
2. 带宽洪泛：向目标主机发送大量的分组，使目标的接入链路变得拥塞，合法的分组无法到达服务器。
3. 连接洪泛：攻击者在目标主机中创建大量的半开或全开TCP连接。该主机因这些伪造的连接，而无法接受合法的连接。

## 应用层
Web应用程序的两种应用程序体系结构：
1. CS架构。用户主机 - Web服务器主机的服务器程序，如搜索服务、facebook、instagram
2. 对等体系结构(P2P)。P2P文件共享系统。参与文件共享的社区中，每台主机中都有一个程序。如BitTorrent、迅雷

![image](https://gitee.com/rbmon/file-storage/raw/main/learning-note/other/networkbook/application-architure.png)


### 可供应用程序使用的运输服务
应用程序服务的对于运输协议要求可分为四大类：可靠数据传输、吞吐量、定时和安全性

**可靠数据传输**：一个协议确保由应用程序一端发送的数据正确、完全地交付给应用程序的另一端。
> 多媒体应用、交谈式音频/视频，能够承受一定量的数据丢失，为容忍丢失的应用。

**吞吐量**：指发送进程能够向接收进程交付比特的速率。即运输层能够以某种特定的速率提供确保的可用吞吐量。
> **带宽敏感的应用**：具有吞吐量要求的应用程序。如一些多媒体应用，保证用户使用体验，必须保证一定的带宽速率\
> **弹性应用**：能够根据当时可用的带宽或多或少利用可供使用的吞吐量。如电子邮件、文件传输

**定时(确定的时延)**：如发送方注入到套接字中的每个比特到达接收方的套接字不迟于100ms。相关需求的应用程序如因特网电话、多方游戏等

**安全性**：运输协议能够为应用程序提供一种或多种安全性服务。

#### TCP服务
TCP服务包括面向连接服务和可靠数据传输服务。另外TCP还具有拥塞控制机制

- 面向连接服务：应用层报文开始流动前，先建立连接。该连接是**全双工**的，即连接双方的进程可以在此连接上同时进行报文收发。
- 可靠的数据传输服务：通信进程能够依靠TCP，**无差错、按适当顺序交付**所有发送的字节。
- 拥塞控制机制：当发送方和接收方之间的网络出现拥塞时，TCP的拥塞控制机制会抑制发送进程。
> 加密传输机制：TCP提供安全套接字层(Secure Sockets Layer, SSL)，用于加密的安全性服务。


#### UDP服务
UDP是一种不提供不必要服务的轻量级运输协议，仅提供最小服务。在通信前没有握手过程，仅提供了一种不可靠数据传输服务，不保证报文能到达接收进程。\
UDP没有拥塞控制机制，所以UDP可以用它选定的任何速率向其下层(网络层)注入数据。
> 实际端到端的吞吐量可能小于该速率，这可能是由于中间链路的带宽受限或因为拥塞而造成的。


#### 因特网运输协议所不提供的服务
在TCP和UDP的描述中，没有对吞吐量和定时保证的讨论，这两个指标要求，目前的因特网运输协议并没有提供。

![image](https://gitee.com/rbmon/file-storage/raw/main/learning-note/other/networkbook/applicationAndProtocol.png)

### 应用层协议
应用层协议定义了运行在不同端系统上的应用程序如何互相传递报文。\
常规应用层的协议定义：
- 交换报文类型，如请求和响应报文。
- 各种报文类型的语法。
- 字段的语义
- 确定进程发送报文的时间跟方式，对报文进行响应的规则。

> RFC文档定义的应用层协议如HTTP。邮箱的应用层协议SMTP。

### Web与HTTP
web的应用层协议是超文本传输协议(HyperText Transfer Protocol, HTTP)
- 使用TCP作为它的支撑运输协议
- 无状态的协议(不保存客户的信息)
- 默认使用持续连接方式，支持配置非持续连接方式

![image](https://gitee.com/rbmon/file-storage/raw/main/learning-note/other/networkbook/httpRequestResponse.png)

#### 非持续连接和持续连接
- 非持续连接：每个请求/响应对是经一个单独的TCP连接。每个请求和响应使用同一个TCP连接。
- 持续连接：所有请求及其响应经相同的TCP连接发送。

往返时间(Round-Trip Time, RTT)，指一个短分组从客户到服务器然后再返回客户所花费的时间。
> 包括分组传播时延、分组在中间路由器和交换机上的排队时延以及分组处理时延

非持续连接缺点：
1. 必须为每一个请求对象建立和维护一个全新的连接。客户及服务器都需要分配TCP的缓冲区和变量，给Web服务器带来负担。
2. 每个对象传输经受两倍RTT的交付时延，即一个RTT用于创建TCP，另一个RTT用于请求和接收一个对象。

持续连接:\
Http1.1 持续连接，服务器在发送响应后保持该TCP连接打开。在相同的客户与服务器之间，后续的请求及响应能在相同的连接进行传送。
> 一个完整的Web页面可以用单独持续TCP连接进行传送，同一个客户的不同Web页面请求在请求同一个服务器，同样使用相同的TCP连接。

Http2，允许相同连接中多个请求和回答交错，并增加了在该连接中请求HTTP报文请求和回答机制。

#### HTTP请求报文格式

```
GET /somedir/page.html HTTP/1.1
Host: www.someschool.edu
Connection: close
User-agent: Mozilla/5.0
Accept-language: fr
```

`GET /somedir/page.html HTTP/1.1`\
**请求行**：由方法字段、URL字段和HTTP版本字段组成。方法字段包括GET、POST、HEAD、PUT、DELETE

`Host: www.someschool.edu`\
首部行Host：指明了该对象所在的主机，是Web代理高速缓存所要求的。

`Connection: close`\
首部行Connection：该浏览器告诉浏览器不要麻烦的使用持续连接，要求浏览器在发送完被请求对象后就关闭该条连接。

`User-agent: Mozilla/5.0`\
首部行User-agent：指明向服务器发送请求的浏览器类型。

`Accept-language: fr`\
首部行Accept-language：表示语言版本

![image](https://gitee.com/rbmon/file-storage/raw/main/learning-note/other/networkbook/generalFormatHttp.png)
- Request line：请求行
- Header lines：多个首部行
- Blank line
- Entity body：POST方法使用的请求参数体

HTTP方法：
- HEAD方法类似于GET方法。服务器接收到HEAD方法的请求时，将会用一个HTTP报文进行响应但不返回对象，HEAD方法常用于调试跟踪。
- PUT方法常与Web发行工具联合使用。允许用户上传对象到指定的Web服务器上的指定路径。也可以用于对象上传。
- DELETE方法允许用户或者应用程序删除Web服务器上的对象。

#### HTTP响应报文格式
```
HTTP/1.1 200 OK
Connection: close
Date: Tue, 09 Aug 2011 15:44:04 GMT
Server: Apache/2.2.3 (CentOS)
Last-Modified: Tue, 09 Aug 2011 15:11:03 GMT
Content-Length: 6821
Content-Type: text/html
(data data data data data ...)
```

`HTTP/1.1 200 OK`\
状态行：包括协议版本字段、状态码和响应的状态信息

`Connection: close`\
首部行Connection: 告诉客户端，发送完报文后将关闭该TCP连接。

`Date: Tue, 09 Aug 2011 15:44:04 GMT`\
首部行Date: 指服务器产生并发送该响应报文的时间和日期。时间为从系统中检索到该对象将该对象插入报文，并发送该响应报文的时间。

`Server: Apache/2.2.3 (CentOS)`\
首部行Server: 服务器版本信息

`Last-Modified: Tue, 09 Aug 2011 15:11:03 GMT`
首部行Last-Modified: 表示对象创建或最后的修改时间。该首部行与缓存的应用有关。

`Content-Type: text/html`
首部行Content-Type: 表示响应的对象类型。

状态码：
- 200 OK： 请求成功
- 301 Moved Permanently：请求的对象已经被永久转移了，新的URL定义在响应报文的首部行Location。客户端软件将自动获取新的URL地址。
- 400 Bad Request： 一个通用的差错代码，指示该请求不能被服务器理解。
- 404 Not Found：被请求的文档不再服务器上。
- 505 HTTP Version Not Supported: 服务器不支持报文使用的HTTP版本

![image](https://gitee.com/rbmon/file-storage/raw/main/learning-note/other/networkbook/generalFormatHttpResponse.png)
  

#### 用户与服务器的交互：cookie
HTTP服务器是无状态的，该设计简化了服务器的设计。

cookie设计的4个组件：
1. 在HTTP响应报文中的一个cookie首部行。`Set-cookie: 1678`
2. 在HTTP请求报文中一个cookie首部行。`Cookie: 1678`
3. 用户端系统中保留一个cookie文件，并由浏览器管理。
4. 位于Web站点的一个后台数据库。

交互过程：
![image](https://gitee.com/rbmon/file-storage/raw/main/learning-note/other/networkbook/keepingUserStateWithCookie.png)

#### 代理服务器
Web缓存器(Web cache) 也加代理服务器(proxy server)，它是能够代表初始Web服务器来满足HTTP技术的网络实体。Web缓存器有自己的磁盘存储空间，并在存储空间中保存最近请求过的对象的副本。

![image](https://gitee.com/rbmon/file-storage/raw/main/learning-note/other/networkbook/webCache.png)
交互过程：
1. 浏览器创建一个到Web缓存器的TCP连接，并发起HTTP请求。
2. Web缓存器检查本地是否存储了该对象的副本。如果存在，Web缓存器就向客户浏览器用HTTP响应报文返回。
3. 如果Web缓存器没有该对象，它就打开一个与该对象的初始服务器的TCP连接，并发起该对象的HTTP请求。服务器收到Web缓存器请求后，返回具有该对象的HTTP响应。
4. 当Web服务器接收到该对象时，它在**本地磁盘存储空间存储一份副本**，冰箱客户端浏览器用HTTP报文响应。

Web缓存器的好处：
1. 可以大大减少对客户端的响应时间，特别是客户与服务器之间的带宽远低于客户与Web缓存器间的瓶颈带宽的场景。因为客户与Web缓存器之间常常会有一个高速的连接。
2. Web缓存器可以大大减少接入链路到互联网的通信量，从整体上大大减低因特网上的Web流量，从而改善所有应用性能。

例子：

![image](https://gitee.com/rbmon/file-storage/raw/main/learning-note/other/networkbook/webCacheExample.png)

Institutional network为一个高速的100 Mbps的内部局域网，接入因特网的带宽为15 Mbps，假设机构内部`15请求/秒`，每个请求传输1M的数据
- 内部传输时延：`15*1M/100M= 0.15s`
- 因特网传输时延：`15*1M/15M= 1s`
- 总时延：因特网传输时延+ 内部传输时延+ 服务处理时延(假设为2s)

当机构的请求数增加大于15Mbps，链路的请求响应时延就会越来越长，因为链路无法完全接受就会出现累计的现象\
一个解决方案是可以通过把接入因特网的带宽增大，但是费用过高。\
另一个方案是增加一个Web缓存服务器，实践中的缓存命中率为0.2~0.7之间。假设缓存命中率为0.4,则整体的平均时延甚至比增加带宽的方案表现更为优秀\
总结一下：缓存器减少了需要**发送因特网的部分流量**，同时**节省了那部分流量的服务请求跟响应时间**。

缓存实现机制：通过条件GET方法实现，条件GET方法为首先一个HTTP GET请求，并且请求报文包含一个`If-Modified-Since`的首部行。\
实现机制：
1. Web缓存器代理HTTP请求时，缓存了对象同时缓存了对象的最后修改日期。
2. 若下次请求时，缓存仍然存在，则缓存器会发送**条件GET命令到服务器执行最新检查**。
3. 若对象没有修改，则服务器返回`304 Not Modified`状态码，告诉缓存服务器，可以使用该对象，响应报文为空，节省了带宽。

如下为两条件GET请求：
```
GET /fruit/kiwi.gif HTTP/1.1
Host: www.exotiquecuisine.com
If-modified-since: Wed, 7 Sep 2011 09:23:24

HTTP/1.1 304 Not Modified
Date: Sat, 15 Oct 2011 15:39:29
Server: Apache/1.3.0 (Unix)
(empty entity body)
```

### 因特网的电子邮件

![image](https://gitee.com/rbmon/file-storage/raw/main/learning-note/other/networkbook/internetEmailSystem.png)



### DNS：因特网的目录服务

域名管理系统(Domain Name System, DNS) ，提供将主机名转换为其背后的IP地址的服务。
1. 一个由分层的DNS服务器(DNS server)实现的分布式数据库。
2. 一个是的主机能够查询分布式数据库的分布式协议。
> DNS服务器通常是运行BIND软件(Berkeley Internet Name Domain)的UNIX机器\
> DNS协议运行在**UDP协议**之上，使用**53 端口**

DNS是通过客户-服务器模式提供的重要网络功能。DNS协议是应用层协议，其原因在于：
1. 使用客户-服务器模式运行在通信的端系统之间。
2. 在通信的端系统之间通过下层端到端的运输协议来传送DNS报文。
> DNS的作用非常不同于HTTP与SMTP，因为其不直接和用户打交道。\
> DNS通过采用来位于网络边缘的客户和服务器，实现了关键的名到地址转换功能。


DNS的重要服务：\
**主机别名(host aliasing)**：有着复杂主机名的主机能拥有一个或多个别名。
> 如主机`relay1.west-coast.enterprise.com`，可能还有别名`enterprise.com` 和 `www.enterprise.com`。此时`relay1.west-coast.enterprise.com`称为规范主机名

**邮件服务器别名(mail server aliasing)**：与主机别名类似，作用于邮件服务器地址上。
> 事实上，MX记录允许一个公司的邮件服务器和Web服务器使用相同的主机名。

负载分配(load distribution)：DNS也用于冗余的服务器之间进行负载分配。
> 如一个IP地址集合与同一个规范主机名相联系，在DNS进行解析时，循环IP地址进行响应，实现了Web服务器之间的负载分配。


DNS的为IP地址提供了目录服务，但也带来了额外的时延，使用主机地址请求时需要额外请求DNS拿到真实的IP地址。因此想要获得IP地址应尽量缓存在"附近的"DNS服务器中，这有助于减少DNS的网络流量和DNS的平均时延。

#### DNS工作机理

DNS的工作流程简述：
1. 用户主机应用程序需要将主机名转换为IP地址，应用程序调用DNS客户端。
2. 用户主机上的DNS接收到请求的主机名，向网络发送一个DNS的查询报文。
3. 所有的DNS请求和响应报文使用UDP数据报经端口53发送，经过若干毫秒到若干秒的时延后，用户主机上的DNS收到回答报文
4. 用户主机的DNS将映射结果传递到调用DNS的应用程序。


单一的DNS服务器架构会出现单点故障、通信容量、远距离集中式数据库、维护等问题，因此DNS的服务器使用是分布式的架构。

**分布式、层次数据库**\
为了处理拓展性问题，DNS使用了大量的DNS服务器，它们以层次方法组织，并且分布在全世界范围内。没有一台DNS服务器拥有因特网上所有主机的映射。

DNS服务器大致可以分为3种类型
- 根DNS服务器(Root DNS server)：有400多个根名字服务器遍及世界，由13个不同的组织管理。
- 顶级域DNS服务器(Top-Level Domain, TLD)：对于每个顶级域如(如com、org、net、edu和gov) 和 所有国家的顶级域(如uk、fr、ca和jp)，都有TLD服务器或服务器集群。
- 权威DNS服务器(authoritative DNS servers)：在因特网上具有公共可访问主机的每个组织机构必须提供公共可访问的DNS记录，这些记录将主机名映射成IP地址。实现的方式有两种，一是实现自己的DNS服务器，另一种是支付费用，让某个服务提供商的DNS服务器帮忙记录。

![image](https://gitee.com/rbmon/file-storage/raw/main/learning-note/other/networkbook/hierarchyDNSServers.png)

在更下层的中还有**本地DNS服务器**(local DNS server)，对于居民区ISP，本地DNS服务通常与主机相隔不超过几个路由器。当主机发出DNS请求时，该请求被发往本地DNS服务器，该服务器起着代理作用，并转发请求到DNS服务层次结构中。



![image](https://gitee.com/rbmon/file-storage/raw/main/learning-note/other/networkbook/interactionOfDNSServer.png)
1. 用户请求主机，发向本地DNS服务器。
2. 本地DNS，请求根DNS获取地址
3. 根DNS，根据前缀返回负责相关前缀的TLD的IP地址列表。
4. 本地DNS依次请求TLD发送查询报文，TLD返回对应的权威DNS服务器IP地址。
5. 最后本地DNS请求权威DNS服务器发送查询报文，获取最终真实的IP

上述过程中发送了8份DNS报文：4份查询报文4份回答报文。


#### DNS缓存
为了改善时延性能并减少在因特网上到处传输的DNS报文数量，DNS广泛使用了缓存技术。

某个DNS服务器接收到一个回答时，它能够缓存包含在该回答中的任何信息。由于主机和主机名和IP地址间的映射并不是永久的，DNS服务器在一段时间(**通常为2天**)后将丢弃缓存信息

#### DNS记录和报文
DNS服务器存储了资源记录(Resource Record, RR)，RR提供了主机名到IP地址的映射。有下列关键信息：`(Name, Value, Type, TTL)`

TTL是记录的生存时间，Type类型可以分为4大类：
1. Type=A，则Name是主机名，Value 为主机名对应的IP地址，提供了标准的主机名到IP地址的映射。\
`(relay1.bar.foo.com, 145.37.93.126, A)`
2. Type=NS，则Name是个域(如foo.com)，而Value是个知道如何获取该域中主机IP地址的**权威DNS服务器的主机名**。\
`(foo.com, dns.foo.com, NS)`
3. Type=CNAME，则Value是别名为Name的主机对应的规范主机名。该记录提供一个主机名对应的**规范主机名**。
`foo.com, relay1.bar.foo.com, CNAME`
4. Type=MX，则Value是别名为Name的**邮件服务器的规范主机名**。MX记录允许邮件服务器具有简单的别名。一个公司的邮件服务器和其他服务器可以使用相同的别名\
`(foo.com, mail.bar.foo.com, MX)`
> 为了获取邮件服务器的规范主机名，DNS客户应该请求一条MX记录。\
> 为了获取其他服务器的规范主机名，DNS客户应该亲故CNAME记录。\
> 两者一个MX为邮件服务器服务，一个CNAME为其他服务器服务。


#### DNS报文

![image](https://gitee.com/rbmon/file-storage/raw/main/learning-note/other/networkbook/DNSMessageFormat.png)

#### DNS脆弱性
DDoS分布式拒绝服务带宽洪泛攻击，攻击根DNS服务器或TLD，对DNS的影响很小，主要是本地的DNS的缓存可以部分缓解。

中间人攻击，通过拦截请求并伪造回答。但是很难试下， 因为要求截获分组或扼制住服务器。

DNS自身显示了对抗攻击 令人惊讶的健壮性。