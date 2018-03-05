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
        mHolder.clear();
        for (int i = 0; i < 20; i++)
        {
            mHolder.add(i);
        }
    }

    private void doForeach(final boolean log)
    {
        mHolder.foreach(new ForeachCallback<Integer>()
        {
            @Override
            protected void next(Integer item)
            {
                if (log)
                {
                    Log.i(TAG, String.valueOf(item));
                }
            }
        });
    }

    private void doForeachReverse(final boolean log)
    {
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
