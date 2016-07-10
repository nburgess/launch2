import java.applet.*;
import java.awt.*;

public class basicD extends Applet
{
   private String param6;
   public void paint (Graphics g)
      {
      	    param6 = getParameter("param6");
            g.drawString ("Ne", 25, 50);
	           g.drawString (param6,50,50);
       }
}
