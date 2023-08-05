package com.example.mvpshop.ui.home.adapter;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RawRes;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.webp.decoder.WebpDrawable;
import com.bumptech.glide.integration.webp.decoder.WebpDrawableTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.mvpshop.R;
import com.example.mvpshop.bean.Goods;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private static final String TAG = "HomeRecyclerViewAdapter";
    private RecyclerView recyclerView;
    private OnItemClickListener onItemClickListener;
    private Context context;
    private List<Goods> data;

    public HomeRecyclerViewAdapter(Context context, RecyclerView recyclerView, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.onItemClickListener = onItemClickListener;
        this.data = new ArrayList<>();
    }

    public void setData(List<Goods> data) {
        this.data.clear();
        this.data.addAll(data);
//        this.data = data;  // todo 为何不使用这个
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(viewType, parent, false);
        view.setOnClickListener(this);
        if (viewType == R.layout.home_image_text_box) return new MultiItemVewHolder(view);
        return new SingleItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case R.layout.home_banner_box:
                Banner banner = (Banner) ((SingleItemViewHolder) holder).itemView;
                Goods bannerGoods = data.get(position);
                banner.setAdapter(new BannerImageAdapter<String>(bannerGoods.getBanners()) {
                    @Override
                    public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                        CenterCrop centerCrop = new CenterCrop();
                        Glide.with(holder.imageView)
                                .load(data)
                                .optionalTransform(centerCrop)
                                .optionalTransform(WebpDrawable.class, new WebpDrawableTransformation(centerCrop))
                                .into(holder.imageView);
                        holder.imageView.setOnClickListener(HomeRecyclerViewAdapter.this);
                    }
                })
                        .addBannerLifecycleObserver((LifecycleOwner) this.context)
                        .setIndicator(new CircleIndicator(this.context));
                break;
            case R.layout.home_image_box:
                ImageView imageView = (ImageView) ((SingleItemViewHolder) holder).itemView;
                CenterCrop centerCrop = new CenterCrop();
                Glide.with(imageView)
                        .load(data.get(position).getImageUrl())
                        .optionalTransform(centerCrop)
                        .optionalTransform(WebpDrawable.class, new WebpDrawableTransformation(centerCrop))
                        .into(imageView);
                break;
            case R.layout.home_text_box:
                TextView textView = (TextView) ((SingleItemViewHolder) holder).itemView;
                textView.setText(data.get(position).getText());
                break;
            case R.layout.home_image_text_box:
                imageView = ((MultiItemVewHolder) holder).imageView;
                textView = ((MultiItemVewHolder) holder).textView;
                centerCrop = new CenterCrop();
                Glide.with(imageView)
                        .load(data.get(position).getImageUrl())
                        .optionalTransform(centerCrop)
                        .optionalTransform(WebpDrawable.class, new WebpDrawableTransformation(centerCrop))
                        .into(imageView);
                textView.setText(data.get(position).getText());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).getBanners() != null) return R.layout.home_banner_box;
        if (data.get(position).getText() == null) return R.layout.home_image_box;
        if (data.get(position).getImageUrl() == null) return R.layout.home_text_box;
        return R.layout.home_image_text_box;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {
        int position = this.recyclerView.getChildAdapterPosition(v);
        this.onItemClickListener.onItemClick(this.data.get(position));
    }

    class SingleItemViewHolder extends RecyclerView.ViewHolder {

        public SingleItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class MultiItemVewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public MultiItemVewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.image);
            this.textView = itemView.findViewById(R.id.text);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Goods goods);
    }
}
