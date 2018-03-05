package com.fanwe.lib.holder.objects;

import java.util.Iterator;

/**
 * Created by zhengjun on 2018/3/5.
 */
public abstract class ForeachCallback<T>
{
    private boolean mIsBreakForeach;
    private Object mData;
    Iterator mIterator;

    public final boolean isBreakForeach()
    {
        return mIsBreakForeach;
    }

    public final void breakForeach()
    {
        mIsBreakForeach = true;
    }

    public final void setData(Object data)
    {
        mData = data;
    }

    public final Object getData()
    {
        return mData;
    }

    protected final void remove()
    {
        mIterator.remove();
    }

    protected abstract void next(T item);
}
