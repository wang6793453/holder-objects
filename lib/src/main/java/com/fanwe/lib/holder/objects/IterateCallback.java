package com.fanwe.lib.holder.objects;

/**
 * Created by zhengjun on 2018/2/23.
 */
public abstract class IterateCallback<T>
{
    private boolean mIsBreakForeach;

    /**
     * 遍历回调
     *
     * @param item
     */
    public abstract void next(T item);

    public final boolean isBreakForeach()
    {
        return mIsBreakForeach;
    }

    /**
     * 停止遍历
     */
    public final void breakForeach()
    {
        mIsBreakForeach = true;
    }
}
