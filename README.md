# BRVAH
http://www.recyclerview.org/
Powerful and flexible RecyclerAdapter,
Please feel free to use this. (Welcome to **Star** and **Fork**)

### 将brvah源码down下来修改部分源码，基于2.9.45-androidx版本修改，具体更改部分如下：
#### 1、新增onViewHolderCreated方法回调 子adapter类可以重新此方法，再viewholder创建的时候做一些初始化的操作，例如itemView的点击事件，应该放在onCreateViewHolder中，设置一次即可，放在bindViewHolder中，由于bindViewHolder滑动时item可见时就会执行，导致会频繁生成太多OnClickListener对象
#### 2、新增BaseItemProvider的onViewHolderCreated 子ItemProvider可以重新此方法，再viewholder创建的时候做一些初始化的操作，同上
#### 3、新增BaseItemProvider的子view的点击事件onChildClick，子view长按事件onChildLongClick
#### 4、去除BaseQuickAdapter的bindRecycleView方法，使用传统方式setAdapter即可，BaseQuickAdapter内部提供了一个弱引用的recycleview可以拿到recycleivew
#### 5、新增自动加载更多的开关，setAutoLoadMore(boolean autoLoadMore),默认true自动加载，传递false后不自动加载需要手动点击加载更多
#### 6、BaseItemProvider可以拿到adapter对象。需要使用的时候可以调用getAdapter()方法，并且新增了mData适配器原数据 ItemProvider可以拿到数据进行增删改等
#### 7、更新loadMoreView一个抽象方法，子类需要重写体用 手动加载的布局
#### 8、适配器中新增点击和长按事件的处理，为了处理以后viewholder公用的问题