package ru.PhotoGallery;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FlickrFetchr {
	public byte[] getUrIBytes(String urlSpec) throws IOException {
		URL url = new URL(urlSpec);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream in = connection.getInputStream();
			if (connection.getResponseCode()) !=HttpURLConnection.HTTP_OK){
				throw new IOException(connection.getResponseMessage() +
						": with " +
						urlSpec);
			}
		}
	}
}
