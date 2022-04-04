import response.FaqResponse;
import response.GuideLineResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GuideLineApi {

    @GET("/api/guidelines?populate=*")
    Call<GuideLineResponse> getFaqBySlug(@Query("filters[slug][$eq]") String slug);
}
