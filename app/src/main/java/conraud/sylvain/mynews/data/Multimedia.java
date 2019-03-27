package conraud.sylvain.mynews.data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Multimedia implements Serializable {

        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("media-metadata")
        @Expose
        private List<Media> media;

        public String getUrl() {
            return url;
        }

        public List<Media>  getMedia(){
            return media;
        }

}
