package conraud.sylvain.mynews.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import conraud.sylvain.mynews.R;
import conraud.sylvain.mynews.data.Article;
import conraud.sylvain.mynews.ui.adapters.RecyclerViewAdapter;

public class MainFragment extends Fragment {

    int position;
    public List<Article> articleList = new ArrayList<>();
    public RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(articleList,position);

    public MainFragment() {
        // Required empty public constructor
    }

    /*create instance main fragment*/
    public static MainFragment newInstance(int position) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.position = position;
        fragment.setArguments(args);
        return fragment;
    }



    /*create view and configure Recycler*/
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_main, container, false);

        assert getArguments() != null;
        int position = getArguments().getInt("position");

        RecyclerView recyclerView = result.findViewById(R.id.fragment_main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerViewAdapter = new RecyclerViewAdapter(articleList, position);
        recyclerView.setAdapter(recyclerViewAdapter);

        return result;
    }


}
