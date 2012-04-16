package tloc.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.newdawn.slick.Image;

import tloc.entities.IDisplayable;
import tloc.entities.SpriteId;

/**
 * This class should go to gui resources and fetch sprites
 */
public class SpriteFactory {
	
	private static final Map<SpriteId, Image> spriteMap = new HashMap<>();
	static {
		try {
			for (SpriteId spriteId : SpriteId.values()) {
				spriteMap.put(spriteId, loadSprite(spriteId));
			}
		} catch (IOException e) {
			throw new IllegalStateException("Error loading sprite resources", e);
		}
	}
	
	public Image getSprite(IDisplayable displayable) {
		return spriteMap.get(displayable);
	}

	private static Image loadSprite(SpriteId spriteId) throws IOException {
		ClassLoader classLoader = SpriteFactory.class.getClassLoader();
		String resName = SpriteFactory.class.getPackage().getName().replace('.', '/') + "/" + spriteId.toString() + ".png";
//		String url = classLoader.getResource(resName);
		if (resName == null) {
			throw new IllegalStateException("Missing resource: " + resName);
		}
		return new Image(resName);
	}
}
