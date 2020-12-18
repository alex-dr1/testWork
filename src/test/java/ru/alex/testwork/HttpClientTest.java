package ru.alex.testwork;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.alex.testwork.utils.HttpStatus;

import javax.xml.stream.XMLStreamConstants;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HttpClientTest {

	private static final String SAMPLE_URL = "https://iss.moex.com/iss/reference/";


	@Test
	public final void test1() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(SAMPLE_URL))
				.build();

		HttpResponse<String> response =
				client.send(request, HttpResponse.BodyHandlers.ofString());
		assertEquals(response.statusCode(), HttpStatus.OK);
	}
}
