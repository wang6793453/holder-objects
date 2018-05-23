# About
这个库的功能主要是用来做多对象持有者，类似java.util.List，这个库的侧重点是用来做遍历，比如多个监听的保存

目前的实现类有：
* FStrongObjectsHolder<T> 强引用多对象持有者
* FWeakObjectsHolder<T> 若引用多对象持有者

# Gradle
`implementation 'com.fanwe.android:holder-objects:1.0.2'`