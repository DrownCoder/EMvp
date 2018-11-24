
### 前言
基于AOP的适用于RecyclerView多楼层开发的开源框架，使用方便，拓展性强，代码侵入性低，楼层耦合度低。  
![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)
### 项目介绍
RecyclerView作为Google替代ListView的一个组件，其强大的拓展性和性能，现在已经成为无数App核心页面的主体框架。RecyclerView的开发模式一般来说都是多Type类型的ViewHolder——后面就称为楼层(感觉很形象)。但是使用多了，许多问题就暴露出来了，经常考虑有这么几个问题：  

* 如何更便捷的使用Adapter和ViewHolder的开发模式？  
* 如何和他人的楼层做到楼层的复用？
* 如何做到全局楼层的打通？
* 楼层本身如何做到逻辑闭合,做到MVP的组件化模式？  

### 功能特性
* 基于编译期注解，不影响性能
* 使用简单，楼层耦合度低
* 代码侵入性低
* 支持全局楼层打通，多人楼层打通
* 楼层支持点对点MVP模式
* 事件中心模式，楼层只是事件的传递者。
* 生命周期监听，支持逻辑的生命周期感知。
* 丰富的API，支持多方面拓展。
* 提供组件化工程使用方案
* 不用每次再写Adapter了～

### USEAGE

```
defaultConfig {
        defaultConfig {
            //添加如下配置就OK了
            javaCompileOptions { annotationProcessorOptions { includeCompileClasspath = true } }
        }
    }

dependencies {
	api 'com.xuan.EMvp:slots:1.0.3'
        annotationProcessor 'com.xuan.EMvp:compiler:1.0.3'
}
```

