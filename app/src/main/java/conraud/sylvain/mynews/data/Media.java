package conraud.sylvain.mynews.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Media {

    @SerializedName("url")
    @Expose
    private String url;

    public String getUrl() {
        return url;
    }
}
