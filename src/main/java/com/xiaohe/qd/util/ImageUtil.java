package com.xiaohe.qd.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;

/**
 * 
 * @author 充满智慧的威哥
 * 
 */
public class ImageUtil {

	private static Log log = LogFactory.getLog(ImageUtil.class);
	public static String imageMagickPath = null;

	static {
		imageMagickPath = "C:\\Program Files (x86)\\ImageMagick-6.7.9-Q8";
	}

	public final static int PHOTO_RATIO = 800; // 缩放图片系数

	/**
	 * 剪切图片
	 * 
	 * @param x
	 *            坐标x
	 * @param y
	 *            坐标y
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @param oldPath
	 *            相对路径
	 * @return 返回新的保存路径
	 * @throws Exception
	 *             描述： 按照坐标要求裁剪图片。
	 */
	public final static String cutImage(HttpServletRequest request, int x, int y, int width, int height,
			String oldPath) throws Exception {
		if (oldPath == null)
			return null;
		String newPath = oldPath.replace(Constants.DIR_TEMP, Constants.DIR_PIC);
		String realPath = request.getServletContext().getRealPath(oldPath);
		String newRealPath = request.getServletContext().getRealPath(newPath);
		IMOperation op = new IMOperation();
		op.addImage(realPath);
		op.crop(width, height, x, y);
		op.addImage(newRealPath);
		runOp(op);
		return newPath;
	}

	/**
	 * 计算字的长度
	 * 
	 * @param text
	 * @return
	 */
	public static int getLength(String text) {
		int length = 0;
		for (int i = 0; i < text.length(); i++) {
			if ((text.charAt(i) + "").getBytes().length > 1) {
				length += 2;
			} else {
				length += 1;
			}
		}
		return length / 2;
	}

	/**
	 * 图片压缩
	 * 
	 * @param picFrom
	 * @param picTo
	 * @param widthdist
	 * @param heightdist
	 */
	public static void resize(String picFrom, String picTo, int widthdist,
			int heightdist) {
		resize(picFrom, picTo, widthdist, heightdist, 0);
	}

