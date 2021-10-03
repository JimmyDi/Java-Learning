import io.netty.handler.codec.http.FullHttpResponse;

public class HeaderResponseFilter implements ResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("Netty-test", "nio-1");
    }
}