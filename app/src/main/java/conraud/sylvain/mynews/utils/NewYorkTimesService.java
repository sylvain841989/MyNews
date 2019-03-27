package conraud.sylvain.mynews.utils;

import conraud.sylvain.mynews.data.Root;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NewYorkTimesService {

    @GET("/svc/topstories/v2/{home}.json?api-key=QvXYMDrGQ0TCPo3okaqyuUSp54eQBniA")
    Call<Root> callTopStories (@Path("home") String category);

    @GET("/svc/mostpopular/v2/{viewed}/1.json?api-key=QvXYMDrGQ0TCPo3okaqyuUSp54eQBniA")
    Call<Root> callMostPopular (@Path("viewed") String type);

}
