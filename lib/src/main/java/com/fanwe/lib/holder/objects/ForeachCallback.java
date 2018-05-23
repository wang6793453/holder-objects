package com.fanwe.lib.holder.objects;

/**
 * Created by zhengjun on 2018/3/5.
 */
public abstract class ForeachCallback<T>
{
    private Object mData;
    boolean mRemove;

    /**
     * 移除当前遍历到的item
     */
    protected final void remove()
    {
        mRemove = true;
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
     * @return true-停止遍历，false-继续遍历
     */
    protected abstract boolean next(T item);
}
