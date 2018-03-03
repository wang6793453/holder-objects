package com.fanwe.lib.holder.objects.iterator;

/**
 * Created by zhengjun on 2018/3/3.
 */
public interface FIterator<T>
{
    /**
     * 准备正序遍历
     */
    void prepareNext();

    boolean hasNext();

    T next();

    /**
     * 准备倒序遍历
     */
    void preparePrevious();

    boolean hasPrevious();

    T previous();

    void remove();
}
