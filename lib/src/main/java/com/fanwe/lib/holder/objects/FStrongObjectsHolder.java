package com.fanwe.lib.holder.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 强引用对象holder
 *
 * @param <T>
 */
public class FStrongObjectsHolder<T> implements FIObjectsHolder<T>
{
    private final List<T> mListObject = new CopyOnWriteArrayList<>();

    @Override
    public synchronized boolean add(T object)
    {
        if (object == null || contains(object))
        {
            return false;
        }

        mListObject.add(object);
        return true;
    }

    @Override
    public synchronized boolean remove(Object object)
    {
        return mListObject.remove(object);
    }

    @Override
    public synchronized boolean contains(T object)
    {
        return mListObject.contains(object);
    }

    @Override
    public synchronized int size()
    {
        return mListObject.size();
    }

    @Override
    public synchronized void foreach(IterateCallback<T> callback)
    {
        if (callback == null)
        {
            return;
        }

        for (T item : mListObject)
        {
            callback.next(item);
            if (callback.isBreakForeach())
            {
                break;
            }
        }
    }

    @Override
    public synchronized void foreachReverse(IterateCallback<T> callback)
    {
        if (callback == null)
        {
            return;
        }

        final List<T> list = new ArrayList<>(mListObject);

        final int size = list.size();
        for (int i = size - 1; i >= 0; i--)
        {
            callback.next(list.get(i));
            if (callback.isBreakForeach())
            {
                break;
            }
        }
    }
}
