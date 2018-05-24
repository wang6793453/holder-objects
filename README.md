# About
这个库的功能主要是用来做多对象持有者，类似java.util.List，这个库的侧重点是用来做遍历，比如多个监听的保存

目前的实现类有：
* FStrongObjectsHolder<T> 强引用多对象持有者
* FWeakObjectsHolder<T> 若引用多对象持有者

# Gradle
`implementation 'com.fanwe.android:holder-objects:1.0.5'`

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
            mHolder.add(view); //添加对象
        }

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Object data = null;

                data = mHolder.foreach(mForeachCallback); // 正序遍历
                Log.e(TAG, "----------------------------------------");
                data = mHolder.foreachReverse(mForeachCallback); // 倒序遍历

                Log.e(TAG, "foreach result:" + data);
            }
        });
    }

    private final ForeachCallback<View> mForeachCallback = new ForeachCallback<View>()
    {
        @Override
        protected boolean next(View item)
        {
            Log.i(TAG, String.valueOf(item.getTag()));

            setData("data"); // 设置数据，遍历结束后会返回

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

    /**
     * 返回保存的所有对象信息，常用来输出日志调试
     *
     * @return
     */
    String getObjectsString();
}
```