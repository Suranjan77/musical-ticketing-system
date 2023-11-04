package org.musical.ticketing.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author suranjanpoudel
 */
public class ImageUtils {

  private static Logger log = LoggerFactory.getLogger(ImageUtils.class);

  public static BufferedImage getImage(String imageName) {
    File file = new File(PathUtils.ROOT_PATH + "/images/" + imageName);

    try {
      return ImageIO.read(file);
    } catch (IOException ex) {
      log.error("Exception getting image {}", imageName, ex);
    }

    return null;
  }
}
