<a name="index">**Index**</a>

<a href="#0">设计及架构思想</a>  
&emsp;<a href="#1">1. 编程思想</a>  
&emsp;&emsp;<a href="#2">1.1. 面向对象编程(Object Oriented Programming，OOP)</a>  
&emsp;&emsp;<a href="#3">1.2. 面向过程编程</a>  
&emsp;&emsp;<a href="#4">1.3. 函数式编程</a>  
&emsp;<a href="#5">2. 六大设计原则</a>  
&emsp;&emsp;<a href="#6">2.1. 单一职责原则</a>  
&emsp;<a href="#7">3. MVC 模式</a>  
&emsp;<a href="#8">4. 系统架构</a>  
&emsp;&emsp;<a href="#9">4.1. 单体</a>  
&emsp;&emsp;<a href="#10">4.2. 集群</a>  
&emsp;&emsp;<a href="#11">4.3. 分布式</a>  
&emsp;&emsp;<a href="#12">4.4. 微服务</a>  
&emsp;&emsp;&emsp;<a href="#13">4.4.1. 拆分原则</a>  
&emsp;&emsp;&emsp;<a href="#14">4.4.2. DDD 领域驱动</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#15">4.4.2.1. 分层架构</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#16">4.4.2.2. 服务</a>  
&emsp;&emsp;&emsp;&emsp;&emsp;<a href="#17">4.4.2.2.1. 模型</a>  
&emsp;&emsp;&emsp;&emsp;&emsp;<a href="#18">4.4.2.2.2. 聚合</a>  
&emsp;&emsp;&emsp;&emsp;&emsp;<a href="#19">4.4.2.2.3. 工厂</a>  
&emsp;&emsp;&emsp;&emsp;&emsp;<a href="#20">4.4.2.2.4. 资源库</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#21">4.4.2.3. 界定的上下文</a>  
&emsp;<a href="#22">5. 相关资料</a>  
# <a name="0">设计及架构思想</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

## <a name="1">编程思想</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="2">面向对象编程(Object Oriented Programming，OOP)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
面向对象编程思想是以现实世界中事物，建立模型体现出来的抽象思维过程。
根据抽象的模型，依照事物之间的关系及方法进行操作，以求达到重用性、灵活性和扩展性的设计目的。
> 面向对象编程是把构成问题的事务分解成各个对象，建立对象的目的不是为了完成一个步骤，而是为了描叙某个事物在整个解决问题的步骤中的行为。

OOP=对象+类+继承+多态+消息，其中核心概念是类和对象。

特点： 封装、多态、继承

### <a name="3">面向过程编程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
面向过程编程就是分析出解决问题所需要的步骤，然后用函数把这些步骤一步一步实现，使用的时候一个一个依次调用就可以了。

### <a name="4">函数式编程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
函数式编程类似于面向过程的程序设计，但其思想更接近数学计算。允许把函数本身作为参数传入另一个函数，还允许返回一个函数。是一种抽象程度很高的编程范式，纯粹的函数式编程语言编写的函数没有变量。
> 面向过程编程体现的是解决方法的步骤，而函数式编程体现的是数据集的映射。

## <a name="5">六大设计原则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="6">单一职责原则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义：单一职责原则适用于类、接口、方法。

## <a name="7">MVC 模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
MVC是一种框架模式。经典MVC模式中，M是指业务模型，V是指用户界面，C则是控制器。

使用MVC的目的是将M和V的实现代码分离，从而使同一个程序可以使用不同的表现形式。其中，View的定义比较清晰，就是用户界面。


## <a name="8">系统架构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="9">单体</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="10">集群</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="11">分布式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="12">微服务</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

#### <a name="13">拆分原则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
垂直划分优先原则：应该根据业务领域对服务进行垂直划分，让团队能关注业务实现。

持续演进原则： 服务数量在非必要的情况下，应该逐步划分，持续演进，避免服务数量的爆炸性增长


#### <a name="14">DDD 领域驱动</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
基本元素：分层架构、实体、值对象、服务、模块、聚合、工厂、资源库


##### <a name="15">分层架构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

