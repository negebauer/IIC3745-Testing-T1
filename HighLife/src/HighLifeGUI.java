import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;

import javax.swing.JPanel;

public class HighLifeGUI extends JPanel {

final public static long serialVersionUID = (long) 1.0;

// FIXME: change dimensions to fit the board's dimensions
private BufferedImage currentDisplayedBoard;
private ComponentColorModel icm = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_GRAY), false, false, ColorModel.OPAQUE, DataBuffer.TYPE_BYTE);

public HighLifeGUI(int boardWidth, int boardHeight) {
  currentDisplayedBoard = new BufferedImage(boardWidth, boardHeight, BufferedImage.TYPE_BYTE_BINARY);
}

public void setDisplayData(boolean[][] board) {

  int w = board.length;
  int h = board[0].length;

  int[] data = new int[w*h];
  for(int i=0; i<w; i++)
    for(int j=0; j<h; j++)
      data[i*h + j] = board[i][j] ? 255 : 0;

  WritableRaster raster = icm.createCompatibleWritableRaster(w, h);
  raster.setPixels(0, 0, w, h, data);

  this.currentDisplayedBoard.setData(raster);

  this.repaint();
}

public void paint(Graphics g) {
  g.drawImage(currentDisplayedBoard, 0, 0, this.getWidth(), this.getHeight(), this);
}
}
