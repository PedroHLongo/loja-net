package com.example.lojanet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {


    List<Product> product = new ArrayList<>();

    public ProductsAdapter() {

        for(int i = 0; i < 100; i++){

            Product p = new Product();

            p.setName_product("Product " + i);
            p.setCategory_product("Category");
            p.setPrice_product("Price");
            product.add(p);

        }
    }

    @NonNull
    @Override
    public ProductsAdapter.ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_card, parent, false);
        ProductsViewHolder vh = new ProductsViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ProductsViewHolder holder, int position) {

        holder.name_product.setText(product.get(position).getName_product());
        holder.category_product.setText(product.get(position).getCategory_product());
        holder.price_product.setText(product.get(position).getPrice_product());

    }


    public class ProductsViewHolder extends RecyclerView.ViewHolder {

        TextView name_product;
        TextView category_product;
        TextView price_product;

        public ProductsViewHolder(View itemView) {
            super(itemView);

            name_product = (TextView) itemView.findViewById(R.id.product_name);
            category_product = (TextView) itemView.findViewById(R.id.product_category);
            price_product = (TextView) itemView.findViewById(R.id.product_price);

        }
    }


    @Override
    public int getItemCount() {
        return product.size();
    }

}

