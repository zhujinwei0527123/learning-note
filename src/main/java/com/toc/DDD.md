<a name="index">**Index**</a>

<a href="#0">领域驱动设计(Domain Driven Design,DDD)</a>  
&emsp;<a href="#1">1. 使用DDD场景</a>  
&emsp;&emsp;<a href="#2">1.1. 两个转变</a>  
&emsp;&emsp;<a href="#3">1.2. 领域驱动设计统一过程（DDDRUP）</a>  
&emsp;<a href="#4">2. 分层架构</a>  
&emsp;<a href="#5">3. 战略建模</a>  
&emsp;&emsp;<a href="#6">3.1. 通用语言(Ubiquitous Language)</a>  
&emsp;&emsp;<a href="#7">3.2. 限界上下文(Bounded Context)</a>  
&emsp;&emsp;&emsp;<a href="#8">3.2.1. 上下文关系</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#9">3.2.1.1. 共享内核(Shared Kernel)</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#10">3.2.1.2. 客户-供应商(Customer-Supplier)</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#11">3.2.1.3. 顺从者/遵奉者</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#12">3.2.1.4. 隔离通道(Separate Way)/分离方式</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#13">3.2.1.5. 开放主机服务(Open Host Service)</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#14">3.2.1.6. 防崩溃层(Anticorruption Layer)</a>  
&emsp;&emsp;&emsp;<a href="#15">3.2.2. 大泥球</a>  
&emsp;&emsp;<a href="#16">3.3. 领域与子领域</a>  
&emsp;<a href="#17">4. 战术建模</a>  
&emsp;&emsp;<a href="#18">4.1. 实体(Entity)</a>  
&emsp;&emsp;<a href="#19">4.2. 值对象(Value Object)</a>  
&emsp;&emsp;<a href="#20">4.3. 领域服务(domain service)</a>  
&emsp;&emsp;<a href="#21">4.4. 模块(module)</a>  
&emsp;&emsp;<a href="#22">4.5. 领域事件(domain event)</a>  
&emsp;<a href="#23">5. 生命周期</a>  
&emsp;&emsp;<a href="#24">5.1. 聚合(aggregate)</a>  
&emsp;&emsp;<a href="#25">5.2. 工厂(factory)</a>  
&emsp;&emsp;<a href="#26">5.3. 资源库(repository)</a>  
&emsp;<a href="#27">6. 四种模型</a>  
&emsp;<a href="#28">7. 代码实现</a>  
&emsp;<a href="#29">8. 参考资料</a>  
# <a name="0">领域驱动设计(Domain Driven Design,DDD)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

基本元素：分层架构、实体、值对象、服务、模块、聚合、工厂、资源库

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/design/ddd/structure.png)

## <a name="1">使用DDD场景</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

领域驱动指的是以领域作为解决问题切入点，面对业务需求，先提炼出领域概念，并构建领域模型来表达业务问题，而构建过程中我们应该尽可能避免牵扯技术方案或技术细节。而编码实现更像是对领域模型的代码翻译，代码（变量名、方法名、类名等）中要求能够表达领域概念，让人见码明义。

### <a name="2">两个转变</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
结合实践经验，“领域驱动”是一种**思维模式转变**\
实践 DDD 以前，**最常使用的是数据驱动设计**。它的核心思路针对业务需求进行数据建模：**根据业务需求提炼出类**，然后通过 ORM 把类映射为表结构，并根据读写性能要求使用范式优化表与表之间的关联关系。数据驱动是从技术的维度解决业务问题，得出的**数据模型是对业务需求的直接翻译**，并没有蕴含稳定的领域知识/规则。一旦需求发生变化，数据模型就得发生变化，对应的库表的设计也需要进行调整。**这种设计思维导致变化从需求穿透到了数据层**，中间并没有稳定的，不易变的层级进行阻隔，最终导致系统响应变化的能力很差。

**协同方式转变**：\
DDD 通过解锁新角色”领域专家"以及模型驱动设计，**有效地降低产品和研发的认知差异**。领域专家是具有丰富行业经验和领域知识储备的人，他们能够在易变的、定制化的需求中提炼出清晰的边界，稳定的、可复用的领域概念和业务规则，并携手产品和研发共同构建出领域模型。

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/design/ddd/cooperate.png)


