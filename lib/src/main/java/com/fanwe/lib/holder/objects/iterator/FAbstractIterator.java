package com.fanwe.lib.holder.objects.iterator;

/**
 * Created by zhengjun on 2018/3/3.
 */
public abstract class FAbstractIterator<T> implements FIterator<T>
{
    private int mIndex;

    @Override
    public synchronized void prepareNext()
    {
        mIndex = -1;
    }

    @Override
    public synchronized boolean hasNext()
    {
        final int index = mIndex + 1;
        return index >= 0 && index < size();
    }

    @Override
    public synchronized T next()
    {
        mIndex++;
        return get(mIndex);
    }

    @Override
    public synchronized void preparePrevious()
    {
        mIndex = size();
    }

    @Override
    public synchronized boolean hasPrevious()
    {
        final int index = mIndex - 1;
        return index >= 0 && index < size();
    }

    @Override
    public synchronized T previous()
    {
        mIndex--;
        return get(mIndex);
    }

    @Override
    public synchronized void remove()
    {
        final T object = get(mIndex);
       remove(object);
    }

    protected abstract T get(int index);

    protected abstract void remove(T object);

    protected abstract int size();
}
