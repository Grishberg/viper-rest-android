package com.grishberg.viper_rest_android.presentation.main.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.grishberg.datafacade.controllers.BaseRecyclerAdapter;
import com.grishberg.datafacade.data.ListResult;
import com.grishberg.viper_rest_android.R;
import com.grishberg.viper_rest_android.domain.models.Shop;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by grishberg on 10.06.16.
 * Адаптер для отображения списка предприятий
 */
public class ShopsAdapter extends BaseRecyclerAdapter<Shop, ShopsAdapter.ShopsViewHolder> {
    private static final String TAG = ShopsAdapter.class.getSimpleName();

    private View.OnClickListener onItemClickListener;

    public ShopsAdapter(@NonNull Context context, @NonNull ListResult<Shop> listResult) {
        super(context, listResult);
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ShopsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_shop_element, parent, false);
        return new ShopsViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(ShopsViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static class ShopsViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tvName)
        TextView name;
        @Bind(R.id.ivBanner)
        ImageView banner;

        public ShopsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Shop model) {
            name.setText(model.getName());
            // TODO: Show image from url with UIL
        }
    }
}
