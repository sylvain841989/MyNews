package conraud.sylvain.mynews.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Media implements Serializable {

    @SerializedName("url")
    @Expose
    private String url;

    public String getUrl() {
        return url;
    }
}
