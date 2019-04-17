package conraud.sylvain.mynews.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import conraud.sylvain.mynews.R;
import conraud.sylvain.mynews.data.Article;
import conraud.sylvain.mynews.data.Root;
import conraud.sylvain.mynews.ui.adapters.RecyclerViewAdapter;
import conraud.sylvain.mynews.utils.ItemClickSupport;

public class ResultsSearchActivity extends AppCompatActivity {

    private Root root;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Article> articleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_search);
        root  = (Root) getIntent().getSerializableExtra("root");
       // android.os.Debug.waitForDebugger();
        configureRecycler();
        configureToolbar();
        configureOnClickRecyclerView();

        System.out.println(articleList);
        if(articleList == null || articleList.size() == 0)
            displayDialogNoArticle();
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerViewAdapter.notifyDataSetChanged();
    }

    /*configure UI*/
    //Configure Toolbar
    private void configureToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Articles");
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    //configure Recycler
    private void configureRecycler(){
        int key;
        if(root.response!=null && root.response.docs != null){
            articleList = root.response.docs;
            key = 3;
        }else{
            articleList = root.results;
            key = 0;
        }
        recyclerViewAdapter = new RecyclerViewAdapter(articleList,key);
        recyclerView = findViewById(R.id.activity_result_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    //configure click
    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(recyclerView, R.layout.fragment_main_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        openArticle(articleList.get(position));
                    }
                });
    }

    private void openArticle(Article article){
        Intent intent = new Intent(this, ArticleWebViewActivity.class);
        if(article.getWeburl() != null){
            intent.putExtra("url", article.getWeburl());
        }
        else{
            intent.putExtra("url",article.getUrl());
        }
        startActivity(intent);
    }

    /*Open dialog if no article available*/
    private void displayDialogNoArticle(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Aucun article ne correspond à votre recherche");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).create()
                .show();
    }
}