![image](https://gitee.com/rbmon/file-storage/raw/main/learning-note/design/systemdesign/structure.png)

![image](https://gitee.com/rbmon/file-storage/raw/main/learning-note/design/systemdesign/structureIntroduce.png)

- 应用层（接入层）：通常用来接收前端（展现层）的请求，转发给领域层获取请求结果，再组装结果返回前端。
- 基础设施层：作为其他层支撑的存在，最通俗的例子就是searchServ，正常的搜索服务都会集成ElasticSearch或者其他搜索功能，searchSearch封装了与基础服务集成的及细节，只暴露了领域需要的接口。


##### <a name="16">服务</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
当一个操作凸现为一个领域中的重要概念时，就需要为它建立一个服务了。以下是服务的 3 个特征：
1. 服务执行的操作涉及一个领域概念，这个领域概念通常不属于一个实体或者值对象。
2. 被执行的操作涉及到领域中的其他的对象。
3. 操作是无状态的。

> 考虑一个实际的 Web 报表应用的例子。报表使用存储在数据库中的数据，它们会基于模版产生。最终的结果是一个在 Web 浏览器中可以显式给用户查看的 HTML 页面。\
用户界面层被合并成 Web 页面，允许用户登录，选择所期望的报表，单击一个按钮就可以发出请求。应用层是非常薄的一个层，它位于用户界面和领域层以及基础设施层的中间位置。它在登录操作时，会跟数据库基础设施进行交互；在需要创建报表时会和领域层进行交互。领域层中包含了领域的核心部分，对象直接关联到报表。有两个这样的对象是报表产生的基础，它们是 Report 和Template。基础设施层将支持数据库访问和文件访问。


###### <a name="17">模型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
> 对一个大型的复杂项目而言，模型趋向于越来越大。模型到达了一个作为整体很难讨论的点，理解不同部件之间的关系和交互变得很困难。基于此原因，很有必要将模型组织进模块。模块被用来作为组织相关概念和任务以便降低复杂性的一种方法。

在设计中使用模块是一种增进内聚和消除耦合的方法。模块应该由在功能上或者逻辑上属于一体的元素构成，以保证内聚。模块应该具有定义好的接口，这些接口可以被其他的模块访问。

###### <a name="18">聚合</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
聚合是针对数据变化可以考虑成一个单元的一组相关的对象。聚合使用边界将内部和外部的对象划分开来。每个聚合有一个根。
> 这个根是一个实体，并且它是外部可以访问的唯一的对象。根可以保持对任意聚合对象的引用，并且其他的对象可以持有任意其他的对象，但一个外部对象只能持有根对象的引用。如果边界内有其他的实体，那些实体的标识符是本地化的，只在聚合内有意义。

一个简单的聚合的案例如下图所示。客户是聚合的根，并且其他所有的对象都是内部的。如果需要地址，一个它的拷贝将被传递到外部对象。
![image](https://gitee.com/rbmon/file-storage/raw/main/learning-note/design/systemdesign/aggreate.png)


###### <a name="19">工厂</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
工厂用来封装对象创建所必需的知识，它们对创建聚合特别有用。当聚合的根建立时，所有聚合包含的对象将随之建立，所有的不变量得到了强化。

###### <a name="20">资源库</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
使用一个资源库，它的目的是封装所有获取对象引用所需的逻辑。领域对象不需处理基础设施，以得到领域中对其他对象的所需的引用。只需从资源库中获取它们，于是模型重获它应有的清晰和焦点。

提供基于某种条件选择对象的方法，返回属性值符合条件的完全实例化的对象或者一组对象，继而封装实际的存储和查询技术。仅对真正需要直接访问的聚合根提供资源库。让客户程序保持对模型的关注，把所有的对象存储和访问细节委托给资源库。

数据驱动强调的是数据结构，也就是通过分析需求，来确定整体数据结构，根据表之间的关系划分服务。


##### <a name="21">界定的上下文</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
界定的上下文：主要的思想是定义模型的范围，画出它的上下文的边界，然后尽最大可能保持模型的一致性。

被创建的上下文有清晰的角色和被指明的关系。在上下文之间：
1. 共享内核（Shared Kernel）和
2. 客户-供应商（Customer-Supplier）是具有高级交互的模式。

## <a name="22">相关资料</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
[微服务拆分方法论](https://blog.csdn.net/no_game_no_life_/article/details/103390169)