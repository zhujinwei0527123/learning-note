<a name="index">**Index**</a>

&emsp;&emsp;<a href="#0">0.1. 策略模式</a>  
### <a name="0">策略模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- Context是上下文，用一个ConcreteStrategy来配置，维护一个对Strategy对象的引用；Strategy是策略类，用于定义所有支持算法的公共接口；ConcreteStrategy是具体策略类，封装了具体的算法或行为，继承于Strategy。


1. 何时使用
 一个系统有许多类，而区分它们的只是他们直接的行为时
 
2. 方法
 将这些算法封装成一个一个的类，任意的替换
 
3. 优点
 算法可以自由切换
 避免使用多重条件判断（如果不用策略模式我们可能会使用多重条件语句，不利于维护）
 扩展性良好，增加一个策略只需实现接口即可

4. 缺点
 策略类数量会增多，每个策略都是一个类，复用的可能性很小
 所有的策略类都需要对外暴露
 
- 实际应用中的例子，spring中的Listener
- TODO待补充