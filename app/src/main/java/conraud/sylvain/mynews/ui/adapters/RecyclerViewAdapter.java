package conraud.sylvain.mynews.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import conraud.sylvain.mynews.R;
import conraud.sylvain.mynews.data.Article;
import conraud.sylvain.mynews.ui.fragments.ItemViewHolder;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private final List<Article> articleList;
    private final int position;


    /*Constructor*/
    public RecyclerViewAdapter(List<Article> articleList, int position) {
        this.articleList = articleList;
        this.position = position;
    }

    /*Create ViewHolder*/
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_main_item, viewGroup,false);
        return new ItemViewHolder(view);
    }
    /*implement ViewHolder*/
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.displayItem(articleList.get(i), position);
    }
    /*size recycler view*/
    @Override
    public int getItemCount() {
        return articleList.size();
    }


}
