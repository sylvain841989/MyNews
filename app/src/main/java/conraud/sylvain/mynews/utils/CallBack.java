package conraud.sylvain.mynews.utils;

import android.content.Context;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import conraud.sylvain.mynews.data.Article;
import conraud.sylvain.mynews.data.Root;
import conraud.sylvain.mynews.ui.adapters.ViewPagerAdapter;

public class CallBack  implements CallService.Callback {

    public static int KEY_TOPSTORIES = 0;
    public static int KEY_MOSTPOPULAR = 1;
    public static int KEY_SCIENCE = 2;
    public static int KEY_SEARCH = 3;
    public static int KEY_NOTIFICATION = 4;

    public ViewPagerAdapter viewPagerAdapter;

    private static CallBack instance = null;

    /* Private constructor*/
    private CallBack() {

    }
    /* Static function*/
    public static CallBack getInstance() {
        if (instance == null) {
            instance = new CallBack();
        }
        return instance;
    }

    /*Callback*/
    @Override
    public void onResponse(Root root, int id) {
        refreshUi(root, id);
    }

    @Override
    public void onFailure(Context context) {
        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
    }

    /*Refresh recycler*/
    private void refreshUi(Root root, int id){
        viewPagerAdapter.arrayFragment[id].articleList.clear();
        viewPagerAdapter.arrayFragment[id].articleList.addAll(getArticle(root));
        viewPagerAdapter.arrayFragment[id].recyclerViewAdapter.notifyDataSetChanged();
    }

    /*Return List Article*/
    private List<Article> getArticle(Root root){
        return root.results;
    }
}
