package conraud.sylvain.mynews.data;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Article implements Serializable {


        @SerializedName("section")
        @Expose
        private String section;
        @SerializedName("subsection")
        @Expose
        private String subsection;
        @SerializedName("title")
        @Expose
        private String title;

        @SerializedName("url")
        @Expose
        private String url;

        @SerializedName("updated_date")
        @Expose
        private String updatedDate;
        @SerializedName("created_date")
        @Expose
        private String createdDate;
        @SerializedName("published_date")
        @Expose
        private String publishedDate;

        @SerializedName("multimedia")
        @Expose
        private List<Multimedia> multimedia = null;

        @SerializedName("media")
        @Expose
        private List<Multimedia> media = null;

        @SerializedName("lead_paragraph")
        @Expose
        private String leadParagraph = null;

        @SerializedName("pub_date")
        @Expose
        private String pubDate = null;

        @SerializedName("section_name")
        @Expose
        private String sectionName = null;

        public String getSectionName(){
                return sectionName;
        }

        public String getPubDate(){
                return pubDate;
        }

        public String getLeadParagraph(){
                return leadParagraph;
        }
        public String getSection() {
            return section;
        }

        public List<Multimedia> getMultimedia() {
            return multimedia;
        }
        public List<Multimedia>  getMedia(){
                return media;
        }

        public String getSubsection() {
            return subsection;
        }

        public String getTitle() {
            return title;
        }

        public String getUrl() {
            return url;
        }

        public String getUpdatedDate() {
            return updatedDate;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public String getPublishedDate() {
            return publishedDate;
        }
}
