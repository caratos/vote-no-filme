package br.com.votefilme.test.util;

import java.util.Collection;
import java.util.concurrent.Future;

import br.com.votefilme.test.util.BlobstoreServiceMock;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.images.Composite;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.InputSettings;
import com.google.appengine.api.images.OutputSettings;
import com.google.appengine.api.images.ServingUrlOptions;
import com.google.appengine.api.images.Transform;

public class ImagesServiceMock implements ImagesService {

	@Override
	public Image applyTransform(Transform transform, Image image) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Image> applyTransformAsync(Transform transform, Image image) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image applyTransform(Transform transform, Image image,
			OutputEncoding encoding) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Image> applyTransformAsync(Transform transform, Image image,
			OutputEncoding encoding) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image applyTransform(Transform transform, Image image,
			OutputSettings settings) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Image> applyTransformAsync(Transform transform, Image image,
			OutputSettings settings) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image applyTransform(Transform transform, Image image,
			InputSettings inputSettings, OutputSettings ouputSettings) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Image> applyTransformAsync(Transform transform, Image image,
			InputSettings inputSettings, OutputSettings outputSettings) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image composite(Collection<Composite> composites, int width,
			int height, long color) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image composite(Collection<Composite> composites, int width,
			int height, long color, OutputEncoding encoding) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image composite(Collection<Composite> composites, int width,
			int height, long color, OutputSettings settings) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] histogram(Image image) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public String getServingUrl(BlobKey blobKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public String getServingUrl(BlobKey blobKey, boolean secureUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public String getServingUrl(BlobKey blobKey, int imageSize, boolean crop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public String getServingUrl(BlobKey blobKey, int imageSize, boolean crop,
			boolean secureUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServingUrl(ServingUrlOptions options) {
		// TODO Auto-generated method stub
		return BlobstoreServiceMock.stringUploadCreated;
	}

	@Override
	public void deleteServingUrl(BlobKey blobKey) {
		// TODO Auto-generated method stub

	}

}
