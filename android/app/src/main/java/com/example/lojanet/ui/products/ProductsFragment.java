package com.example.lojanet.ui.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lojanet.ProductsAdapter;
import com.example.lojanet.R;

public class ProductsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_products, container, false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerViewProducts);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ProductsAdapter mAdapter = new ProductsAdapter();
        recyclerView.setAdapter(mAdapter);

        return root;
    }



}