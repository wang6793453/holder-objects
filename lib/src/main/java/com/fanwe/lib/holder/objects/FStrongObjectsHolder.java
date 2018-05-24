/*
 * Copyright (C) 2017 zhengjun, fanwe (http://www.fanwe.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fanwe.lib.holder.objects;

import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 强引用对象holder
 *
 * @param <T>
 */
public class FStrongObjectsHolder<T> implements ObjectsHolder<T>
{
    private final List<T> mListObject;

    public FStrongObjectsHolder(List<T> list)
    {
        if (list == null)
            list = new CopyOnWriteArrayList<>();

        mListObject = list;
    }

    @Override
    public boolean add(T object)
    {
        if (object == null || contains(object))
            return false;

        mListObject.add(object);
        return true;
    }

    @Override
    public boolean remove(Object object)
    {
        return mListObject.remove(object);
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
    public Object foreach(ForeachCallback<T> callback)
    {
        if (callback == null)
            return null;

        for (T item : mListObject)
        {
            if (callback.next(item))
                break;
        }
        return callback.getData();
    }

    @Override
    public Object foreachReverse(ForeachCallback<T> callback)
    {
        if (callback == null)
            return null;

        final ListIterator<T> it = mListObject.listIterator(mListObject.size());
        while (it.hasPrevious())
        {
            if (callback.next(it.previous()))
                break;
        }
        return callback.getData();
    }

    @Override
    public String getObjectsString()
    {
        return mListObject.toString();
    }
}
