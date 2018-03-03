package com.fanwe.holder_objects;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.fanwe.lib.holder.objects.FObjectsHolder;
import com.fanwe.lib.holder.objects.FStrongObjectsHolder;
import com.fanwe.lib.holder.objects.IterateCallback;
import com.fanwe.lib.holder.objects.iterator.FIterator;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = MainActivity.class.getSimpleName();

    private FObjectsHolder<String> mObjectsHolder = new FStrongObjectsHolder<>();

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
                    doForeach();
                }
                Log.e(TAG, "time:" + (System.currentTimeMillis() - start));
            }
        });

        doFill();
        doNext();
        doPrevious();
    }

    private void doFill()
    {
        mObjectsHolder.clear();
        for (int i = 0; i < 20; i++)
        {
            mObjectsHolder.add(String.valueOf(i));
        }
    }

    private void doForeach()
    {
        mObjectsHolder.foreachReverse(new IterateCallback<String>()
        {
            @Override
            public void next(String item)
            {
//                Log.i(TAG, item);
            }
        });
    }

    private void doNext()
    {
        FIterator<String> it = mObjectsHolder.getIterator().prepareNext();
        while (it.hasNext())
        {
            String item = it.next();
//            Log.i(TAG, item);
        }
    }

    private void doPrevious()
    {
        FIterator<String> it = mObjectsHolder.getIterator().preparePrevious();
        while (it.hasPrevious())
        {
            String item = it.previous();
//            Log.i(TAG, item);
        }
    }
}
