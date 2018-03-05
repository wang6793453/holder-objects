package com.fanwe.holder_objects;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.fanwe.lib.holder.objects.FObjectsHolder;
import com.fanwe.lib.holder.objects.FStrongObjectsHolder;
import com.fanwe.lib.holder.objects.FWeakObjectsHolder;
import com.fanwe.lib.holder.objects.ForeachCallback;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = MainActivity.class.getSimpleName();

    private FObjectsHolder<View> mHolder = new FWeakObjectsHolder<>();
    private List<View> mList = new CopyOnWriteArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                long start = System.currentTimeMillis();
                for (int i = 0; i < 10000; i++)
                {
                    doForeachReverse(false);
                }
                Log.e(TAG, "time:" + (System.currentTimeMillis() - start));
            }
        });

        fillData();
        doForeach(true);
        doForeachReverse(true);
    }

    private void fillData()
    {
        mHolder.clear();
        mList.clear();
        for (int i = 0; i < 20; i++)
        {
            View view = new View(this);
            view.setTag(i);
            mHolder.add(view);
            mList.add(view);
        }
    }

    private void doNormal(boolean log)
    {
        for (View item : mList)
        {
            if (log)
            {
                Log.i(TAG, String.valueOf(item.getTag()));
            }
        }
    }

    private void doForeach(final boolean log)
    {
        mHolder.foreach(new ForeachCallback<View>()
        {
            @Override
            protected void next(View item)
            {
                if (log)
                {
                    Log.i(TAG, String.valueOf(item.getTag()));
                }
            }
        });
    }

    private void doForeachReverse(final boolean log)
    {
        mHolder.foreachReverse(new ForeachCallback<View>()
        {
            @Override
            protected void next(View item)
            {
                if (log)
                {
                    Log.e(TAG, String.valueOf(item.getTag()));
                }
            }
        });
    }
}
