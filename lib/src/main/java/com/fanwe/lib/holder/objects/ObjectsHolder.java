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
 * 对象holder
 *
 * @param <T>
 */
public interface ObjectsHolder<T>
{
    /**
     * 添加对象
     *
     * @param object
     * @return true-调用此方法后对象被添加了
     */
    boolean add(T object);

    /**
     * 移除对象
     *
     * @param object
     * @return true-调用此方法后对象被移除了
     */
    boolean remove(Object object);

    /**
     * 是否包含某个对象
     *
     * @param object
     * @return true-包含
     */
    boolean contains(T object);

    /**
     * 当前保存对象的个数
     *
     * @return
     */
    int size();

    /**
     * 清空
     */
    void clear();

    /**
     * 正序遍历
     *
     * @param callback
     * @return 返回callback中的data {@link ForeachCallback#getData()}
     */
    Object foreach(ForeachCallback<T> callback);

    /**
     * 倒序遍历
     *
     * @param callback
     * @return 返回callback中的data {@link ForeachCallback#getData()}
     */
    Object foreachReverse(ForeachCallback<T> callback);

    /**
     * 返回保存的所有对象信息，常用来输出日志调试
     *
     * @return
     */
    String getObjectsString();
}
