package ru.PhotoGallery;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;

public class PhotoGalleryFragment extends Fragment {

	private static final String TAG = "PhotoGalleryFragment";

	private RecyclerView mPhotoRecyclerView;

	public static PhotoGalleryFragment newInstance() {
		return new PhotoGalleryFragment();
	}


	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_photo_gallery, container, false);

		mPhotoRecyclerView = (RecyclerView) v.findViewById(R.id.Photo_recycler_view);
		mPhotoRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));


		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		new FetchItemsTask().execute();
	}

	private class FetchItemsTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
//			try {
//				String result = new FlickrFetchr()
//						.getUrliString("www.bignerdranch.com");
//				Log.i(TAG, "Fetched contents of URL: " + result);
//			} catch (IOException ioe) {
//				Log.e(TAG, "Failed of fetch URL: ", ioe);
//			}
			new FlickrFetchr().fetchItems();
			return null;
		}
	}
}
