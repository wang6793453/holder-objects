package com.fanwe.lib.holder.objects.iterator;

import com.fanwe.lib.holder.objects.FObjectsHolder;

/**
 * Created by zhengjun on 2018/3/3.
 */
public class FObjectsHolderIterator<T> extends FAbstractIterator<T>
{
    private FObjectsHolder<T> mHolder;

    public FObjectsHolderIterator(FObjectsHolder<T> holder)
    {
        mHolder = holder;
    }

    @Override
    protected T get(int index)
    {
        return mHolder.get(index);
    }

    @Override
    protected void remove(T object)
    {
        mHolder.remove(object);
    }

    @Override
    protected int size()
    {
        return mHolder.size();
    }
}