### <a name="3">领域驱动设计统一过程（DDDRUP）</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
DDDRUP 可以串联 DDD 的所有概念和模式，实施DDD的设计过程

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/design/ddd/dddrup.png)

## <a name="4">分层架构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/design/ddd/structureIntroduce.png)
- 应用层(接入层)：通常用来接收前端(展现层)的请求，转发给领域层获取请求结果，再组装结果返回前端。
- 基础设施层：作为其他层支撑的存在，最通俗的例子就是searchServ，正常的搜索服务都会集成ElasticSearch或者其他搜索功能，searchSearch封装了与基础服务集成的及细节，只暴露了领域需要的接口。
> 将一个复杂的程序划分成多个层。为每一个层开发一个内聚的设计，让每个层仅依赖于它底下的那些层。遵照标准的架构模式实现与其上面的那些层的低耦合。将领域模型相关的代码集中到一个层中，把它从用户界面、应用和基础设施代码中隔离开来。领域对象不必再承担显示自己、保存自己、管理应用任务的职责，而是专注于**表达领域模型**。


应用层、领域层和基础设施层之间的一个典型交互，看上去会是这样：\
用户想要预定一个飞行路线，请求一个位于应用层中的应用服务来做这件事情。应用层从基础设施层中取得相关的领域对象，然后调用它们的相关方法，例如检查与其他已经被预定的飞行线路的安全界限(security margins)。当领域对象执行完所有的检查并将它们的状态修改为“已决定”(decided)之后，应用服务将对象持久化到基础设施中。


![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/design/ddd/ddd-core.png)

## <a name="5">战略建模</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="6">通用语言(Ubiquitous Language)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
其实写软件就像是翻译，把领域上的业务需求翻译成软件的各个功能。业务需求来自领域专家(Domain Expert)，程序员们需要把领域专家的语言翻译成程序。如果程序员们翻译的时候使用的是自己的语言，而领域专家使用自己的行话，导致术语不一致，就会使得沟通不顺畅，难于消化知识。所以团队需要一种通用语言来进行沟通。这样的通用语言尽量以业务语言为主，而非技术语言。一开始的通用语言可能不尽完美，但它就像是代码一样，经常需要重构。例如：“创建一个订单”就比“插入一条订单数据”更容易让领域专家明白谈话的背景。

### <a name="7">限界上下文(Bounded Context)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

界定的上下文：主要的思想是定义模型的范围，画出它的上下文的边界，然后尽最大可能保持模型的一致性。


#### <a name="8">上下文关系</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
限界上下文封装了分离的业务能力，上下文映射则建立了限界上下文之间的关系。上下文映射提供了各种模式（防腐层、开放主机服务、发布语言、共享内核、合作者、客户方/供应方、分离方式、遵奉者、大泥球），**本质是在控制变化在限界上下文之间传递所产生的影响**。

##### <a name="9">共享内核(Shared Kernel)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
共享内核指将限界上下文中的领域模型直接暴露给其他限界上下文使用。注意，这会削弱了限界上下文边界的控制力。
> 防腐层、开放主机服务以及发布语言无不传达一种思想，限界上下文不能直接暴露自己的领域模型或直接访问其他限界上下文的领域模型，一定要有隔离层！

共享内核的目的是减少重复，但是仍保持两个独立的上下文。

在特定的场景下，共享内核不见得不是一种合理的方式。**任何软件设计决策都要考量成本与收益，只有收益高于成本，决策才是合理的**。一般对于一些领域通用的值对象是相对稳定的，这些类型通常属于通用子领域，会被系统中几乎所有的限界上下文复用，那么这些领域模型就适合使用共享内核的方式。共享内核的收益不言而喻，而面临的风险则是共享的领域模型可能产生的变化。

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/design/ddd/share-core.png)

##### <a name="10">客户-供应商(Customer-Supplier)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
当一个限界上下文单向地为另一个限界上下文提供服务时，它们对应的团队就形成了客户方/供应方模式。这是最为常见的团队协作模式，客户方作为下游团队，供应方作为上游团队，二者协作的主要内容包括：
- 下游团队对上游团队提出的服务
- 上游团队提供的服务采用什么样的协议与调用方式
- 下游团队针对上游服务的测试策略
- 上游团队给下游团队承诺的交付日期
- 当上游服务的协议或调用方式发生变更时，如何控制变更


