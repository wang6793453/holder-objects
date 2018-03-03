package com.fanwe.lib.holder.objects;

import com.fanwe.lib.holder.objects.iterator.FIterator;
import com.fanwe.lib.holder.objects.iterator.FObjectsHolderIterator;

/**
 * Created by zhengjun on 2018/3/3.
 */
public abstract class FAbstractObjectsHolder<T> implements FObjectsHolder<T>
{
    private FIterator<T> mIterator;

    @Override
    public FIterator<T> getIterator()
    {
        if (mIterator == null)
        {
            mIterator = new FObjectsHolderIterator<>(this);
        }
        return mIterator;
    }
}
