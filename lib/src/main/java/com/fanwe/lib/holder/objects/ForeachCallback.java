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

/**
 * Created by zhengjun on 2018/3/5.
 */
public abstract class ForeachCallback<T>
{
    private Object mData;

    /**
     * 设置遍历的数据
     *
     * @param data
     */
    protected final void setData(Object data)
    {
        mData = data;
    }

    /**
     * 返回设置的数据
     *
     * @return
     */
    public final Object getData()
    {
        return mData;
    }

    /**
     * 遍历到每一个item的时候触发此方法
     *
     * @param item
     * @return true-停止遍历，false-继续遍历
     */
    protected abstract boolean next(T item);
}
