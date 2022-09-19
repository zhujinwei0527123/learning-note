<a name="index">**Index**</a>

<a href="#0">head first 设计模式</a>  
&emsp;<a href="#1">1. 设计原则</a>  
&emsp;<a href="#2">2. 策略模式</a>  
&emsp;<a href="#3">3. 观察者模式</a>  
&emsp;<a href="#4">4. 装饰者模式</a>  
&emsp;<a href="#5">5. 工厂模式</a>  
&emsp;<a href="#6">6. 抽象工厂模式</a>  
&emsp;<a href="#7">7. 单件模式（单例）</a>  
&emsp;<a href="#8">8. 命令模式</a>  
&emsp;<a href="#9">9. 适配器模式</a>  
&emsp;<a href="#10">10. 外观模式</a>  
&emsp;<a href="#11">11. 模板方法模式</a>  
&emsp;<a href="#12">12. 迭代器模式</a>  
&emsp;<a href="#13">13. 组合模式</a>  
&emsp;<a href="#14">14. 状态模式</a>  
&emsp;<a href="#15">15. 代理模式</a>  
&emsp;<a href="#16">16. 建造者模式</a>  
&emsp;<a href="#17">17. 责任链模式</a>  
&emsp;<a href="#18">18. 原型模式</a>  
&emsp;<a href="#19">19. 桥接模式</a>  
&emsp;<a href="#20">20. 蝇量模式（享元模式）</a>  
&emsp;<a href="#21">21. 解释器模式</a>  
&emsp;<a href="#22">22. 中介者模式</a>  
&emsp;<a href="#23">23. 备忘录模式</a>  
&emsp;<a href="#24">24. 访问者模式</a>  
# <a name="0">head first 设计模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
## <a name="1">设计原则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 多用组合少用继承。
- 找出应用中可能需要变化之处，把他们独立出来，不要和那些不需要变化的代码混合在一起。
- 针对接口编程，而不是针对实现编程。
- 为了交互对象之间的松耦合设计而努力。
- 类应该对扩展开放，对修改关闭。
- 要依赖抽象，不要依赖具体类。
- 最少知识原则：只和你的密友谈话。（相当于类间交互适当封装，减少耦合，只让必要部分交互）
- 好莱坞原则：别调用我们，我们会调用你。（高层组件控制何时调用底层组件）
- 一个类应该只有一个引起改变的原因。（使一个类具有单一的责任，避免类的改变引起大范围的修改，实现类的高内聚）


- 单一职责( 一个类和方法只做一件事 )
- 里氏替换( 多态，子类可扩展父类 )
- 依赖倒置( 细节依赖抽象，下层依赖上层 )
- 接口隔离( 建立单一接口 )
- 迪米特原则( 最少知道，降低耦合 )
- 开闭原则( 抽象架构，扩展实现 )

