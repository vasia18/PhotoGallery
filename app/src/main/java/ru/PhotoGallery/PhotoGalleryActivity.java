package ru.PhotoGallery;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class PhotoGalleryActivity extends SingleFragmentActivity {

	protected Fragment createFragment() {
		return PhotoGalleryFragment.newInstance();
	}
}