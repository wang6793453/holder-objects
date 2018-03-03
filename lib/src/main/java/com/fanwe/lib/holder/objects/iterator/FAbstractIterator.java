package com.fanwe.lib.holder.objects.iterator;

/**
 * Created by zhengjun on 2018/3/3.
 */
public abstract class FAbstractIterator<T> implements FIterator<T>
{
    private int mIndex;

    @Override
    public FIterator<T> prepareNext()
    {
        mIndex = -1;
        return this;
    }

    @Override
    public boolean hasNext()
    {
        final int index = mIndex + 1;
        return index >= 0 && index < size();
    }

    @Override
    public T next()
    {
        mIndex++;
        return get(mIndex);
    }

    @Override
    public FIterator<T> preparePrevious()
    {
        mIndex = size();
        return this;
    }

    @Override
    public boolean hasPrevious()
    {
        final int index = mIndex - 1;
        return index >= 0 && index < size();
    }

    @Override
    public T previous()
    {
        mIndex--;
        return get(mIndex);
    }

    @Override
    public void remove()
    {
        final T object = get(mIndex);
        remove(object);
    }

    protected abstract T get(int index);

    protected abstract void remove(T object);

    protected abstract int size();
}
