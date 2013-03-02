package com.hector.invoice.lib;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.hector.invoice.common.InvoiceInfo;

import android.content.Context;
import android.os.Environment;

/**
 * 
 * Utility class for managing external storage.
 * 
 * @author: HaiTC3
 * @version: 1.0
 * @since: 1.0
 */
public class ExternalStorage {
	@SuppressWarnings("unused")
	private static final String TAG = "ExternalStorage";

	// Convention for external storage path used by Android 2.2.
	private static final String EXT_STORAGE_ROOT_PREFIX = "/Android/data/";
	private static final String EXT_STORAGE_ROOT_SUFFIX = "/files/";

	private static final String INT_STORAGE_DATA = "/data/data/";
	private static final String INT_STORAGE_DATABASE = "/databases/";

	private static StringBuilder sStoragePath = new StringBuilder();

	// "/mnt/sdcard/";
	public static String SDCARD_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath() + "/";

	// external storage directory
	public static String EXTERNAL_CACHE_DIR = InvoiceInfo.getInstance()
			.getAppContext().getExternalCacheDir().getAbsolutePath()
			+ "/";

	/**
	 * Likely places where we could find an external SD card mounted. This list
	 * was determined empirically, by looking at various devices. It is not
	 * known to be complete.
	 */
	private static final String ALTERNATE_SDCARD_MOUNTS[] = { "/emmc", // Internal
																		// storage
																		// on
																		// Droid
																		// Incredible,
																		// Nook
																		// Color/CyanogenMod,
																		// some
																		// other
																		// devices
			"/sdcard/ext_sd", // Newer (2011) HTC devices (Flyer, Rezound)
			"/sdcard-ext", // Some Motorola devices (RAZR)
			"/sdcard/sd", // Older Samsung Galaxy S (Captivate)
			"/sdcard/sdcard" // Archos tablets
	};

	// thu muc VINAMILK ngoai SDCard
	public static final String INVOICE = SDCARD_PATH + "INVOICE/";
	// Thu muc chua anh chup hinh
	public static final String INVOICE_TAKEN_PHOTO_FOLDER = "TAKEN_PHOTOS";
	// thu muc file buffer dong bo du lieu
	public static final String INVOICE_SYNDATA_FOLDER = "SYN_DATA";
	// thu muc database trong cache VNM
	public static final String INVOICE_DATABASE_FOLDER = EXTERNAL_CACHE_DIR
			+ "DATABASE";

	/**
	 * 
	 * Return a File object containing the folder to use for storing data on the
	 * external SD card. Falls back to the internal application cache if SD is
	 * not available or writable.
	 * 
	 * @param context
	 * @param dirName
	 *            name of sub-folder within the SD card root.
	 * @return
	 * @return: File
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 10, 2012
	 */
	public static File getSDCacheDir(Context context, String dirName) {
		File cacheDir = null;

		// Check to see if SD Card is mounted and read/write accessible
		if (android.os.Environment.MEDIA_MOUNTED.equals(android.os.Environment
				.getExternalStorageState())) {
			// Get the directory on the SD card to store content
			// Attempt to use getExternalFilesDir() if we are on Android 2.2 or
			// newer
			// Data stored in this location will auto-delete with app uninstall
			Method getExternalFilesDirMethod = null;
			try {
				getExternalFilesDirMethod = Context.class.getMethod(
						"getExternalFilesDir", String.class);
				cacheDir = (File) getExternalFilesDirMethod.invoke(context,
						dirName);
			} catch (NoSuchMethodException e) {
				// Android 2.1 and earlier - use old APIs
				cacheDir = buildCacheDirPath(context,
						android.os.Environment.getExternalStorageDirectory(),
						dirName);
			} catch (IllegalArgumentException e) {
				cacheDir = buildCacheDirPath(context,
						android.os.Environment.getExternalStorageDirectory(),
						dirName);
			} catch (IllegalAccessException e) {
				cacheDir = buildCacheDirPath(context,
						android.os.Environment.getExternalStorageDirectory(),
						dirName);
			} catch (InvocationTargetException e) {
				cacheDir = buildCacheDirPath(context,
						android.os.Environment.getExternalStorageDirectory(),
						dirName);
			}
		}

		if (cacheDir == null) {
			// Attempting to find the default external storage was a failure.
			// Look for another suitable external filesystem where we can store
			// our crap
			for (int i = 0; i < ALTERNATE_SDCARD_MOUNTS.length; i++) {
				File alternateDir = new File(ALTERNATE_SDCARD_MOUNTS[i]);
				if (alternateDir.exists() && alternateDir.isDirectory()
						&& alternateDir.canRead() && alternateDir.canWrite()) {
					cacheDir = buildCacheDirPath(context, alternateDir, dirName);
					break;
				}
			}
		}

		// Attempt to create folder on external storage if it does not exist
		if (cacheDir != null && !cacheDir.exists()) {
			if (!cacheDir.mkdirs()) {
				cacheDir = null; // Failed to create folder
			}
		}

		// Fall back on internal cache as a last resort
		if (cacheDir == null) {
			cacheDir = new File(context.getCacheDir() + File.separator
					+ dirName);
			cacheDir.mkdirs();
		}

		return cacheDir;
	}

