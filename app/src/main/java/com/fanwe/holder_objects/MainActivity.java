package com.fanwe.holder_objects;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.fanwe.lib.holder.objects.FObjectsHolder;
import com.fanwe.lib.holder.objects.FStrongObjectsHolder;
import com.fanwe.lib.iterator.FIterator;
import com.fanwe.lib.iterator.FListIterator;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = MainActivity.class.getSimpleName();

    private FObjectsHolder<View> mHolder = new FStrongObjectsHolder<>();

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
                    doForeach(false);
                }
                Log.e(TAG, "time:" + (System.currentTimeMillis() - start));
            }
        });

        fillData();
        doNext(true);
        doPrevious(true);
    }

    private void fillData()
    {
        mHolder.clear();
        for (int i = 0; i < 20; i++)
        {
            View view = new View(this);
            view.setTag(i);
            mHolder.add(view);
        }
    }

    private void doForeach(boolean log)
    {
        List<View> list = mHolder.toList();
        for (View item : list)
        {
            if (log)
            {
                Log.i(TAG, String.valueOf(item.getTag()));
            }
        }
    }

    private void doNext(boolean log)
    {
        FIterator<View> it = new FListIterator<>(mHolder.toList());
        it.prepare(true);
        while (it.hasNext())
        {
            View item = it.next();
            if (log)
            {
                Log.i(TAG, String.valueOf(item.getTag()));
            }
        }
    }

    private void doPrevious(boolean log)
    {
        FIterator<View> it = new FListIterator<>(mHolder.toList());
        it.prepare(false);
        while (it.hasNext())
        {
            View item = it.next();
            if (log)
            {
                Log.i(TAG, String.valueOf(item.getTag()));
            }
        }
    }
}
