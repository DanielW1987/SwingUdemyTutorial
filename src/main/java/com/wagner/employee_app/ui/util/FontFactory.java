package com.wagner.employee_app.ui.util;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class FontFactory {

  public static Font getVikingFont() {
    URL fontURL = FontFactory.class.getClassLoader().getResource("fonts/Viking-Normal.otf");

    Font font = null;
    try {
      font = Font.createFont(Font.TRUETYPE_FONT, fontURL.openStream());
    } catch (IOException | FontFormatException e) {
      e.printStackTrace();
    }

    return font.deriveFont(Font.PLAIN, 15);
  }
}