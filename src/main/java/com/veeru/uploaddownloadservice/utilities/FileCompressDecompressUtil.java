package com.veeru.uploaddownloadservice.utilities;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * @author Virupaksha K
 *
 */
public class FileCompressDecompressUtil {

	public static byte[] compressed(byte[] data) {
		ByteArrayOutputStream baos = null;
		Deflater dfl = new Deflater();
		dfl.setLevel(Deflater.BEST_COMPRESSION);
		dfl.setInput(data);
		dfl.finish();
		baos = new ByteArrayOutputStream();
		byte[] tmp = new byte[4 * 1024];
		try {
			while (!dfl.finished()) {
				int size = dfl.deflate(tmp);
				baos.write(tmp, 0, size);
			}
		} catch (Exception ex) {

		} finally {
			try {
				if (baos != null)
					baos.close();
			} catch (Exception ex) {
			}
		}

		return baos.toByteArray();

	}

	public static byte[] decompressed(byte[] data) {
		ByteArrayOutputStream baos = null;
		Inflater iflr = new Inflater();
		iflr.setInput(data);
		baos = new ByteArrayOutputStream();
		byte[] tmp = new byte[4 * 1024];
		try {
			while (!iflr.finished()) {
				int size = iflr.inflate(tmp);
				baos.write(tmp, 0, size);
			}
		} catch (Exception ex) {

		} finally {
			try {
				if (baos != null)
					baos.close();
			} catch (Exception ex) {
			}
		}

		return baos.toByteArray();
	}
}
