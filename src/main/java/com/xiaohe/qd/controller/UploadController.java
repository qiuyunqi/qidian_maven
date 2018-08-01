package com.xiaohe.qd.controller;

import java.io.File;
import java.io.InputStream;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.ServiceException;
import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.OSSErrorCode;
import com.aliyun.openservices.oss.OSSException;
import com.aliyun.openservices.oss.model.CannedAccessControlList;
import com.aliyun.openservices.oss.model.ObjectMetadata;
import com.xiaohe.qd.util.Constants;
import com.xiaohe.qd.util.ImageUtil;
import com.xiaohe.qd.util.Property;

@Controller
@RequestMapping("/upload")
@Scope("prototype")
public class UploadController extends BaseController {

	public final static String savePath = Constants.DIR_TEMP;
	public final static Double width = 800.0;
	public final static Double height = 800.0;
	public final static Boolean largePic = false;
	public final static String[] types = new String[] { ".bmp", ".png", ".gif", ".jpeg", ".pjpeg", ".jpg" };
	public final static String[] fileType = new String[] { ".exe", ".jar", ".dll", ".jsp", ".class", ".sh", ".bat" };
	public final static long maxSize = 10000000;
	public final static long maxFileSize = 20000000;

	@RequestMapping(value = "/img.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String img(HttpServletRequest request, HttpServletResponse response) {
		MultipartRequest multipartRequest = (MultipartRequest) request;
		MultipartFile imgFile = multipartRequest.getFile("imgFile");
		String imgFileFileName = imgFile.getOriginalFilename();
		try {
			long size = imgFile.getSize();
			if (size > maxSize) {
				alert(response, 1, "图片应小于10M!");
				return null;
			}
			boolean admit = true;
			String fileType = ".jpg";
			for (int i = 0; i < types.length; i++) {
				if (types[i].equalsIgnoreCase(imgFileFileName.substring(imgFileFileName.lastIndexOf(".")))) {
					admit = false;
					if (types[i].endsWith(".gif"))
						fileType = ".gif";
					if (types[i].endsWith(".png"))
						fileType = ".png";
				}
			}
			if (admit) {
				alert(response, 1, "上传图片类型不正确!");
				return null;
			}
			String fileName = (System.currentTimeMillis() + (new Random(999999).nextLong())) + fileType;
			try {
				if (null == imgFile || size < 0) // 文件不存在时
					return null;
				String bucketName = "hhr360oss";
				// 使用默认的OSS服务器地址创建OSSClient对象。
				OSSClient client = new OSSClient(Property.getProperty("OSS_ACCESS_ID"), Property.getProperty("OSS_ACCESS_KEY"));
				ensureBucket(client, bucketName);
				ObjectMetadata objectMeta = new ObjectMetadata();
				objectMeta.setContentLength(imgFile.getSize());
				InputStream is = imgFile.getInputStream();
				client.putObject(bucketName, fileName, is, objectMeta);
				String saveUrl = Property.getProperty("OSS_URL") + fileName;
				JSONObject obj = new JSONObject();
				obj.put("fileName", imgFileFileName);
				obj.put("fileSize", (int) size / 1024);
				obj.put("error", 0);
				obj.put("url", saveUrl);
				obj.put("saveDir", saveUrl);
				writeJson(response, obj);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				alert(response, 1, "上传图片异常!");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void ensureBucket(OSSClient client, String bucketName) throws OSSException, ClientException {

		try {
			// 创建bucket
			client.createBucket(bucketName);
			client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
		} catch (ServiceException e) {
			if (!OSSErrorCode.BUCKES_ALREADY_EXISTS.equals(e.getErrorCode())) {
				// 如果Bucket已经存在，则忽略
				throw e;
			}
		}
	}

	/**
	 * 图片压缩
	 * 
	 * @param picFrom
	 *            待压缩的图片保存路径
	 * @param picTo
	 *            压缩后的图片保存路径
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @throws Exception
	 */
	public static void comPress(String picFrom, String picTo, double width, double height) throws Exception {
		ImageUtil.resize(picFrom, picTo, (int) width, (int) height);
	}

	/**
	 * 图片压缩 作者：漆传涛
	 * 
	 * @param savePath
	 *            文件保存的真实路径
	 * @param oldFile
	 *            压缩文件
	 * @param width
	 *            文件压缩宽
	 * @param height
	 *            文件压缩高
	 * @throws Exception
	 */
	public void comPress(String savePath, File oldFile, double width, double height, boolean largePic) {
		try {
			if (!oldFile.exists()) // 文件不存在时
				return;
			String picFrom = oldFile.getAbsolutePath();
			int quality = (int) ((largePic ? 200000d : 80000d) / oldFile.length() * 100);
			if (quality >= 100) {
				quality = 0;
			} else {
				if (quality < 70) {
					quality = 50;
				}
			}
			ImageUtil.resize(picFrom, savePath, (int) width, (int) height, quality);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void alert(HttpServletResponse response, int error, String msg) {
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			JSONObject array = new JSONObject();
			array.put("error", error);
			array.put("message", msg);
			response.getWriter().write(array.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * private static void saveFile(File file, String path) throws Exception { BufferedInputStream bis = null; BufferedOutputStream bos = null; bis = new BufferedInputStream(new
	 * FileInputStream(file)); bos = new BufferedOutputStream(new FileOutputStream(path)); try { byte[] buf = new byte[1024 * 1024]; int len = 0; while (((len = bis.read(buf)) != -1)) { bos.write(buf,
	 * 0, len); } } catch (Exception e) { throw e; } finally { bis.close(); bos.close(); } }
	 */

	// ///////////////////////////////////////**************************************///////////////////////
}
