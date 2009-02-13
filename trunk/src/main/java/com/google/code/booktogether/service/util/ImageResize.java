package com.google.code.booktogether.service.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.swing.ImageIcon;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageResize {

	//이미지저장(축소시켜서)
	public static void createImageResize(InputStream source,String target,int targetW) throws Exception{	//파일경로,저장위치,크기
		
		byte[] image=new byte[source.available()];
		
		source.read(image);

		Image imgSource=new ImageIcon(image).getImage();

		int oldW=imgSource.getWidth(null);
		int oldH=imgSource.getHeight(null);

		int newW=targetW;
		int newH=(targetW*oldH)/oldW;

		Image imgTarget=imgSource.getScaledInstance(newW,newH, Image.SCALE_SMOOTH);

		int pixels[]= new int[newW*newH];

		PixelGrabber pg=new PixelGrabber(imgTarget,0,0,newW,newH,pixels,0,newW);
		pg.grabPixels();

		BufferedImage bi=new BufferedImage(newW,newH,BufferedImage.TYPE_INT_BGR);
		bi.setRGB(0, 0, newW, newH, pixels, 0, newW);

		FileOutputStream fos=new FileOutputStream(target);

		JPEGImageEncoder jpeg= JPEGCodec.createJPEGEncoder(fos);

		JPEGEncodeParam jep=jpeg.getDefaultJPEGEncodeParam(bi);
		jep.setQuality(1, false);
		jpeg.encode(bi,jep);

		fos.close();
	}
	
}
