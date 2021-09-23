package cn.skyjilygao.springboot;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;


@State(Scope.Benchmark)
@Slf4j
public class JmhTests {

    @Benchmark
//    @Warmup(iterations = 1, time = 3)
    @Fork(1)
    @Threads(1)
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 1, time = 1)
    public void testForEach() {
        //
//        System.out.println("current Thread:" + Thread.currentThread().getName() + "==>");
        log.info("log...");
    }

    public final OkHttpClient client = new OkHttpClient();

    public String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @Benchmark
//    @Warmup(iterations = 1, time = 3)
    @Fork(2)
    @Threads(2)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 1, time = 10)
    public void main() throws IOException {
        JmhTests jmhTests = new JmhTests();
        String response = jmhTests.run("http://192.168.88.23:32000/hello/sky");
        System.out.println(response);
    }

}
