package ru.PhotoGallery;

import android.net.Uri;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FlickrFetchr {

	private static final String TAG = "FlickrFetchr";
	private static final String API_KEY = "ваш ключ API";

	public byte[] getUrIBytes(String urlSpec) throws IOException {
		URL url = new URL(urlSpec);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream in = connection.getInputStream();
			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new IOException(connection.getResponseMessage() +
						": with " +
						urlSpec);
			}

			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = in.read(buffer)) > 0) {
				out.write(buffer, 0, bytesRead);
			}
			out.close();
			return out.toByteArray();

		} finally {
			connection.disconnect();
		}

	}

	public String getUrlString(String urlSpec) throws IOException {
		return new String(getUrIBytes(urlSpec));
	}


	public void fetchItems() {
		try {
			String url = Uri.parse("https://api.flickr.com/services/rest/")
					.buildUpon()
					.appendQueryParameter("method", "flickr.photos.getRecent")
					.appendQueryParameter("api_key", API_KEY)
					.appendQueryParameter("format", "json")
					.appendQueryParameter("nojsoncallback", "1")
					.appendQueryParameter("extras", "url_s")
					.build().toString();
			String jsonString = getUrlString(url);
			Log.i(TAG, "Reseived JSON: " + jsonString);
		} catch (IOException ioe) {
			Log.e(TAG, "Failed to fetch items", ioe);
		}
	}
}