### 使用方式
这里就介绍一下基于自己对于RecyclerView的理解，开发的一款基于AOP的，适用于多楼层模式的RecyclerView的开发框架。
#### 一.单样式列表
##### 1.定义楼层（支持三种模式）
* 继承Component类型
```
@ComponentType(
        value = ComponentId.SIMPLE,
        layout = R.layout.single_text
)
public class SimpleVH extends Component {
    public SimpleVH(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    public void onBind(int pos, Object item) {
    }
    
    @Override
    public void onUnBind() {
    }
}

```
* 继承原生ViewHolder类型
```
@ComponentType(
        value = PersonId.VIEWHOLDER,
        layout = R.layout.person_item_layout
)
public class PersonVH extends RecyclerView.ViewHolder implements IComponentBind<PersonModel> {
    private TextView tvName;

    public PersonVH(View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tv_name);
    }

    @Override
    public void onBind(int pos, PersonModel item) {
        tvName.setText(item.name);
    }

    @Override
    public void onUnBind() {
    }
}
```
* 自定义View类型
```
@ComponentType(PersonId.CUSTOM)
public class CustomView extends LinearLayout implements IComponentBind<PersonModel> {
    public CustomView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.cutom_view_vh, this, true);
        setBackgroundColor(Color.BLACK);
    }

    @Override
    public void onBind(int pos, PersonModel item) {
    }

    @Override
    public void onUnBind() {

    }
}
```
很清晰，不用再每次在复杂的`if else`中寻找自己楼层对应的布局文件。(熟悉的人应该都懂)  
**注意：**
* 1.value:楼层的唯一标示，int型  
* 2.layout:楼层的布局文件
* 3.继承ViewHolder和自定义View类型需要实现`IComponentBind`接口即可
* 4.对于R文件不是常量在组件化时遇到的问题的[解决方案](https://github.com/DrownCoder/EMvp/wiki/%E7%BB%84%E4%BB%B6%E5%8C%96%E9%A1%B9%E7%9B%AE%E4%B8%ADR%E6%96%87%E4%BB%B6%E6%97%A0%E6%B3%95%E4%BD%BF%E7%94%A8)

##### 2.定义Model
```
@BindType(ComponentId.SIMPLE)
public class SimpleModel {
    
}
```
**BindType**:当是单样式时，model直接注解对应的楼层的唯一标示，int型

##### 3.绑定RecyclerView
```
@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_layout);
        mRcy = findViewById(R.id.rcy);
        mRcy.setLayoutManager(new LinearLayoutManager(this));
        new ToolKitBuilder<>(this, mData).build().bind(mRcy);
    }
```
使用对应的API，利用build()方法构建SlotsContext实体最后利用`bind()`方法绑定ReyclerView.
#### 二.多楼层模式
1.定义ViewHolder(同前一步)  
2.多样式判断逻辑(两种方式)
##### 2.1 Model实现HandlerType接口处理逻辑
```
public class CommonModel implements HandlerType {
    public int pos;
    public String tips;
    public String eventId;

    @Override
    public int handlerType() {
        if (pos > 8) {
            pos = pos % 8;
        }
        switch (pos) {
            case 1:
                return ComponentId.VRCY;
            case 3:
                return ComponentId.DIVIDER;
            case 4:
                return ComponentId.WEBVIEW;
            case 5:
                return ComponentId.TEXT_IMG;
            case 6:
                return ComponentId.IMAGE_TWO_VH;
            case 7:
                return ComponentId.IMAGE_VH;
            case 8:
                return ComponentId.USER_INFO_LAYOUT;
        }
        return ComponentId.VRCY;
    }
}
```

返回定义的ItemViewType，这里封装在Model内部，是由于平时我们总是将java中的Model当作一个JavaBean，而导致我们赋予Model的职责过于轻，所以就会出现更多的其实和Model紧密相关的逻辑放到了Activity，Presenter或者别的地方，但是其实当我们将Model当作数据层来看待，其实可以将许多与Model紧密相关的逻辑放到Model中，这样我们其实单模块的逻辑内聚度就很高，便于我们理解。
(这里思路其实来源于IOS开发中的**胖Model**的概念，大家可以Goolge一下)
>**好处**：当我们需要确定楼层之间和Model的关系，直接按住ctrl，进入Model类，一下就可以找到相关逻辑。

##### 2.2 实现IModerBinder接口自定义处理类
一款好的框架肯定是对修改关闭，对拓展开放的，当我们认为放到Model中处理过于粗暴，或者Model中已经有过多的逻辑了，我们也可以将逻辑抽出来，实现IModerBinder接口。
```
public interface IModerBinder<T> {
    int getItemType(int pos, T t);
}
```
对应的利用`ToolKitBuilder.setModerBinder(IModerBinder<T> moderBinder)`构建即可。例如：
```
.setModerBinder(new ModelBinder<PersonModel>() {
                    @Override
                    protected int bindItemType(int pos, PersonModel obj) {
                    	//处理Type的相关逻辑
                       return type;
                    }
                })
```
### 个人模式
当涉及到大型项目时，多人协作往往是一个问题，当所有人都维护一套ComponentId，合并代码时解决冲突往往是很大的问题，并且不可能所有的楼层都是全局打通的类型，所以这里提供一种个人开发模式。
* 1.使用attach注解，绑定对应class
```
@ComponentType(
        value = PersonId.VIEWHOLDER,
        layout = R.layout.person_item_layout,
        //class类型，对应到映射表的key
        attach = PersonModel.class
)
public class PersonVH extends RecyclerView.ViewHolder implements IComponentBind<PersonModel> {
    private TextView tvName;

    public PersonVH(View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tv_name);
    }

    @Override
    public void onBind(int pos, PersonModel item) {
        //tvName.findViewById(R.id.tv_name);
        tvName.setText(item.name);
    }

    @Override
    public void onUnBind() {

    }
}
```
* 2.调用SlotContext.attachRule绑定对应的Class
```
SlotContext slotContext =
                new ToolKitBuilder<PersonModel>(this)
                        //注册绑定的类型，对应获取映射表
                        .attachRule(PersonModel.class).build();
```
### 进阶使用
项目利用Build模式构建SlotContext实体，SlotContext原理基于Android中的Context思想，作为一个全局代理的上下文对象，通过SlotContext，我们可以获取对应的类，进而实现对应类的获取和通信。
#### ToolKitBuilder的构造函数
```
public ToolKitBuilder(Context context, List<T> data)
public ToolKitBuilder(Context context)
```
#### ToolKitBuilder的API
| 方法名 | 描述 | 备注 |
| ------ | ------ | ------ |
| setData(List<T> data) | 设置绑定的数据集 | 空对象，对应的构造的size=0 |
| setModerBinder(IModerBinder<T> moderBinder) | 处理多样式时Model对应的Type | 处理优先级优先于HandlerType和注解BindType |
| setEventCenter(View.OnClickListener onClickListener) | 设置事件中心 | ViewHolder的事件绑定后都会回调到这个事件中心 |
| setComponentFactory(CustomFactory componentFactory) | 设置自定义创建ViewHolder的工厂 | 可以自定义创建三种类型 |
| setMixStrategy(IMixStrategy<T> mixStrategy) | 设置混合模式处理策略 | 多人楼层打通 |
| attachRule(Class<?> clazz) | 注册楼层映射表 | 个人模式和混合模式 |
| SlotContext<T> build() | 构建出SlotContext对象 |  |
#### SlotContext的构造函数
```
public SlotContext(Context context, List<T> data)
public SlotContext(ToolKitBuilder<T> builder)
```
#### SlotContext的API
| 方法名 | 描述 | 备注 |
| ------ | ------ | ------ |
| Context getContext() | 获取Context对象 |  |
| setData(List<T> data) | 绑定数据集 | 这里不会刷新数据，仅仅是设置 |
| notifyDataSetChanged() | 刷新数据 | 只提供了全局刷新的方式，局部刷新可以通过获取Adapter使用 |
| attachRule(Class<?> clazz) | 注册楼层映射表 | 个人模式和混合模式 |
| registerLogic(IPresent logic) | 注册Presenter逻辑 | 可注册多个，需要实现IPresenter空接口 |
| obtainLogic(Class<?> clazz) | 获取对应注册的Presenter实例 | 以class作为key |
| bind(RecyclerView rcy) | 绑定Adapter | 会重新创建Adapter并绑定 |
| RecyclerView.Adapter getAdapter() | 获取Adapter |  |
| pushLife(ILifeCycle lifeCycle) | 注册任何对象监听生命周期 | 实现ILifeCycler接口 |
| pushGC(IGC gc) | 监听Destroy生命周期 |  |

### 详细使用方式  
详细使用方式->[Wiki](https://github.com/DrownCoder/EMvp/wiki)  

**:mag_right:Wiki相关：**  
1.[MVP模式的使用](https://github.com/DrownCoder/EMvp/wiki/MVP%E6%A8%A1%E5%BC%8F%E7%9A%84%E4%BD%BF%E7%94%A8)  
2.[组件化项目中R文件无法使用](https://github.com/DrownCoder/EMvp/wiki/%E7%BB%84%E4%BB%B6%E5%8C%96%E9%A1%B9%E7%9B%AE%E4%B8%ADR%E6%96%87%E4%BB%B6%E6%97%A0%E6%B3%95%E4%BD%BF%E7%94%A8)  
3.[优化反射创建](https://github.com/DrownCoder/EMvp/wiki/%E4%BC%98%E5%8C%96%E5%8F%8D%E5%B0%84%E5%88%9B%E5%BB%BA)  
4.[多人楼层打通](https://github.com/DrownCoder/EMvp/wiki/%E5%A4%9A%E4%BA%BAMIX%E6%A8%A1%E5%BC%8F)   
5.更多问题欢迎提issue:blush:	～

### License
```
Copyright 2017 [DrownCoder] 

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
