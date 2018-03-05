package com.fanwe.lib.holder.objects;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 弱引用对象holder
 *
 * @param <T>
 */
public class FWeakObjectsHolder<T> implements FObjectsHolder<T>
{
    private final List<WeakReference<T>> mListObject = new CopyOnWriteArrayList<>();
    private final ReferenceQueue<T> mQueue = new ReferenceQueue<>();

    @Override
    public boolean add(T object)
    {
        if (object == null || contains(object))
        {
            return false;
        }

        WeakReference<T> reference = new WeakReference<>(object, mQueue);
        mListObject.add(reference);
        return true;
    }

    @Override
    public boolean remove(Object object)
    {
        if (object == null)
        {
            return false;
        }

        releaseWeakReferenceIfNeed();
        for (WeakReference<T> item : mListObject)
        {
            if (object.equals(item.get()))
            {
                mListObject.remove(item);
                return true;
            }
        }
        return false;
    }

    @Override
    public T get(int index)
    {
        releaseWeakReferenceIfNeed();
        if (index >= 0 && index < size())
        {
            return mListObject.get(index).get();
        } else
        {
            return null;
        }
    }

    @Override
    public boolean contains(T object)
    {
        if (object == null)
        {
            return false;
        }

        releaseWeakReferenceIfNeed();
        for (WeakReference<T> item : mListObject)
        {
            if (object.equals(item.get()))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size()
    {
        releaseWeakReferenceIfNeed();
        return mListObject.size();
    }

    @Override
    public void clear()
    {
        releaseWeakReferenceIfNeed();
        mListObject.clear();
    }

    @Override
    public List<T> toList()
    {
        releaseWeakReferenceIfNeed();

        List<T> list = new ArrayList<>();
        for (WeakReference<T> item : mListObject)
        {
            list.add(item.get());
        }
        return list;
    }

    private void releaseWeakReferenceIfNeed()
    {
        while (true)
        {
            Reference<? extends T> item = mQueue.poll();
            if (item == null)
            {
                break;
            } else
            {
                mListObject.remove(item);
            }
        }
    }
}
