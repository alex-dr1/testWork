package ru.alex.testwork.services;


import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Collectors;

@Service
public class SecuritiesIssImpl implements SecuritiesIss {

	@Autowired
	private final UriBuilder uriBuilder;

	public SecuritiesIssImpl(UriBuilder uriBuilder) {
		this.uriBuilder = uriBuilder;
	}

	@Override
	public String findSecuritiesBySecID(String SecID) {
		//TODO сделать билдер uri
//		String uri = "https://iss.moex.com/iss/securities.xml?q="
//				+SecID
//				+"&iss.meta=off&securities.columns=id,secid,shortname,regnumber,emitent_title";
//		try {
//
//			return getXMLSecurities(uri);
//		} catch (Exception e) {
			return null;
//		}
	}

	//TODO private
	public String getXMLSecurities(String secID) throws Exception {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(buildUriSecurities(secID))
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		if(response.statusCode() != 200){
			throw new RuntimeException("Response: " + response.statusCode());
		}

		return response.body()
//				.lines()
//				.filter(s -> {
//					if (s.length()>5){
//						return s.startsWith("<row ");
//					}
//					return false;
//				})
//				.map(s -> s.replace("&quot;",""))
//				.collect(Collectors.joining())
				;
	}

	private URI buildUriSecurities(String secID) {
		return uriBuilder
				.replaceQueryParam("q", secID)
				.build();
	}
}
