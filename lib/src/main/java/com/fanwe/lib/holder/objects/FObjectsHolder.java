package com.fanwe.lib.holder.objects;

import com.fanwe.lib.holder.objects.iterator.FIterator;

/**
 * 对象holder
 *
 * @param <T>
 */
public interface FObjectsHolder<T>
{
    /**
     * 添加对象
     *
     * @param object
     * @return true-调用此方法后对象被添加了
     */
    boolean add(T object);

    /**
     * 移除对象
     *
     * @param object
     * @return true-调用此方法后对象被移除了
     */
    boolean remove(Object object);

    /**
     * 返回第几个对象
     *
     * @param index
     * @return
     */
    T get(int index);

    /**
     * 是否包含某个对象
     *
     * @param object
     * @return true-包含
     */
    boolean contains(T object);

    /**
     * 当前保存对象的个数
     *
     * @return
     */
    int size();

    /**
     * 清空
     */
    void clear();

    FIterator<T> iterator();

    /**
     * 遍历
     *
     * @param callback
     */
    void foreach(IterateCallback<T> callback);

    /**
     * 倒序遍历
     *
     * @param callback
     */
    void foreachReverse(IterateCallback<T> callback);
}
