package ru.GalkinAllan.Currency.requester;

        import org.springframework.stereotype.Service;

        import java.io.IOException;
        import java.net.URI;
        import java.net.http.HttpClient;
        import java.net.http.HttpRequest;
        import java.net.http.HttpResponse;

@Service
public class CurrencyRequesterImpl implements CurrencyRequester {

    @Override
    public String getRatesASXml(String url) throws IOException, InterruptedException {

        var client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();

    }
}