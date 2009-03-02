package com.google.code.booktogether.service.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageResize {

	/**
	 * 이미지 축소 시키고 저장하기(JEPG코덱 사용)
	 * 
	 * @param source
	 *            파일원본소스
	 * @param target
	 *            저장 위치
	 * @param targetW
	 *            이미지 가로크기(크기에 맞게 세로크기자동)
	 * @throws Exception
	 */
	public static void createImageResize(InputStream source, String target,
			int targetW, int targetH) throws Exception { // 파일경로,저장위치,크기

		BufferedImage originalImage = ImageIO.read(source);

		BufferedImage thumbnailImage = new BufferedImage(targetW, targetH,
				BufferedImage.TYPE_INT_RGB);

		Graphics2D graphics2D = thumbnailImage.createGraphics();

		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		graphics2D.drawImage(originalImage, 0, 0, targetW, targetH, null);

		ImageIO.write(thumbnailImage, "jpg", new File(target));

	}

}
