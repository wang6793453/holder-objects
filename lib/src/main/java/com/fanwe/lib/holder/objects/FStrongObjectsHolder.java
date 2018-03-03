package com.fanwe.lib.holder.objects;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 强引用对象holder
 *
 * @param <T>
 */
public class FStrongObjectsHolder<T> extends FAbstractObjectsHolder<T>
{
    private final List<T> mListObject = new CopyOnWriteArrayList<>();

    @Override
    public boolean add(T object)
    {
        if (object == null || contains(object))
        {
            return false;
        }

        mListObject.add(object);
        return true;
    }

    @Override
    public boolean remove(Object object)
    {
        return mListObject.remove(object);
    }

    @Override
    public T get(int index)
    {
        if (index >= 0 && index < size())
        {
            return mListObject.get(index);
        } else
        {
            return null;
        }
    }

    @Override
    public boolean contains(T object)
    {
        return mListObject.contains(object);
    }

    @Override
    public int size()
    {
        return mListObject.size();
    }

    @Override
    public void clear()
    {
        mListObject.clear();
    }

    @Override
    public List<T> toList()
    {
        return mListObject;
    }
}
