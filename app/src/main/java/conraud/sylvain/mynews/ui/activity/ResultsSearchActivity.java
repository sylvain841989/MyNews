package conraud.sylvain.mynews.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import conraud.sylvain.mynews.R;
import conraud.sylvain.mynews.data.Article;
import conraud.sylvain.mynews.data.Root;
import conraud.sylvain.mynews.ui.adapters.RecyclerViewAdapter;
import conraud.sylvain.mynews.utils.CallBack;

public class ResultsSearchActivity extends AppCompatActivity {

    Root root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_search);
        root  = (Root) getIntent().getSerializableExtra("root");
        configureRecycler();
    }

    void configureRecycler(){
        List<Article> test = root.response.docs;
        RecyclerView recyclerView = findViewById(R.id.activity_result_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new RecyclerViewAdapter(test, CallBack.KEY_SEARCH));
    }
}