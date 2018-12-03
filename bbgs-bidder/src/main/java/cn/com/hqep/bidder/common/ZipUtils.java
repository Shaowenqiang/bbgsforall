package cn.com.hqep.bidder.common;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

import java.io.File;

/**
 * 功能： 1 、实现把指定文件夹下的所有文件压缩为指定文件夹下指定 zip 文件 2 、实现把指定文件夹下的 zip 文件解压到指定目录下
 */

public class ZipUtils {

	public static void main(String[] args) {

	}

	private static final Project DEFAULT_PROJECT = new Project();

	/**
	 * @note 待解压文件不存在时会抛异常
	 * @param orgin
	 *            压缩文件完整路径(含后缀.zip)
	 * @param dest
	 *            目标文件夹路径
	 * */
	public static void unzip(String orgin, String dest) throws Exception {
		unzip(new File(orgin), new File(dest));
	}

	/**
	 * @note 待解压文件不存在时会抛异常
	 * @param orgin
	 *            压缩文件
	 * @param dest
	 *            目标文件夹
	 * */
	public static void unzip(File orgin, File dest) throws Exception {
		Expand expand = new Expand();
		expand.setProject(DEFAULT_PROJECT);
		expand.setSrc(orgin);
		expand.setDest(dest);
		expand.setEncoding("utf-8");
		expand.execute();
	}

	/**
	 * @note 待压缩文件不存在时会抛异常, 若存放压缩文件的目录下的存在同名压缩文件则会报错
	 * @param orgin
	 *            待压缩文件完整路径(包含文件拓展名)
	 * @param dest
	 *            生成的压缩文件完整路径(包含.zip拓展名)
	 * */
	public static void zip(String orgin, String dest) throws Exception {
		zip(new File(orgin), new File(dest));
	}

	/**
	 * @note 待压缩文件不存在时会抛异常, 若存放压缩文件的目录下的存在同名压缩文件则会报错
	 * @param orgin
	 *            待压缩文件
	 * @param dest
	 *            生成的压缩文件
	 * */
	public static void zip(File orgin, File dest) throws Exception {
		if(orgin.exists()==false){
			throw new Exception("文件"+orgin.getPath()+"不存在!");
		}
		Zip zip = new Zip();
		zip.setProject(DEFAULT_PROJECT);
		zip.setDestFile(dest);

		FileSet fs = new FileSet();
		fs.setProject(DEFAULT_PROJECT);
		if (orgin.isDirectory()) {
			fs.setDir(orgin);
		} else {
			fs.setFile(orgin);
		}
		zip.addFileset(fs);
		zip.setEncoding("utf-8");
		zip.execute();
	}

	/**
	 * @note 待压缩文件不存在时会抛异常, 若存放压缩文件的目录下的存在同名压缩文件则会报错
	 * @param orgin
	 *            待压缩文件完整路径(包含文件拓展名)
	 * @param dest
	 *            生成的压缩文件完整路径(包含.zip拓展名)
	 * @param delBefore
	 *            先删除已经存在的存放压缩文件的目录下的同名压缩文件
	 * */
	public static void zip(String orgin, String dest, boolean delBefore)
			throws Exception {
		File destFile = new File(dest);
		if (delBefore && destFile.exists()) {
			FileUtils.forceDelete(destFile);
		}
		zip(new File(orgin), destFile);
	}
}
