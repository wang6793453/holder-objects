package com.fanwe.lib.holder.objects;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Collections;
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
    public synchronized boolean add(T object)
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
    public synchronized boolean remove(Object object)
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
    public synchronized boolean contains(T object)
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
    public synchronized int size()
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
    public synchronized void foreach(IterateCallback<T> callback)
    {
        if (callback == null)
        {
            return;
        }

        releaseWeakReferenceIfNeed();
        for (WeakReference<T> item : mListObject)
        {
            callback.next(item.get());
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

        Collections.reverse(mListObject);
        foreach(callback);
        Collections.reverse(mListObject);
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
