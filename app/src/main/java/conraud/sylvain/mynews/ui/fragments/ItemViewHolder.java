package conraud.sylvain.mynews.ui.fragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import conraud.sylvain.mynews.R;
import conraud.sylvain.mynews.data.Article;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView textViewDate, textViewTitle, textViewCategory;
    private RequestManager glide;

    /*Assign Views*/
    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.fragment_main_item_image_view);
        textViewDate = itemView.findViewById(R.id.fragment_main_item_text_view_date);
        textViewCategory = itemView.findViewById(R.id.fragment_main_item_text_view_category);
        textViewTitle = itemView.findViewById(R.id.fragment_main_item_text_view_title);
        glide = Glide.with(itemView);
    }

    /*Implement item*/
    public void displayItem(Article article, int position){
        textViewDate.setText(article.getPublishedDate());
        textViewCategory.setText(article.getSection());
        textViewTitle.setText(article.getTitle());
        imageView.setImageResource(R.drawable.noimage);

        switch (position){
            case 0 : if(multimediaContentImage(article))
                glide.load(article.getMultimedia().get(0).getUrl()).into(imageView);
            break;
            case 1 :if(mediaContentImage(article))
                glide.load(article.getMedia().get(0).getMedia().get(0).getUrl()).into(imageView);
            break;
            case 2 :if(multimediaContentImage(article))
                glide.load(article.getMultimedia().get(0).getUrl()).into(imageView);
        }
    }

    /*check if article has image*/
    //check multimedia
    private Boolean multimediaContentImage(Article article){
        return article.getMultimedia().size() > 0;
    }
    //check media
    private Boolean mediaContentImage(Article article){
        return article.getMedia().size() > 0 && article.getMedia().get(0).getMedia().size()>0;
    }
}