##### <a name="11">顺从者/遵奉者</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
> 当两个开发团队有客户-供应商关系，而且供应商团队没有动力为客户团队提供需要的帮助时，客户团队是无助的。\
> 如果客户不得不使用供应商团队的模型，而且这个模型做得很好，那么就需要顺从这个模型了。客户团队遵从供应商团队的模型，完全顺从它。

当上游的限界上下文处于强势地位，且上游团队响应不积极时，我们可以采用遵奉者模式。即下游严格遵从上游团队的模型，以消除复杂的转换逻辑。

当下游团队选择“遵奉”于上游团队设计的模型时，意味着：

可以直接复用上游上下文的模型（好的）；
减少了两个限界上下文之间模型的转换成本（好的）；
使得下游限界上下文对上游产生了模型上的强依赖（坏的）。

##### <a name="12">隔离通道(Separate Way)/分离方式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
分离方式的团队协作模式是指两个限界上下文之间没有一丁点关系。如果此时双方使用到了相似/相同的领域模型，则可以通过拷贝的方式解决，保证限界上下文之间的物理隔离！

在采用隔离通道模式之前，我们需要确信我们将不会回到一个集成的系统。独立开发的模型是很难做集成的，它们的相通之处很少，不值得这样做。
##### <a name="13">开放主机服务(Open Host Service)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
> 当我们试图集成两个子系统时，通常要在它们之间创建一个转换层。当一个子系统要和其他很多子系统集成时，为每一个子系统定制一个转换器会使整个团队陷入困境。\
> 这个问题的解决方案是，将外部子系统看作服务提供者。如果我们能为这个系统封装一组服务，那么所有的其他子系统将会访问这些服务，我们也就不需要任何转换层。

开放主机服务定义公开服务的协议（亦称为“服务契约”），包括通信方式、传递消息的格式（协议），让限界上下文可以被当做一组服务访问。开放主机服务也可以视为一种承诺，保证开放的服务不会轻易做出变化。

对于进程内的开放主机服务，称为本地服务（对应 DDD 中的应用服务）。

对于进程间的开放主机服务，成为远程服务。根据选择的分布式通信技术的不同，又可以定义出类型不同的远程服务：
- 面向服务行为，比如基于 RPC，称为提供者（Provider）；
- 面向服务资源，比如基于 REST，称为资源（Resource）；
- 面向事件，比如基于消息中间件，称为订阅者（Subscriber）；
- 面向视图模型，比如基于 MVC，称为控制器（Controller）；

##### <a name="14">防崩溃层(Anticorruption Layer)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
引入防腐层的目的是为了隔离耦合。防腐层往往位于下游，通过它隔离上游上下文发生的变化。
> 可以将服务实现为一个 Facade。除了这一点，防崩溃层最有可能还需要一个适配器（Adapter）。适配器可以使你将一个类的接口转换成客户端能够理解的另一个接口。
> 适配器将外部系统的行为包装起来。我们还需要对象和数据转换（object and data conversion），可以使用一个转换器（translator）来完成这个任务。

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/design/ddd/anticorruption.png)


#### <a name="15">大泥球</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
一定要避免制造大泥球！大泥球的特点：
- **越来越多的聚合因为不合理的关联和依赖导致交叉污染**；
- 对大泥球的维护牵一发而动全身；
- 强调“个人英雄主义”，只有个别“超人”能够理清逻辑。


### <a name="16">领域与子领域</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
领域（Domain）：指的是整个要涉及的业务内容
> 例如 ：一个CRM系统，可以是一个领域，一个HR系统，一个电子商务的商城，都可以作为领域概念。 如果一个软件公司，既提供CRM的Saas服务也提供e-HR的Saas服务，但是这两种业务是有很明确的边界的，那么这两个业务就要各自独立为两个不同的领域。不能因为是同一家公司的产品，就混在一个领域范围内。

子领域的作用：
- 划分问题空间，作为业务服务分类的边界；
- 用于分辨问题空间的核心问题和次要问题。

