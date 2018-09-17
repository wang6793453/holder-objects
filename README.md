# About
这个库的功能主要是用来做多对象持有者，类似java.util.List，这个库的侧重点是用来做遍历，比如多个监听的保存

目前的实现类有：
* FStrongObjectsHolder<T> 强引用多对象持有者
* FWeakObjectsHolder<T> 若引用多对象持有者

# Gradle
[![](https://jitpack.io/v/zj565061763/holder-objects.svg)](https://jitpack.io/#zj565061763/holder-objects)

# 简单demo
```java
public class MainActivity extends AppCompatActivity
{
    public static final String TAG = MainActivity.class.getSimpleName();

    /**
     * 强引用
     */
    private ObjectsHolder<View> mHolder = new FStrongObjectsHolder<>(null);

    /**
     * 弱引用
     */
//    private ObjectsHolder<View> mHolder = new FWeakObjectsHolder<>(null);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 5; i++)
        {
            View view = new View(this);
            view.setTag(i);
            //添加对象
            mHolder.add(view);
        }

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 正序遍历
                Object data = mHolder.foreach(mForeachCallback);
                Log.e(TAG, "----------------------------------------");

                // 倒序遍历
                data = mHolder.foreachReverse(mForeachCallback);
                Log.e(TAG, "foreach result:" + data);
            }
        });
    }

    private final ObjectsHolder.ForeachCallback<View> mForeachCallback = new ObjectsHolder.ForeachCallback<View>()
    {
        @Override
        protected boolean next(View item)
        {
            Log.i(TAG, String.valueOf(item.getTag()));

            // 设置数据，遍历结束后会返回
            setData("data");

            // 返回true-停止遍历
            return false;
        }
    };
}
```

# 接口
```java
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

    abstract class ForeachCallback<T>
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
}
```