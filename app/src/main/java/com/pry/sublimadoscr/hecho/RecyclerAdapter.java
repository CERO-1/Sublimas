package com.pry.sublimadoscr.hecho;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pry.sublimadoscr.R;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private List<Product> products = new ArrayList<>();
String id_user;

    public RecyclerAdapter (Context context,List<Product> products,String id){
        this.mContext = context;
        this.products = products;
        this.id_user = id;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle, mPrice;
        private ImageView mImageView;
        private RatingBar mRate;
        private LinearLayout mContainer;

        public MyViewHolder (View view){
            super(view);

            mTitle = view.findViewById(R.id.product_title);
            mImageView = view.findViewById(R.id.product_image);
            mRate = view.findViewById(R.id.product_rating);
            mPrice = view.findViewById(R.id.product_price);
            mContainer = view.findViewById(R.id.product_container);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.products_list_item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Product product = products.get(position);

        holder.mPrice.setText("$"+product.get$precio());
      //  holder.mRate.setRating(product.getRating());
        holder.mTitle.setText(product.getNombre());
        Glide.with(mContext).load(product.get$photo()).into(holder.mImageView);

        holder.mContainer.setOnClickListener(v -> {

            Intent intent = new Intent(mContext,DetailedProductsActivity.class);
            intent.putExtra("id_user", id_user);
            intent.putExtra("id",product.get$id());
            intent.putExtra("codigo",product.getNombre());
            intent.putExtra("title",product.getNombre());
            intent.putExtra("image",product.get$photo());
            intent.putExtra("descrip",product.get$descripcion());
            intent.putExtra("price",product.get$precio());

            mContext.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