子领域的分类：
- 子域(Sub Domain): 领域中的一部分，可以理解为大业务中的小业务
- 核心子领域(核心领域,Core Domain)：能够体现系统愿景，具有产品差异化和核心竞争力的业务服务；
- 通用子领域(通用域,Generic domain)：包含的内容缺乏领域个性，具有较强的通用性，例如权限管理和邮件管理；
- 支撑子领域(支撑域,Supporting subdomain)：包含的内容多为“定制开发”，其为核心子领域的功能提供了支撑。

子领域的功能分类策略：问题空间应该分为哪些子领域，需要团队对目标系统整体进行探索，并根据**功能分类策略**进行分解。
- **业务职能**：当目标系统运用于企业的生产和管理时，与目标系统业务有关的职能部门往往会影响目标系统的子领域划分，并形成一种简单的映射关系。这是康威定律的一种运用。
- **业务产品**：当目标系统为客户提供诸多具有业务价值的产品时，可以按照产品的内容与方向进行子领域划分。
- **业务环节**对贯穿目标系统的核心业务流程进行阶段划分，然后按照划分出来的每个环节确定子领域。（这也是我们最常用的策略）
- **业务概念**：捕捉目标系统中一目了然的业务概念，将其作为子领域。

## <a name="17">战术建模</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="18">实体(Entity)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
所谓领域，反映到代码里就是模型。模型分为实体和值对象两种。实体是有标识(Identity)的，两个拥有相同属性的实体不是相等的，除非它们的标识相等；而不同实体的标识不能相等。
> 例如：某人下了两个相同的订单，里面都购买了相同的商品。这两个订单就是有标识(订单号)的两个实体，虽然内容相同，但它们是两个不同的实体。常用的标识有自增数字、Guid、自然标识(如邮箱、身份证号)等。

**实体具有生命周期**，它们的内容可能在这期间会发生改变，但是标识是永远不会变化的。实体作为领域模型的主体，**需要拥有自己的方法**，方法名来自于通用语言。通过这些方法来保证自己始终是一致的状态，而非被调用者set来set去。例如：`people.runTo(x, y)`，而非`people.setX(x);people.setY(y);`

### <a name="19">值对象(Value Object)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
实体用来表示领域中的一个东西，而值对象只用于描述或度量一个东西。**值对象没有任何标识**，只要两个值对象的属性相等，那么它们就是相等的。值对象是不可变的，如果要改变值对象的内容，那就重新创建一个值对象。
**值对象没有生命周期**，因为它只是值而已。
> 例如：金额(含数值和货币单位)，颜色(含rgb值)等。因为不需要标识，所以它们其实比实体要简单许多。Java里的String类，就具有一个值对象的行为；C#的Struct其实就是一个值对象，不过一般还是会用Class来表示值对象。

### <a name="20">领域服务(domain service)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
> 当我们分析领域并试图定义构成模型的主要对象时，我们发现领域的有些方面难以被映射成对象。对象通常被认为是拥有属性，一个由对象管理的内部状态、并且暴露出一种行为。\
> 例如，为了从一个账户向另一个账户转钱，这个功能应该放到转出的账户还是在接收的账户中？感觉放在这两个中的哪一个也不对劲。\
> 当这样的行为从领域中被识别出来时，**最佳实践是将它声明成一个服务**。

当一个操作凸现为一个领域中的重要概念时，就需要为它建立一个服务了。以下是服务的 3 个特征：
1. 服务执行的操作涉及一个领域概念，这个领域概念通常不属于一个实体或者值对象。
2. 被执行的操作涉及到领域中的其他的对象。
3. 操作是无状态的。

> 考虑一个实际的 Web 报表应用的例子。报表使用存储在数据库中的数据，它们会基于模版产生。最终的结果是一个在 Web 浏览器中可以显式给用户查看的 HTML 页面。\
用户界面层被合并成 Web 页面，允许用户登录，选择所期望的报表，单击一个按钮就可以发出请求。应用层是非常薄的一个层，它位于用户界面和领域层以及基础设施层的中间位置。它在登录操作时，会跟数据库基础设施进行交互；在需要创建报表时会和领域层进行交互。领域层中包含了领域的核心部分，对象直接关联到报表。有两个这样的对象是报表产生的基础，它们是 Report 和Template。基础设施层将支持数据库访问和文件访问。

