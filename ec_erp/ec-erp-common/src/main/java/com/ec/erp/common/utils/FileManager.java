package com.ec.erp.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileManager {
	private static FileManager manager;
	
	//单例模式
	private FileManager() {
		//判断图片目录是否存在
		File file = new File("ItemImg");
		if  (!file .exists()  && !file.isDirectory())      
		{       
		    file .mkdir();
		}
	}
	
	static public FileManager getFileManager() {
		if(manager == null) {
			manager = new FileManager();
		}
		return manager;			
	}
	
	/**
	 * 依据促销ID修改促销信息
	 * @param file    上传的文件
	 * @param strName 文件的新名字
	 * @return 文件URL
	 */
	static public String saveFile(File file,String strName ) {
		if(!file.exists())
			return null;
		
		String strExt = getFileExtension(file);
		File newFile = new File("ItemImg/" + strName);
		if(!newFile.exists()) {
			//保存文件
			try {
				fileChannelCopy(file, newFile);
				return "ItemImg/" + strName;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
			
		return null;
	}
	
	private static String getFileExtension(File file) {
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		}

		return "";

		
	}
	
	 /**
	    * 使用文件通道的方式复制文件
	    * @param srcFile 源文件
	    * @param dstFile 复制到的新文件
	    */
	    public static void fileChannelCopy(File srcFile, File dstFile) throws IOException {
	        FileInputStream fi = null;
	        FileOutputStream fo = null;
	        FileChannel in = null;
	        FileChannel out = null;

	        try {
	            fi = new FileInputStream(srcFile);
	            fo = new FileOutputStream(dstFile);
	            in = fi.getChannel();//得到对应的文件通道
	            out = fo.getChannel();//得到对应的文件通道
	            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                fi.close();
	                in.close();
	                fo.close();
	                out.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}
