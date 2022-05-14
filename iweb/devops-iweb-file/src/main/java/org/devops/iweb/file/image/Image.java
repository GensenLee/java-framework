package org.devops.iweb.file.image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class Image
{
  private static final Logger LOGGER = Logger.getLogger(Image.class.getName());
  public static final float DEFAULT_JPEG_QUALITY = 0.85F;
  private BufferedImage image;

  public void release()
  {
    this.image = null;
  }

  public Image(BufferedImage image) {
    this.image = image;
  }
  
  public Image(File file) {
	  try{
	    this.image = toBufferedImage(Image.read(file));
	  }catch(Exception e){
		  throw new RuntimeException(e);
	  }
  }

  public Image(byte[] bytes) {
    this.image = toBufferedImage(bytes);
  }

  public BufferedImage getBufferedImage() {
    return this.image;
  }

  public int getWidth() {
    if (this.image == null)
      return 0;
    return this.image.getWidth();
  }

  public int getHeight() {
    if (this.image == null)
      return 0;
    return this.image.getHeight();
  }

  public static byte[] read(File file) throws IOException {
    FileInputStream in = null;
    try {
      in = new FileInputStream(file);
      return read(in);
    } catch (IOException e) {
      throw e;
    } finally {
      if (in != null)
        in.close();
    }
  }

  public static byte[] read(URL url) throws IOException
  {
    InputStream in = null;
    try {
      in = url.openStream();
      return read(in);
    } catch (IOException e) {
      throw e;
    } finally {
      if (in != null)
        in.close();
    }
  }

  public static byte[] read(InputStream in) throws IOException
  {
    try {
      return IOUtils.toByteArray(in);
    }
    catch (IOException e) {
      throw e;
    }
  }

  public static String getFormatName(byte[] imgData) {
    ImageInputStream input = null;
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imgData);
    try {
      input = ImageIO.createImageInputStream(byteArrayInputStream);
      Iterator it = ImageIO.getImageReaders(input);
      if (it.hasNext()) {
        ImageReader reader = (ImageReader)it.next();
        return reader.getFormatName();
      }
    } catch (Exception localException2) {
    } finally {
      try {
        if (input != null)
          input.close();
      }
      catch (Exception localException5) {
      }
      try {
        byteArrayInputStream.close();
      }
      catch (Exception localException6)
      {
      }
    }
    try
    {
      if (input != null)
        input.close();
    }
    catch (Exception localException7) {
    }
    try {
      byteArrayInputStream.close();
    }
    catch (Exception localException8) {
    }
    return null;
  }

  public static Image parse(File file) throws IOException {
    return new Image(read(file));
  }

  public static Image parse(URL url) throws IOException {
    InputStream in = null;
    try {
      in = url.openStream();
      return new Image(read(url.openStream()));
    } catch (IOException e) {
      throw e;
    } finally {
      if (in != null) {
        in.close();
        in = null;
      }
    }
  }

  public boolean cutTo(int tarWidth, int tarHeight)
  {
    if (this.image == null) {
      throw new RuntimeException("image file not be load.please execute 'load' function agin.");
    }

    int iSrcWidth = getWidth();
    int iSrcHeight = getHeight();

    if ((iSrcWidth < tarWidth) && (iSrcHeight < tarHeight)) {
      LOGGER.warning("source image size too small.");
      return true;
    }

    int iDstLeft = (iSrcWidth - tarWidth) / 2;
    int iDstTop = (iSrcHeight - tarHeight) / 2;

    this.image = this.image.getSubimage(iDstLeft, iDstTop, tarWidth, tarHeight);

    return true;
  }

  public boolean zoomTo(int tarWidth, int tarHeight,boolean alpha)
  {
	  
    int type = 2;
    if (!alpha)
      type = 1;
    
    BufferedImage tagImage = new BufferedImage(tarWidth, tarHeight, type);

    java.awt.Image zoomImage = this.image.getScaledInstance(tarWidth, tarHeight, BufferedImage.SCALE_SMOOTH);
    Graphics g = tagImage.getGraphics();

    if (!alpha) {
      Color c = g.getColor();
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, tarWidth, tarHeight);
      g.setColor(c);
    }

    g.drawImage(zoomImage, 0, 0, null);
    g.dispose();

    this.image = tagImage;

    return true;
  }

  public byte[] getBytes(String format) {
    return toBytes(this.image, format);
  }

  public byte[] getJPEGBytes(float quality) {
    return toJPEGBytes(this.image, quality);
  }

  public void drawMarkImage(byte[] markBytes, int right, int bottom, boolean alpha) {
    BufferedImage markImage = toBufferedImage(markBytes);
    drawMarkImage(markImage, right, bottom, alpha);
  }

  public void drawMarkImage(BufferedImage markImage, int right, int bottom, boolean alpha)
  {
    int wideth = this.image.getWidth();
    int height = this.image.getHeight();

    int type = 2;
    if (!alpha)
      type = 1;
    BufferedImage tagImage = new BufferedImage(wideth, height, type);

    Graphics g = tagImage.createGraphics();

    if (!alpha) {
      Color c = g.getColor();
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, wideth, height);
      g.setColor(c);
    }

    g.drawImage(this.image, 0, 0, wideth, height, null);

    int markWidth = markImage.getWidth();
    int markHeight = markImage.getHeight();
    g.drawImage(markImage, wideth - markWidth - right, height - markHeight - bottom, markWidth, markHeight, null);
    g.dispose();
    this.image = tagImage;
  }

  public static BufferedImage toBufferedImage(byte[] bytes)
  {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
    try {
      BufferedImage image = ImageIO.read(byteArrayInputStream);
      return image;
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    } finally {
      try {
        byteArrayInputStream.close();
      }
      catch (Exception localException1)
      {
      }
    }
  }

  public static byte[] toBytes(BufferedImage image, String format)
  {
    if (("JPEG".equals(format.toUpperCase())) || ("JPG".equals(format.toUpperCase()))) {
      return toJPEGBytes(image, 0.85F);
    }

    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    try {
      ImageIO.write(image, format, bos);
      return bos.toByteArray();
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    } finally {
      try {
        bos.close();
      }
      catch (IOException localIOException2)
      {
      }
    }
  }

  public static byte[] toJPEGBytes(BufferedImage image, float quality)
  {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    byte[] b = null;
    try {
//      JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
//      JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(image);
//      param.setQuality(quality, false);
//      encoder.setJPEGEncodeParam(param);
//      encoder.encode(image);
    	ImageIO.write(image, "jpg", bos);
    	bos.flush();
        b = bos.toByteArray();
        bos.close();
        image.flush();
        image = null;
      return b;
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    } finally {
      try {
        bos.close();
      }
      catch (IOException localIOException2)
      {
      }
    }
  }

  public static byte[] fixWidth(byte[] data, int width, String format)
  {
    try
    {
      Image image = new Image(data);

      int imgW = image.getWidth();
      int imgH = image.getHeight();

      double wRate = imgW / width;

      int height = (int)(imgH / wRate);

      boolean alpha = false;
      if ("PNG".equalsIgnoreCase(format)) {
        alpha = true;
      }

      image.zoomTo(width, height, alpha);
      data = image.getBytes(format);
    }
    catch (Exception e) {
    	e.printStackTrace();
    }
    return data;
  }

  public static byte[] fixSize(byte[] data, int width, int height, String format)
  {
    try
    {
      Image image = new Image(data);

      int imgW = image.getWidth();
      int imgH = image.getHeight();

      if ((imgW == width) && (imgH == height)) {
        return data;
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

      boolean alpha = false;
      if ("PNG".equalsIgnoreCase(format)) {
        alpha = true;
      }

      image.zoomTo(zoomW, zoomH, alpha);
      image.cutTo(width, height);
      data = image.getBytes(format);
    }
    catch (Exception e) {
    	e.printStackTrace();
    }
    return data;
  }
}