	/**
	 * 图片压缩
	 * 
	 * @param picFrom
	 * @param picTo
	 * @param widthdist
	 * @param heightdist
	 */
	public static void resize(String picFrom, String picTo, int widthdist,
			int heightdist, int quality) {
		try {
			BufferedImage bi = ImageIO.read(new File(picFrom));
			int new_w = bi.getWidth();
			int new_h = bi.getHeight();
			double rate = 1;
			if (new_w > widthdist) {
				rate = new_w / widthdist;
				new_h = (int)(new_h / rate);
				new_w = widthdist;
			}
			if (new_h > heightdist) {
				rate = new_h / heightdist;
				new_h = heightdist;
				new_w = (int)(new_w / rate);
			}
			resizeImage(picFrom, picTo, new_w, new_h, quality);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage());
		} catch (Throwable e) {
			log.error(e.getMessage());
		}
	}

	public static JSONObject getImgData(String picFrom) {
		return getImgWidthHeight(picFrom);
	}

	public static void resize(String picFrom, String picTo, int ratio)
			throws Exception {
		BufferedImage bi = ImageIO.read(new File(picFrom));
		// 原始图片属性
		int srcWidth = bi.getWidth();
		int srcHeight = bi.getHeight();
		// 生成图片属性
		int newWidth = srcWidth;
		int newHeight = srcHeight;
		// 如果超出最大宽或高就压缩
		if (srcWidth > ratio || newHeight > ratio) {
			// 生成图片width, height计算
			if (srcWidth >= srcHeight) {
				if (srcWidth < ratio) {
					return;
				}
				newWidth = ratio;
				newHeight = (ratio * srcHeight / srcWidth);
			} else {
				if (srcHeight < ratio) {
					return;
				}
				newHeight = ratio;
				newWidth = (ratio * srcWidth / srcHeight);
			}
		}
		resize(picFrom, picTo, newWidth, newHeight);
	}

	/**
	 * 方法描述: 验证文件类型
	 * 
	 * @param filename
	 * @return
	 */
	public static boolean validateImage(String filename) {
		// 定义可上传文件的 类型
		List<String> fileTypes = new ArrayList<String>();

		// 图片
		fileTypes.add("jpg");
		fileTypes.add("jpeg");
		fileTypes.add("bmp");
		fileTypes.add("gif");
		fileTypes.add("png");

		// 得到文件尾数 并 进行小写转换
		String postfix = filename.substring(filename.lastIndexOf(".") + 1)
				.toLowerCase();
		return fileTypes.contains(postfix) ? true : false;
	}

	public static void resize(String picFrom, String picTo) throws Exception {
		resize(picFrom, picTo, PHOTO_RATIO);
	}

	public static void main(String[] args) throws Exception {
		// resizeImg("G:/xiaoguo/46留言反馈/此方.jpg", "G:/xiaoguo/46留言反馈/此方2.jpg",
		// 200, 200);
		resize("G:/xiaoguo/46留言反馈/此方.jpg", "G:/xiaoguo/46留言反馈/此方2.jpg", 200,
				200);
		// String img = "/temp/yancheng.jpg";
		// String imgtemp = img.substring(img.lastIndexOf("/")+1,
		// img.lastIndexOf("."));
		// System.out.println(imgtemp);
	}

	public static JSONObject getImgWidthHeight(String picFrom) {
		try {
			JSONObject obj = new JSONObject();
			BufferedImage bi = ImageIO.read(new File(picFrom));
			// 原始图片属性
			int srcWidth = bi.getWidth();
			int srcHeight = bi.getHeight();
			obj.put("width", srcWidth);
			obj.put("height", srcHeight);
			return obj;
		} catch (RuntimeException e) {
			log.error(e.getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage());
		} catch (Throwable e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 根据尺寸缩放图片
	 * 
	 * @param width
	 *            缩放后的图片宽度
	 * @param height
	 *            缩放后的图片高度
	 * @param srcPath
	 *            源图片路径
	 * @param newPath
	 *            缩放后图片的路径
	 */
	public static void resizeImage(String srcPath, String newPath, int ratio)
			throws Exception {
		BufferedImage bi = ImageIO.read(new File(srcPath));
		// 原始图片属性
		int srcWidth = bi.getWidth();
		int srcHeight = bi.getHeight();
		// 生成图片属性
		int newWidth = srcWidth;
		int newHeight = srcHeight;
		// 如果超出最大宽或高就压缩
		if (srcWidth > ratio || newHeight > ratio) {
			// 生成图片width, height计算
			if (srcWidth >= srcHeight) {
				if (srcWidth < ratio) {
					return;
				}
				newWidth = ratio;
				newHeight = (ratio * srcHeight / srcWidth);
			} else {
				if (srcHeight < ratio) {
					return;
				}
				newHeight = ratio;
				newWidth = (ratio * srcWidth / srcHeight);
			}
		}
		resizeImage(srcPath, newPath, newWidth, newHeight,0);
	}

	/**
	 * 根据尺寸缩放图片
	 * 
	 * @param width
	 *            缩放后的图片宽度
	 * @param height
	 *            缩放后的图片高度
	 * @param srcPath
	 *            源图片路径
	 * @param newPath
	 *            缩放后图片的路径
	 */
	public static void resizeImage(String srcPath, String newPath, int width,
			int height,int quality) throws Exception {
		IMOperation op = new IMOperation();
		op.addImage(srcPath);
		op.resize(width, height);
		if(quality>0&&quality<100){
			op.quality(quality*1d);
		}
		op.addImage(newPath);
		runOp(op);
	}
	
	public static void resizeImage(String srcPath, String newPath, int width,
			int height) throws Exception {
		resize(srcPath, newPath, width, height, 0);
	}
	
	public static void runOp(IMOperation op) throws Exception {
		ConvertCmd convert = new ConvertCmd();
//		convert.setSearchPath(imageMagickPath);
		convert.run(op);
	}
}