## <a name="2">策略模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：定义了算法族，分别封装起来，让他们之间可以互相替换，此模式让算法的变化独立于使用算法的客户。
- 例子： 鸭子有不同的品种，但是它们都会鸣叫，有不同的鸣叫方式。
![avatar](http://dl2.iteye.com/upload/attachment/0093/6873/97871e8f-0d17-33f9-968d-dd05769b67fa.jpg)


## <a name="3">观察者模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：定义了对象之间的一对多依赖，这样一来，当一个对象的状态改变时，他的所有依赖着都会受到通知并自动更新。
- 其中java中有内置观察者模式的接口，java.util.Observer和java.util.Observable。缺点就是无法在通知时自定义一些行为。
- 例子：气象站与布告板，气象站的温度改变，布告板就需要知道改变的温度。
![avatar](https://www.runoob.com/wp-content/uploads/2014/08/observer_pattern_uml_diagram.jpg)

## <a name="4">装饰者模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：定义动态地将责任附加到对象上。若要拓展功能，装饰着提供了比集成更有弹性的替代方案。
- 例子：饮料分为多种，添加不同的调料，支持调料多次添加。设置调料味装饰者。
- java中的例子java IO类，FilterInputStream为一个装饰者。
![avatar](https://img2018.cnblogs.com/blog/1216886/201909/1216886-20190922012926406-314109345.png)

![avatar](https://img2018.cnblogs.com/blog/1216886/201909/1216886-20190922011430803-958922094.png)

## <a name="5">工厂模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：定义一个创建对象的接口，但由子类觉得要实例化的类是哪一个。工厂方法让类把实例化推迟到子类。
![avatar](https://www.runoob.com/wp-content/uploads/2014/08/AB6B814A-0B09-4863-93D6-1E22D6B07FF8.jpg)

## <a name="6">抽象工厂模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：提供一个接口，用于创建相关或依赖对象的家族，而不需要明确指定具体类。
![avatar](https://www.runoob.com/wp-content/uploads/2014/08/3E13CDD1-2CD2-4C66-BD33-DECBF172AE03.jpg)

## <a name="7">单件模式（单例）</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：确保一个类只有一个实例，并提供一个全局的访问点。

## <a name="8">命令模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：定义将"请求"封装成对象，以便使用不同的请求、队列或者日志来参数化其他对象。命令模式也支持可撤销的操作。
- 例子：

1. 餐厅点菜场景: 顾客点菜创建Command，交给服务员Waiter，服务员invoke命令。命令中包含完成改命令的厨师对象，厨师接收到命令开始执行具体的动作。

2. 遥控器场景：灯泡有开跟关两个命令，需要交给遥控器来触发invoke，遥控器invoke命令后，命令对象触发包含的灯泡接收对象执行相关指令。

- 适用场景：

1.队列：如一个工作队列，你在一端添加命令，然后另一端则是线程。线程用于从队列中取出一个命令并调用它的执行方法，调用完成丢弃。

2.日志请求：将执行动作记录在日志中，在系统死机之后，重新调用这些动作。
![avatar](https://images0.cnblogs.com/blog/300932/201310/29231031-a9012ec0f1404c9f8b1432f7b85594b9.png)

## <a name="9">适配器模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：定义将一个类的接口，转换成客户期待的另一个接口。适配器让原本接口不兼容的类可以合作无间。
- 只适合做单方向的适配，如三极插头转换成二极。
- java中的适配器应用：1.迭代器的使用，为了兼容旧的使用枚举器的接口，使用适配器进行转换。
![avatar](https://img2018.cnblogs.com/blog/1272523/201901/1272523-20190129143223087-243785080.png)

## <a name="10">外观模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：提供了一个统一的接口，用来访问子系统中的一群接口。外观定义了一个高层接口，让子系统更容易使用。
- 简单的理解就是对多个接口进行封装，对外统一暴露接口，隐藏内部接口。
![avatar](https://images2018.cnblogs.com/blog/1018770/201805/1018770-20180516222046525-864875223.png)

## <a name="11">模板方法模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：在一个方法中定义一个算法的骨架，而将一些步骤延迟到子类中。模板模式使得子类可以在不改变算法结构的情况下，重新定义算法的某些步骤。
- 例子：咖啡店煮咖啡都是有相同的模板跟步骤的，比如都要烧水，选咖啡豆，煮咖啡，将咖啡倒入杯子中。那么可以将模板的步骤定义到抽象的父类中，并提供公共的方法如烧开水、将咖啡倒入杯中。提供公共的钩子，让子类实现，再与实际的操作模板挂钩。
- 容易与策略混淆，区别为模板方法有定义一个算法的大纲，子类再具体实现步骤。而策略是根据不同策略提供子类不同的行为。
![avatar](https://img2018.cnblogs.com/i-beta/898240/201911/898240-20191120150741079-1083249750.png)

## <a name="12">迭代器模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：提供一个方法顺序访问一个聚合对象中的各个元素，而又不暴露其内部的表示。
- 例子：一个对象中的元素为数组表示，而另一个对象中为list。让两个对象实现接口，提供统一的构造器。使用时便可以屏蔽实际的数据类型，使用统一的数据模式进行访问。
![avatar](https://www.runoob.com/wp-content/uploads/2014/08/iterator_pattern_uml_diagram.jpg)

## <a name="13">组合模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：允许你将对象组合成属性结构来表现“整体、部分”的层次结构，组合能让客户以一致的方式处理个别对象及对象组合。
- 例子： 一个菜单组件包含菜单项还有子菜单，但是菜单展示的时候，希望可以统一处理，服务员读菜单的时候直接把菜单组件当成一个整体，能统一的读出所有菜单项。
![avatar](https://pics5.baidu.com/feed/a9d3fd1f4134970aaa3b00725b71f9cda6865d67.jpeg?token=2a84b74d0bcf059636a4e5bcb017a283&s=7AA83462119F65CC5CF511CA0000A0B1)

## <a name="14">状态模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：允许对象在内部状态改变时改变它的行为，对象看起来好像修改了它的类。
- 例子： 糖果机经历投入硬币、转扳手、滚出糖果的三个状态，每个状态都需要执行不同的行为。
- 与策略模式容易混淆，策略主要是针对行为的改变，对于对象是一部分行为的改变。而状态模式针对的则是对象整体行为的改变，两个模式的意图不一致。
![avatar](https://www.runoob.com/wp-content/uploads/2014/08/state_pattern_uml_diagram.png)

## <a name="15">代理模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：为一个对象提供一个替身或者占位符以控制这个对象的访问。代理的对象可以是远程的对象、创建开销大的对象或需要安全控制的对象。
- 与装饰者模式容易混淆，主要是行为跟意图的不同，代理主要是为了在对象调用时提供一个额外的保护或者加上其他的动作。而装饰者则是对象调用前，加上额外的行为。
- 代理模式有三种，1.静态代理。2.JDK动态代理（基于接口）3.cglib动态代理（可基于类）
![avatar](https://www.runoob.com/wp-content/uploads/2014/08/proxy_pattern_uml_diagram.jpg)

## <a name="16">建造者模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：使用生成器模式封装一个产品的构造过程，并允许按步骤构造。
- 优点：将一个复杂的对象的创建过程封装起来，允许对象通过多个步骤来创建，并可以改变过程（这里跟一个步骤的工程不同），同时向客户隐藏产品的内部表现。产品的实现可以被替换，因为客户只看到一个抽象的接口。
- 经常被用来创建组合结构。
- 场景： 当一个类的构造函数参数个数超过4个，而且这些参数有些是可选的参数，考虑使用构造者模式。
- 例子：一辆汽车需要很多个部件进行组装才能建好，builder提供了建造汽车所需的方法，而不同产品的汽车可以用不同的builder实现，director用于指导汽车的组装，比如第一步创建什么第二步组装什么，客户只需要传入正确的builder，director便可以根据步骤创建一辆汽车。
- 指导者意义可以屏蔽复杂的创建过程，提供创建复杂对象的步骤和模板，最终返回所创建对象。
![avatar](https://pic3.zhimg.com/80/v2-5a7bd484bf046798b86826e95ab894fa_1440w.jpg)

## <a name="17">责任链模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：当你想要让一个以上的对象有机会能够处理某个请求的时候，就使用责任链模式。
- 优点：1.将请求的发送者和接受者解耦。2.简化你的对象，因为他不需要知道链的结构。3.通过改变链内的成员或调用他们的次序，允许你动态的增加或删除责任
- 缺点：1.不能保证请求一定会被执行，如果没有被执行的话，请求可能会落到链的尾端之外。2.不容易观察运行时的特征，有碍于排错。
![avatar](http://c.biancheng.net/uploads/allimg/181116/3-1Q116135Z11C.gif)

## <a name="18">原型模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：当创建给定类的示例过程很昂贵或者复杂时，就是用原型模式。
- 优点：1.向客户隐藏制造新实例的复杂性。2.某些环境下，复制对象比创建新对象更有效。
- 缺点: 对象的复制有时候相当复杂。
![avatar](https://www.runoob.com/wp-content/uploads/2014/08/prototype_pattern_uml_diagram.jpg)

## <a name="19">桥接模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：使用桥接模式不仅改变你的模式，还改变你的抽象
- 优点：1.将实现基于解耦，让它和界面之间不再永远绑定。2.抽象和实现可以独立拓展，不会影响对方。3.对于“具体的抽象类”所做的改变，不会影响到客户。3.当需要用不同的方式改变接口和实现，桥接很好用。
- 缺点：增加了复杂度。
![avatar](https://img2018.cnblogs.com/blog/1018770/201906/1018770-20190618215338547-1219481456.png)


## <a name="20">蝇量模式（享元模式）</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：如想让某个类的一个实例能用来提供许多"虚拟实例",就使用一辆模式。
- 优点：1.减少运行时对象实例的个数，节省内存。2.将许多"虚拟"对象的状态集中管理。
- 缺点：1.一旦使用蝇量模式，那么单个的逻辑实例将无法拥有独立而不同的行为。
- 使用频率不高。
![avatar](https://www.runoob.com/wp-content/uploads/2014/08/flyweight_pattern_uml_diagram-1.jpg)

## <a name="21">解释器模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：使用解释器模式为语言创建解释器
- 优点: 1.将每一个语法规则表示层一个雷，方便语言实现。2.因为语法使用类表示，因此拓展性好。3.在类结构加入新的方法，可以在解释器增加新的行为。
- 缺点：当语法规则数目太大，使用该模式会变成非常繁杂。
- 使用场景： 1、可以将一个需要解释执行的语言中的句子表示为一个抽象语法树。 2、一些重复出现的问题可以用一种简单的语言来进行表达。 3、一个简单语法需要解释的场景。
- 使用频率不高
![avatar](https://img-blog.csdnimg.cn/20200425211810545.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1RPTkdaT05HRQ==,size_16,color_FFFFFF,t_70)

## <a name="22">中介者模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：使用中介者模式来集中相关对象之间复杂的沟通和控制方式。
- 优点：1.通过将对象彼此解耦，可以增加对象的复用性。2.通过将控制逻辑集中，可以简化系统维护。3.可以让对象之间所传递的消息变得简单而且大幅减少。
- 缺点：如果设计不当，中介者对象会变得过于复杂。
- 使用频率不高
![avatar](https://images2.freesion.com/364/88/887b0f12446b74e4188148216fbe2944.JPEG)

## <a name="23">备忘录模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：当你需要让对象返回之前的状态时，就是用备忘录模式
- 优点：1.将被存储的状态放在外面，不要和关键对象混在一起，这可以帮助维护内聚。2.保持关键对象的数据封装。3.提供了容易实现的恢复能力。
- 缺点：1.存储和恢复状态的过程可能相当耗时。2.在java系统中，可以考虑用序列化机制存储系统的状态。
![avatar](https://www.runoob.com/wp-content/uploads/2014/08/memento_pattern_uml_diagram.jpg)


## <a name="24">访问者模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：当你想要为一个对象的组合增加新的能力，且封装并不重要时，就是用访问者模式。
- 优点：1.允许你对组合结构加入新的操作，无需改变结构本身。2.想要加入新的操作非常容易。3.访问者所进行的操作，其代码是集中在一起的。
- 缺点：1.使用访问者模式会打破组合类的封装。2.因为游走的功能牵涉其中，所以对组合结构的改变就更加困难。
![avatar](https://www.runoob.com/wp-content/uploads/2014/08/visitor_pattern_uml_diagram.jpg)