### <a name="21">模块(module)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
> 对一个大型的复杂项目而言，模型趋向于越来越大。模型到达了一个作为整体很难讨论的点，理解不同部件之间的关系和交互变得很困难。基于此原因，很有必要将模型组织进模块。模块被用来作为组织相关概念和任务以便降低复杂性的一种方法。

在设计中使用模块是一种增进内聚和消除耦合的方法。模块应该由在功能上或者逻辑上属于一体的元素构成，以保证内聚。模块应该具有定义好的接口，这些接口可以被其他的模块访问。

> 虽然内聚开始于类和方法级别，它也可以应用于模块级别。推荐的做法是将高关联度的类分组到一个模块，以提供尽可能大的内聚性。有很多类型的内聚性。最常用到的两个是通信性内聚(communicational cohesion)和功能性内聚(functional cohesion)。\
> **通信性内聚**: 在模块中的部件操作相同的数据时，可以得到通信性内聚。把它们分到一组很有意义，因为它们之间存在很强的关联性。\
> **功能性内聚**: 在模块中的部件协同工作以完成定义好的任务时，可以得到功能性内聚。功能性内聚被认为是最佳的内聚类型。

模块应该由在功能上或者逻辑上属于一体的元素构成，以确保内聚性。模块应该具有定义好的接口，这些接口可以被其他的模块访问。最好用访问一个接口的方式，而不是调用模块中的三个对象，因为这样做可以降低耦合度。如果模块间仅有极少的连接，通过这些连接来执行定义好的功能，这样做会让人更容易理解系统是如何工作的。


### <a name="22">领域事件(domain event)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
领域事件是一个定义了领域专家所关心的事件的对象。当关心的状态由于模型行为而发生改变时，系统将发布领域事件。如果通用语言里出现了：“当……的时候，需要……”通常就意味着一个领域事件。
> 例如：当订单完成支付时，商品需要出库。这里的订单完成支付就预示着一个OrderPaidEvent，里面持有着这个订单的标识。领域事件代表的是已经发生的事，所以命名上通常都使用过去时(如Paid)。

对领域事件的处理就像是一个观察者模式，由领域事件的订阅方来决定。订阅方既可以是本地的限界上下文，也可以是外部的限界上下文。

## <a name="23">生命周期</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="24">聚合(aggregate)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
> 一个模型会包含众多的领域对象。无论在设计时做了多少考虑，我们都会看到很多对象会跟其他的对象发生关联，形成了一个复杂的关系网，如1:n,n:n。

聚合是**针对数据变化可以考虑成一个单元的一组相关的对象**。聚合使用边界将内部和外部的对象划分开来。每个聚合有一个根。
> 这个根是一个实体，并且它是外部可以访问的唯一的对象。根可以保持对任意聚合对象的引用，并且其他的对象可以持有任意其他的对象，但一个外部对象只能持有根对象的引用。如果边界内有其他的实体，那些实体的标识符是本地化的，只在聚合内有意义。

聚合就是一组应该呆在一起的对象，聚合根(Aggregate Root)就是聚合在一起的基础，并提供对这个聚合的操作。聚合除了聚合根以外，还有自己的边界(boundary)，即聚合里有什么。
> - 例如：一个订单可以有多个订单明细，订单明细不可能脱离订单而存在，而订单也不可能没有订单明细。这种情况下，订单和订单明细就是一个聚合，而订单就是这个聚合的聚合根，订单和订单明细就处于这个聚合的边界之内。如果要变更订单明细，我们需要通过操作聚合根订单来实现，如`order.changeItemCount()`，而非订单明细自身。
> - 另外一个例子：一名客户可以有多个订单，订单不可能脱离客户而存在，而客户却可以没有订单。这种情况下，客户和订单就是不同的两个聚合，一个聚合以客户为聚合根，另一个聚合以订单为聚合根，引用客户的标识。客户里并不引用订单的标识，这样将关联减至最少有助于简化对象的关系网。但是带来的一个麻烦就是如果要查找某位客户的所有订单，就不得不从所有的订单里查，而不能从客户这个聚合里直接获得。
> - 最后再举一个多对多的例子：一个班级可以有多名学生，学生可以脱离这个班级而存在，而班级不能没有学生，学生也不能不在班级里。这种情况下，班级和学生也是不同的两个聚合，一个聚合以班级为聚合根，引用学生的标识；另一个聚合以学生为聚合根，引用班级的标识，将多对多转换成两个一对多。

