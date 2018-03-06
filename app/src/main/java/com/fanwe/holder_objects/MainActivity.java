package com.fanwe.holder_objects;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fanwe.lib.holder.objects.FObjectsHolder;
import com.fanwe.lib.holder.objects.FStrongObjectsHolder;
import com.fanwe.lib.holder.objects.ForeachCallback;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = MainActivity.class.getSimpleName();

    private FObjectsHolder<Integer> mHolder = new FStrongObjectsHolder<>();
//    private FObjectsHolder<Integer> mHolder = new FWeakObjectsHolder<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillData();
        doForeach(true);
        doForeachReverse(true);
    }

    private void fillData()
    {
        mHolder.clear(); //清空所有对象
        for (int i = 0; i < 20; i++)
        {
            mHolder.add(i); //添加对象
        }
    }

    private void doForeach(final boolean log)
    {
        /**
         * 正序遍历
         */
        mHolder.foreach(new ForeachCallback<Integer>()
        {
            @Override
            protected void next(Integer item)
            {
                if (item == 5)
                {
                    breakForeach(); //停止遍历
                }
                if (log)
                {
                    Log.i(TAG, String.valueOf(item));
                }
            }
        });
    }

    private void doForeachReverse(final boolean log)
    {
        /**
         * 倒序遍历
         */
        mHolder.foreachReverse(new ForeachCallback<Integer>()
        {
            @Override
            protected void next(Integer item)
            {
                if (log)
                {
                    Log.e(TAG, String.valueOf(item));
                }
            }
        });
    }
}
