import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.FaqResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FaqController implements Callback<FaqResponseBody> {

    public static String BASE_URL = "http://localhost:1337";


    public void getAllConfiguredFaqs() {
        FaqApi faqApi = getFaqApiClient();

        Call<FaqResponseBody> call = faqApi.getFaqs();
        call.enqueue(this);
    }

    public void getFaqBySlug() {
        FaqApi faqApi = getFaqApiClient();
        Call<FaqResponseBody> call = faqApi.getFaqBySlug("mmbus-frequently-asked-questions");
        call.enqueue(this);
    }

    private FaqApi getFaqApiClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(FaqApi.class);
    }


    public static void main(String[] args) {
        FaqController controller = new FaqController();

        //Get All Faqs
        //controller.getAllConfiguredFaqs();

        //Get Faq by slug
        controller.getFaqBySlug();
    }

    @Override
    public void onResponse(Call<FaqResponseBody> call, Response<FaqResponseBody> response) {
        if(response.isSuccessful()) {
            FaqResponseBody faqs = response.body();
            faqs.data.stream().forEach(faq -> {
                System.out.println(faq.attributes.title + " (" + faq.attributes.slug + ") ");
                System.out.println("-------------------------------------------------------------------------------------------");
                faq.attributes.faqItems.stream().forEach(faqItem -> {
                    System.out.println(faqItem.title);
                    faqItem.questionAnswer.stream().forEach(ques -> {
                        System.out.println(ques.question);
                        System.out.println(ques.answer);
                        System.out.println();
                    });
                });
            });
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<FaqResponseBody> call, Throwable throwable) {
        throwable.printStackTrace();

    }
}
