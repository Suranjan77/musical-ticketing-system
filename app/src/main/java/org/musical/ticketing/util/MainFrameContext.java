package org.musical.ticketing.util;

import org.musical.ticketing.view.MainFrame;

/**
 * @author suranjanpoudel
 */
public class MainFrameContext {

  private final MainFrame mainFrame;

  private static class Helper {

    private static final MainFrameContext instance = new MainFrameContext();
  }

  public static MainFrameContext instance() {
    return MainFrameContext.Helper.instance;
  }

  private MainFrameContext() {
    this.mainFrame = new MainFrame();
  }

  public MainFrame getRootFrame() {
    return this.mainFrame;
  }
}
