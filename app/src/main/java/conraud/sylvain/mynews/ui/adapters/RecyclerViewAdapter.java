package conraud.sylvain.mynews.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import conraud.sylvain.mynews.R;
import conraud.sylvain.mynews.data.Article;
import conraud.sylvain.mynews.ui.activity.MainActivity;
import conraud.sylvain.mynews.ui.fragments.ItemViewHolder;
import conraud.sylvain.mynews.utils.ItemClickSupport;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<Article> articleList;
    private int position;
    RecyclerView recyclerView;

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
        recyclerView = viewGroup.findViewById(R.id.fragment_main_recycler_view);
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