聚合是持久化的一个单位，我们需要保证以聚合为单位的数据一致性。如果聚合太大，那就会导致并发修改困难，多人并发修改同一个聚合里的不同项目，结果就是只有第一个提交的人成功修改，其它人不得不重新刷新聚合才能再次修改。大聚合还会导致性能问题，因为操作实体时会将整个大聚合同时加载进内存。珍爱生命，拒绝大聚合。

聚合根必须是实体而非值对象，因为它需要整体持久化，所以一定会有标识。而聚合根里的各个元素，既可能是实体，也可能是值对象。
> 例如：一个订单(聚合根)一般会有订单明细(实体)和送货地址(值对象)。这些元素里可以有对聚合根的引用，但是不能相互引用。任何对其它元素的操作都必须通过聚合根来进行。聚合根里的标识是全局的，聚合根里的实体标识是聚合里唯一的本地标识，因为对它的访问都是通过聚合根来操作的。聚合根拥有自己独立的生命周期，其实体的生命周期从属于其所属的聚合，值对象因为只是值而已，并没有生命周期。

一个简单的聚合的案例如下图所示。客户是聚合的根，并且其他所有的对象都是内部的。如果需要地址，一个它的拷贝将被传递到外部对象。
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/design/ddd/aggregation.png)


### <a name="25">工厂(factory)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
> 创建一个对象可以是它自身的主要操作，但是复杂的组装操作不应该成为被创建对象的职责。组合这样的职责会产生笨拙的设计，也很难让人理解。复杂对象的创建涉及到内部的数据结构、规则等，这破坏了对于领域对象和聚合的封装。如果客户属于应用层，领域层的一部分将被移到了外边，从而打乱整个设计。

工厂用来封装对象创建所必需的知识，它们对创建聚合特别有用。当聚合的根建立时，所有聚合包含的对象将随之建立，所有的不变量得到了强化。

**工厂是生命周期的开始阶段**，它可以用来创建复杂的对象或是一整个聚合。复杂对象的创建是领域层的职责，但它并不属于被创建的对象自身的职责。实体和值对象的工厂不太一样，因为值对象是不可变的，所以需要工厂一次性创建一个完整的值对象出来。而实体工厂则可以选择创建之后再补充一些细节。

### <a name="26">资源库(repository)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
**资源库是生命周期的结束**，它封装了基础设施以提供查询和持久化聚合的操作。**这样能够让我们始终聚焦于模型**，而把对象的存储和访问都委托给资源库来完成。以订单和订单明细的聚合为例，因为一定是通过订单这个聚合根来获取订单明细，所以可以有订单的资源库，但是不能有订单明细的资源库。也就是说，只有聚合才拥有资源库。需要注意的是，资源库并不是数据库的封装，而是领域层与基础设施之间的桥梁。DDD关心的是领域内的模型，而并非是数据库的操作。理想的资源库对客户(而非开发者)隐藏了内部的工作细节，委托基础设施层来干那些脏活，到关系型数据库、NOSQL、甚至内存里读取和存储数据。

使用一个资源库，它的目的是封装所有获取对象引用所需的逻辑。领域对象不需处理基础设施，以得到领域中对其他对象的所需的引用。只需从资源库中获取它们，于是模型重获它应有的清晰和焦点。\
提供基于某种条件选择对象的方法，返回属性值符合条件的完全实例化的对象或者一组对象，继而封装实际的存储和查询技术。仅对真正需要直接访问的聚合根提供资源库。让客户程序保持对模型的关注，把所有的对象存储和访问细节委托给资源库。\
数据驱动强调的是数据结构，也就是通过分析需求，来确定整体数据结构，根据表之间的关系划分服务。

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/design/ddd/repository.png)

## <a name="27">四种模型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 失血模型：模型仅仅包含数据的定义和getter/setter方法，业务逻辑和应用逻辑都放到服务层中。这种类在Java中叫POJO，在.NET中叫POCO。
- 贫血模型：贫血模型中包含了一些业务逻辑，但不包含依赖持久层的业务逻辑。这部分依赖于持久层的业务逻辑将会放到服务层中。可以看出，贫血模型中的领域对象是不依赖于持久层的。
- 充血模型：充血模型中包含了所有的业务逻辑，包括依赖于持久层的业务逻辑。所以，使用充血模型的领域层是依赖于持久层，简单表示就是 UI层->服务层->领域层<->持久层。
- 胀血模型：胀血模型就是把和业务逻辑不相关的其他应用逻辑（如授权、事务等）都放到领域模型中。我感觉胀血模型反而是另外一种的失血模型，因为服务层消失了，领域层干了服务层的事，到头来还是什么都没变。

