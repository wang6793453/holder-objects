package com.sd.holder_objects;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.sd.lib.holder.objects.FStrongObjectsHolder;
import com.sd.lib.holder.objects.ObjectsHolder;

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
