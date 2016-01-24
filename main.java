import java.awt.BorderLayout;
import java.awt.EventQueue;
//
import viewer.FLogin;
import viewer.FMainMenue;

public class main
{
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          FLogin f_login_ = new FLogin();
          f_login_.setLocationRelativeTo(null);
          f_login_.setVisible(true);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }
}
