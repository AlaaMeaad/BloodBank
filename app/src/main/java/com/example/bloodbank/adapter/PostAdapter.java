package com.example.bloodbank.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.allposts.PostsData;
import com.example.bloodbank.helper.HelperMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private Activity activity;
    private Context context;
    private List<PostsData> postDataList = new ArrayList<>();

    public PostAdapter(Activity activity, Context context, List<PostsData> postDataList) {
        this.activity = activity;
        this.context = context;
        this.postDataList = postDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

        holder.postAdapterTvPostTitle.setText(postDataList.get(position).getTitle());
        HelperMethod.onLoadImageFromUrl(holder.postAdapterIvPostImage, postDataList.get(position).getThumbnailFullPath(), activity);

        if (postDataList.get(position).getIsFavourite()) {
            holder.postAdapterIvPostStatus.setImageResource(R.drawable.ic_heart_solid);
        } else {
            holder.postAdapterIvPostStatus.setImageResource(R.drawable.ic_heart_regular);
        }

    }

    private void setAction(ViewHolder holder, int position) {

        holder.postAdapterIvPostStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postDataList.get(position).setIsFavourite(!postDataList.get(position).getIsFavourite());
                if (postDataList.get(position).getIsFavourite()) {
                    holder.postAdapterIvPostStatus.setImageResource(R.drawable.ic_heart_solid);
                } else {
                    holder.postAdapterIvPostStatus.setImageResource(R.drawable.ic_heart_regular);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return postDataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.post_adapter_tv_post_title)
        TextView postAdapterTvPostTitle;
        @BindView(R.id.post_adapter_iv_post_status)
        ImageView postAdapterIvPostStatus;
        @BindView(R.id.post_adapter_iv_post_image)
        ImageView postAdapterIvPostImage;
        private View view;

        public ViewHolder(View itemview) {
            super(itemview);
            view = itemview;
            ButterKnife.bind(this, view);
        }
    }
}