## <a name="28">代码实现</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

**用户接口层**\
用户接口层的核心职能：协议转换和适配、鉴权、参数校验和异常处理。
```
interfaces
├── controller                             //面向视图模型&资源
│   ├── ResultController.java
│   ├── assembler                         // 装配器，将VO转换为DTO
│   │   └── ResultAssembler.java
│   └── vo                                // VO(View Object)对象
│       ├── EnterResultRequest.java
│       └── ResponseVO.java
├── facade(provider)                       // 面向服务行为
├── subscriber                             // 面向事件
└── task                                   // 面向策略(定时任务)
    └── TotalResultTask.java
```

**应用层(application)**\
应用层的核心职能：编排领域服务、事务管理、发布应用事件

```
├── application
│   ├── assembler                              // 装配器，将DTO转换为DO
│   │   ├── ResultAssembler.java
│   │   └── TotalResultAssembler.java
│   ├── pojo                                   // DTO(Data Transfer Object)对象
│   │   ├── reqeust                           // 请求相关的DTO
│   │   ├── event                             // 应用事件相关的DTO对象, subscriber负责接收
│   │   └── qry                               // 查询相关的DTO对象
│   └── service                                // 应用服务
│       ├── ResultApplicationService.java
│       ├── impl                               // service实现           
│       ├── event                              // 应用事件，用于发布
│       └── adapter                            // 防腐层适配器接口
```

**领域层(domain)**：代码组织以聚合为基本单元。

**领域防腐层anticorruption**: 是当前领域需要获知其他领域或者外部信息时，对其他领域二方包的封装。
> 防腐层从代码层面来看，可以避免调用外部客户端时，在领域内部进行复杂的参数拼装和结果的转换。

```
├── domain                                 // 领域层聚合
│   ├── anticorruption                    //  领域防腐层
│   │   └── service                       
│   ├── factory                           //  工厂类 解决了复杂聚合的初始化问题
│   ├── entity                             // 成绩聚合内的实体
│   │   ├── vo                            // 领域返回对应的VO
│   │   └── Result.java                    // 领域对象
│   ├── service                           // 领域服务
│   │   ├── ResultDomainService.java
│   │   ├── event                         // 领域事件
│   │   └── repository                    // 资源库
│   │       └── ResultRepository.java
│   └── valueobject                        // 成绩聚合的值对象
│       ├── GPA.java
│       ├── SchoolYear.java
│       └── Semester.java
```

**基础设施实现层**: 该层主要提供领域层接口（资源库、防腐层接口）和应用层接口（防腐层接口）的实现。\
代码组织基本以聚合为基本单元。对于应用层的防腐层接口，则直接以 application 作为包名组织。
```
├── application                                  // 应用层相关实现
│   └── adapter                                 // 防腐层适配器接口实现
│       ├── facade                              // 外观接口
│       └── translator                          // 转换器，DO -> DTO
├── result                                      // 成绩聚合相关实现
│   ├── adapter
│   │   ├── facade
│   │   └── translator
│   └── repository                              // 成绩聚合资源库接口实现
│       └── ResultRepositoryImpl.java
└── totalresult                                  // 总成绩聚合相关实现
    ├── adapter
    │   ├── CourseAdapterImpl.java
    │   ├── facade
    │   └── translator
    └── repository
        └── TotalResultRepositoryImpl.java

```

## <a name="29">参考资料</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [领域驱动设计(DDD)](https://www.wolai.com/sSuS9PurVF2jVuj2RU9G3D)
- [万字长文助你上手软件领域驱动设计 DDD](https://mp.weixin.qq.com/s/BIYp9DNd_9sw5O2daiHmlA)
- [领域驱动编程，代码怎么写？](https://mp.weixin.qq.com/s/W9xT9hNQjjIfjGxbePqDJw)
- [Alibaba-cola](https://github.com/alibaba/COLA)