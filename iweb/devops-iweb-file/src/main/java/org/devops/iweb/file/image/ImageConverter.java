package org.devops.iweb.file.image;

import java.awt.image.BufferedImage;

public class ImageConverter
{
  private byte[] input;

  public ImageConverter(byte[] input)
  {
    this.input = input;
  }

  public byte[] convert(int width, int heigth) {
    return makeThumbnail(0.9f, width, heigth);
  }

  private byte[] makeThumbnail(float quality, int width, int height) {
    if ((width <= 0) && (height <= 0)) {
      return null;
    }

    BufferedImage bi = Image.toBufferedImage(this.input);
    Image image = new Image(bi);

    if (width <= 0) {
      width = (int)(image.getWidth() * height * 1.0D / image.getHeight());
      image.zoomTo(width, height, false);
    } else if (height <= 0) {
      height = (int)(image.getHeight() * width * 1.0D / image.getWidth());
      image.zoomTo(width, height, false);
    } else {
      fixSize(image, width, height);
    }

    return image.getJPEGBytes(quality);
  }

  private void fixSize(Image image, int width, int height) {
    int imgW = image.getWidth();
    int imgH = image.getHeight();

    if ((imgW == width) && (imgH == height)) {
      return;
    }

    double wRate = imgW / width;
    double hRate = imgH / height;

    int zoomW = width;
    int zoomH = height;

    if (wRate < hRate) {
      zoomW = (int)(imgW / wRate) + 1;
      zoomH = (int)(imgH / wRate) + 1;
    } else {
      zoomW = (int)(imgW / hRate) + 1;
      zoomH = (int)(imgH / hRate) + 1;
    }

    image.zoomTo(zoomW, zoomH, false);
    image.cutTo(width, height);
  }
}