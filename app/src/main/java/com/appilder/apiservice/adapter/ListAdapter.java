package com.appilder.apiservice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.appilder.apilibrary.models.Repo;
import com.appilder.apiservice.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by saleeh on 02/02/15.
 */
public class ListAdapter extends ArrayAdapter<Repo> {

    Context context;
    private LayoutInflater mInflater;


    public ListAdapter(Context context, List<Repo> objects) {
        super(context, 0, objects);
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecordHolder holder = null;
        int type = getItemViewType(position);
        if (convertView == null) {

            holder = new RecordHolder();
            convertView = mInflater.inflate(R.layout.list_repo_item, parent, false);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.user = (TextView) convertView.findViewById(R.id.user);
            convertView.setTag(holder);

        } else {
            holder = (RecordHolder) convertView.getTag();
        }
        Repo item = getItem(position);

        holder.txtTitle.setText(item.getName());
        holder.user.setText(item.getOwner().getLogin());

        ImageLoader.getInstance().displayImage(item.getOwner().getAvatarUrl(), holder.imageView);


        return convertView;
    }


    class RecordHolder {
        TextView txtTitle;
        ImageView imageView;
        TextView user;

    }
}
