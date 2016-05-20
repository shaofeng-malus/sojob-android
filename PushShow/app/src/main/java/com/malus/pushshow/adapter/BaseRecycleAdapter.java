package com.malus.pushshow.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.malus.pushshow.Application.MainApplication;
import com.malus.pushshow.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/********************
 * 作者：malus
 * 日期：16/1/27
 * 时间：下午9:24
 * 注释：
 ********************/
public abstract class BaseRecycleAdapter<V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {
    protected LayoutInflater mInflater;
    protected MainApplication application;
    protected Context mContext;
    protected ImageLoader imageLoader;

    public BaseRecycleAdapter(Context context) {
        this.mContext = context;
        if (context.getApplicationContext() != null && context.getApplicationContext() instanceof MainApplication) {
            application = (MainApplication) context.getApplicationContext();
        }
        mInflater = LayoutInflater.from(context);

        imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory()
                .cacheOnDisc()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .displayer(new RoundedBitmapDisplayer(0))
                .build();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context).defaultDisplayImageOptions(options).build();
        imageLoader.init(configuration);

    }

}