	/**
	 * 
	 * Clear files from SD cache.
	 * 
	 * @author: HaiTC3
	 * @param context
	 * @param dirName
	 * @return: void
	 * @throws:
	 * @since: Mar 2, 2013
	 */
	public static void clearSDCache(Context context, String dirName) {
		File cacheDir = getSDCacheDir(context, dirName);
		File[] files = cacheDir.listFiles();
		for (File f : files) {
			f.delete();
		}
		cacheDir.delete();
	}

	/**
	 * 
	 * get file database .
	 * 
	 * @author: HaiTC3
	 * @param context
	 * @return
	 * @return: File
	 * @throws:
	 * @since: Mar 2, 2013
	 */
	public static File getDatabasePath(Context context) {
		StringBuilder str = new StringBuilder();
		str.append(INT_STORAGE_DATA);
		str.append(context.getPackageName());
		str.append(INT_STORAGE_DATABASE);
		// str.append(Constants.DATABASE_NAME);
		File f = new File(str.toString());
		return f;
	}

	/**
	 * 
	 * Use older Android APIs to put data in the same relative directory
	 * location as the 2.2 API. When device upgrades to 2.2, data will
	 * auto-delete with app uninstall.
	 * 
	 * @author: HaiTC3
	 * @param context
	 * @param mountPoint
	 * @param dirName
	 * @return
	 * @return: File
	 * @throws:
	 * @since: Mar 2, 2013
	 */
	private static File buildCacheDirPath(Context context, File mountPoint,
			String dirName) {
		sStoragePath.setLength(0);
		sStoragePath.append(EXT_STORAGE_ROOT_PREFIX);
		sStoragePath.append(context.getPackageName());
		sStoragePath.append(EXT_STORAGE_ROOT_SUFFIX);
		sStoragePath.append(dirName);
		return new File(mountPoint, sStoragePath.toString());
	}

	/**
	 * 
	 * get path contains file db download
	 * 
	 * @author: HaiTC3
	 * @param context
	 * @return
	 * @return: File
	 * @throws:
	 * @since: Mar 2, 2013
	 */
	public static File getFileDBPath(Context context) {
		// if (MemoryUtils.isSdPresent()){
		// return new File(SDCARD_PATH );
		// }else {
		// return getDatabasePath(context);
		// }
		File path = new File(INVOICE_DATABASE_FOLDER);
		if (!path.exists()) {
			path.mkdir();
		}
		return path;
	}

	/**
	 * 
	 * get path folder contains picture file need upload
	 * 
	 * @author: HaiTC3
	 * @param context
	 * @return
	 * @return: File
	 * @throws:
	 * @since: Mar 2, 2013
	 */
	public static File getTakenPhotoPath(Context context) {
		final File root = new File(INVOICE);
		if (!root.exists()) {
			root.mkdir();
		}
		final File path = new File(INVOICE + INVOICE_TAKEN_PHOTO_FOLDER);
		if (!path.exists()) {
			path.mkdir();
		}
		return path;

	}

	/**
	 * 
	 * create path file syndata on sdcard
	 * 
	 * @author: HaiTC3
	 * @param context
	 * @return
	 * @return: File
	 * @throws:
	 */
	public static File getPathSynData() {
		final File root = new File(INVOICE);
		if (!root.exists()) {
			root.mkdir();
		}
		final File path = new File(INVOICE + INVOICE_SYNDATA_FOLDER);
		if (!path.exists()) {
			path.mkdir();
		}
		return path;
	}
}
