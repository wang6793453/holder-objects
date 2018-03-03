package com.fanwe.lib.holder.objects;

import com.fanwe.lib.holder.objects.iterator.FAbstractIterator;
import com.fanwe.lib.holder.objects.iterator.FIterator;

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
            mIterator = new FAbstractIterator<T>()
            {
                @Override
                protected T get(int index)
                {
                    return FAbstractObjectsHolder.this.get(index);
                }

                @Override
                protected void remove(T object)
                {
                    FAbstractObjectsHolder.this.remove(object);
                }

                @Override
                protected int size()
                {
                    return FAbstractObjectsHolder.this.size();
                }
            };
        }
        return mIterator;
    }
}
