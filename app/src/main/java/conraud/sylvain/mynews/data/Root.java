package conraud.sylvain.mynews.data;

import java.io.Serializable;
import java.util.List;

public class Root implements Serializable {

    public List<Article> results;
    public Response response;
}
