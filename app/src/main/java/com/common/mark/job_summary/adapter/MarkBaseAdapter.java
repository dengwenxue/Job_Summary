package com.common.mark.job_summary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.mark.job_summary.R;
import com.common.mark.job_summary.bean.FilmBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 适用一下View的adapter
 * 1.ListView
 * Created by mark on 2017/3/13.
 */

public class MarkBaseAdapter extends BaseAdapter {

    private Context mContext;
    private List<FilmBean.DataBean.MoviesBean> mData;
    private LayoutInflater mInflater;


    public MarkBaseAdapter(Context context, List<FilmBean.DataBean.MoviesBean> data) {
        this.mContext = context;
        this.mData = data;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (mData.size() != 0) {
            return mData.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mData != null) {
            return mData.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FilmBean.DataBean.MoviesBean moviesBean = mData.get(position);
//        int type = getItemViewType(position);

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.card_title);
            holder.img = (ImageView) convertView.findViewById(R.id.item_recreation_iamge);
            holder.star = (TextView) convertView.findViewById(R.id.card_actor);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 设置数据
        holder.title.setText(moviesBean.nm);
        // 设置图片
        Picasso.with(mContext).load(mData.get(position).img).fit().priority(Picasso.Priority.HIGH).into(holder.img);
        holder.star.setText("主演:" + moviesBean.star);

        return convertView;
    }

    /**
     * 根据数据源的position返回需要显示的的layout的type
     * type的值必须从0开始
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        // 该处的json数据中没有type
//        FilmBean.DataBean.MoviesBean moviesBean = mData.get(position);
//        int type = moviesBean.getType();

        return super.getItemViewType(position);
    }

    /**
     * 返回所有的layout的数量
     *
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    class ViewHolder {
        private TextView title;
        private ImageView img;
        private TextView star;
    }

}
