package br.com.votefilme.test.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.ByteRange;
import com.google.appengine.api.blobstore.FileInfo;
import com.google.appengine.api.blobstore.UploadOptions;

public class BlobstoreServiceMock implements BlobstoreService {
	
	public static final String stringUploadCreated = "CODIGOGERADO-ACTIONUPLOAD";

	@Override
	public String createUploadUrl(String successPath) {
		
		return stringUploadCreated;
	}

	@Override
	public String createUploadUrl(String successPath,
			UploadOptions uploadOptions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void serve(BlobKey blobKey, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void serve(BlobKey blobKey, ByteRange byteRange,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void serve(BlobKey blobKey, String rangeHeader,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public ByteRange getByteRange(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(BlobKey... blobKeys) {
		// TODO Auto-generated method stub

	}

	@Override
	@Deprecated
	public
	Map<String, BlobKey> getUploadedBlobs(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, List<BlobKey>> getUploads(HttpServletRequest request) {
		
		Map<String, List<BlobKey> > blobs = new HashMap<String, List<BlobKey>>();
		List<BlobKey> list = new ArrayList<BlobKey>();
		list.add( new BlobKey(stringUploadCreated) );
		blobs.put("imagem", list );
		return blobs;
	}

	public Map<String, List<BlobInfo>> getBlobInfos(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, List<FileInfo>> getFileInfos(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] fetchData(BlobKey blobKey, long startIndex, long endIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlobKey createGsBlobKey(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

}
