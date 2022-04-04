import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.GuideLineResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GuideLineController implements Callback<GuideLineResponseBody> {

    public static String BASE_URL = "http://localhost:1337";

    public void getFaqBySlug() {
        GuideLineApi guideLineApi = getGuideApiClient();
        Call<GuideLineResponseBody> call = guideLineApi.getFaqBySlug("mmbus-passenger-guidelines");
        call.enqueue(this);
    }

    private GuideLineApi getGuideApiClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(GuideLineApi.class);
    }


    public static void main(String[] args) {
        GuideLineController controller = new GuideLineController();

        //Get Faq by slug
        controller.getFaqBySlug();
    }


    @Override
    public void onResponse(Call<GuideLineResponseBody> call, Response<GuideLineResponseBody> response) {
        if(response.isSuccessful()) {
            GuideLineResponseBody guideLines = response.body();
            guideLines.data.stream().forEach(guideLine -> {
                System.out.println(guideLine.attributes.title + " (" + guideLine.attributes.slug + ") ");
                System.out.println("-------------------------------------------------------------------------------------------");
                guideLine.attributes.guidelineItems.stream().forEach(item -> {
                    System.out.println(item.title);
                    System.out.println(item.content);
                });
            });
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<GuideLineResponseBody> call, Throwable throwable) {

    }
}
