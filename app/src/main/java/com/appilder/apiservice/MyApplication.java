package com.appilder.apiservice;

import android.app.Application;

import com.appilder.apilibrary.ClientConfig;
import com.appilder.apilibrary.ServiceApi;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by saleeh on 02/02/15.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ServiceApi.initialize(this,
                new ClientConfig.Builder()
                        .setCacheDir(getCacheDir())
                        .setcachedSize(10 * 1024 * 1024)
                        .setcacheExpire(60 * 60 * 24 * 28)
                        .build()
        );
        File cacheDir = StorageUtils.getCacheDirectory(getApplicationContext(), true);
        ImageLoaderConfiguration localImageLoaderConfiguration =
                new ImageLoaderConfiguration.Builder(
                        getApplicationContext()).defaultDisplayImageOptions(
                        new DisplayImageOptions.Builder()
                                .showImageOnLoading(R.drawable.placeholder)
                                .showImageForEmptyUri(R.drawable.placeholder)
                                .showImageOnFail(R.drawable.placeholder)
                                .cacheInMemory(true)
                                .cacheOnDisk(true)
                                .considerExifParams(true)
                                .displayer(new FadeInBitmapDisplayer(500))
                                .build())
                        .tasksProcessingOrder(QueueProcessingType.LIFO)
                        .memoryCache(new WeakMemoryCache())
                        .diskCache(new UnlimitedDiscCache(cacheDir)) // default
                        .diskCacheSize(50 * 1024 * 1024)
                        .diskCacheFileCount(100)
                        .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                        .build();


        ImageLoader.getInstance().init(localImageLoaderConfiguration);
    }
}
