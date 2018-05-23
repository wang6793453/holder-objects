package com.fanwe.lib.holder.objects;

/**
 * Created by zhengjun on 2018/3/5.
 */
public abstract class ForeachCallback<T>
{
    private boolean mIsBreakForeach;
    private Object mData;

    /**
     * 是否要求停止遍历
     *
     * @return
     */
    public final boolean isBreakForeach()
    {
        return mIsBreakForeach;
    }

    /**
     * 停止遍历
     */
    protected final void breakForeach()
    {
        mIsBreakForeach = true;
    }

    /**
     * 设置遍历的数据
     *
     * @param data
     */
    protected final void setData(Object data)
    {
        mData = data;
    }

    /**
     * 返回设置的数据
     *
     * @return
     */
    public final Object getData()
    {
        return mData;
    }

    /**
     * 遍历到每一个item的时候触发此方法
     *
     * @param item
     */
    protected abstract void next(T item);
}
