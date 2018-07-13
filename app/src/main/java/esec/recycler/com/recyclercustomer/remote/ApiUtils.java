package esec.recycler.com.recyclercustomer.remote;

public class ApiUtils {

    private ApiUtils() {}
    //
    public static final String BASE_URL = "http://testedserver.com/eseco/public/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}