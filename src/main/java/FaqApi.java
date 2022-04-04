import model.FaqResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FaqApi {

    @GET("/api/faqs?populate[0]=faqItems&populate[1]=faqItems.questionAnswer")
    Call<FaqResponseBody> getFaqs();

//    @GET("/api/faqs?populate[0]=faqItems&populate[1]=faqItems.questionAnswer&filters[slug][$eq]=mmbus-frequently-asked-questions")
//    Call<FaqResponse> getFaqBySlug();

    @GET("/api/faqs?populate[0]=faqItems&populate[1]=faqItems.questionAnswer")
    Call<FaqResponseBody> getFaqBySlug(@Query("filters[slug][$eq]") String slug);
}
