package com.fanwe.holder_objects;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.fanwe.lib.holder.objects.FStrongObjectsHolder;
import com.fanwe.lib.holder.objects.ForeachCallback;
import com.fanwe.lib.holder.objects.ObjectsHolder;

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
