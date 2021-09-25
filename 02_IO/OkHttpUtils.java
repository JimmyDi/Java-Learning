package String;

import jdk.nashorn.internal.ir.RuntimeNode;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;


public class OkHttpUtils {
    final OkHttpClient clien = new OkHttpClient();

    String getRequest(String url) throws IOException {
        Request request = new Request.Builder()
                                        .url(url)
                                        .build();
        try(Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void main(String[] agrs) throws Exception{
        OkHttpUtils util = new OkHttpUtils();
        String url = "http://localhost:8801";
        String resp = util.getRequest(url);
    }


}
