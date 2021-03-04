//104403012 �Y��3A�Ǐ�͢

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.ItemEvent;
import javax.swing.JColorChooser;

  public class SmallPainter extends JFrame {
  private JLabel statusBar = new JLabel()   ;
  private JPanel toolPanel = new JPanel();
  private JPanel paintPanel = new JPanel();
  private JRadioButton bigButton,midButton,smallButton;
  private ButtonGroup group = new ButtonGroup();
  private JCheckBox fill;
  Color color1 = new Color(1,3,3);
  Color backcolor = new Color(255,255,255);
  private JButton foreground = new JButton();
  private JButton background = new JButton();


  

  public SmallPainter() {
      this.setTitle("С����");
      this.setSize(800,800);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLayout(new BorderLayout());
      this.setResizable(true);
      ButtonsHandler ButtonsActionListener = new ButtonsHandler();
      
     
      paintPanel.setBackground( Color.WHITE );
      toolPanel.setLayout(new GridLayout(10, 1));
      add(statusBar, BorderLayout.SOUTH);
      add(toolPanel, BorderLayout.WEST);
      add(paintPanel, BorderLayout.CENTER);

      JLabel drawTool = new JLabel("[�L�D����]");
      JLabel brushSize = new JLabel("[�Pˢ��С]");
      String[] items = { "�Pˢ", "ֱ��", "�E�A��", "����", "��Ƥ��" };
      JComboBox toolBox = new JComboBox(items);
      toolBox.addItemListener(new BoxListener());
      toolPanel.add(drawTool);
      toolPanel.add(toolBox);
      toolPanel.add(brushSize);


	  bigButton = new JRadioButton("С");
	  bigButton.addActionListener(ButtonsActionListener);
	  bigButton.setSelected(true);
	  toolPanel.add(bigButton); 
	    
	  midButton = new JRadioButton("��");
	  midButton.addActionListener(ButtonsActionListener);
	  toolPanel.add(midButton); 
	    
	  smallButton = new JRadioButton("��");
	  smallButton.addActionListener(ButtonsActionListener);
	  toolPanel.add(smallButton); 
	    	   
	  group.add(bigButton);
	  group.add(midButton);
	  group.add(smallButton);

      fill = new JCheckBox("��M");
      fill.addItemListener(new BoxListener());
      foreground = new JButton("ǰ��ɫ");
      foreground.addActionListener(ButtonsActionListener);
      background = new JButton("����ɫ");
      background.addActionListener(ButtonsActionListener);
      JButton clearScreen = new JButton("�������");
      clearScreen.addActionListener(ButtonsActionListener);
      foreground.setBackground(color1);
    		  
      toolPanel.add(fill);
      toolPanel.add(foreground);
      toolPanel.add(background);
      toolPanel.add(clearScreen);
      addMouseMotionListener(new MouseMotion());
      addMouseListener(new  MouseAdapterDemo()) ;
	  foreground.setBackground(color1);
	  background.setBackground(backcolor);
	           
      Init = 0;
      penSize = 3;
      fill.setEnabled(false);  
      
      
  }

  int mox, moy,drx,dry,rex,rey,prx,pry;
  int  Init, penSize;
  int mode = 1;
  int solid = 0;
  //�L�D����
  public void paint(Graphics g) {
      Graphics2D G2D = (Graphics2D) g; 
	  G2D.setStroke(new BasicStroke(penSize));//�Pˢ�ֶ�
      toolPanel.paint(toolPanel.getGraphics());
	  g.setColor(color1);
      
	  float[] dash={10,10}	;
	  if(mode == 1 ) {
	    if(Init ==0){
	      g.drawLine(mox,moy,drx,dry);        
          mox = drx;
          moy = dry;
          repaint();    
	    }
	  } 
	  else if(mode == 2) {
		  if(Init ==3) {	
		  if(solid==1)	  
		  g.drawLine(rex,rey,prx,pry);
		  else	      
		  G2D.setStroke(new BasicStroke(penSize,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10,dash,0) );
		  g.drawLine(rex,rey,prx,pry);
          repaint();
          Init = 0;          
		  } 
	   }
	  else if(mode == 4) {
		  if(Init ==3) {
		  int dx,dy;
		  if(prx>rex) {
			  dx=prx-rex ;
			  prx=rex;
		  }
		  else dx=rex-prx;
		  if(pry>rey) {
			  dy=pry-rey;
			  pry=rey;
		  }
		  else dy=rey-pry;
		  if(solid == 0)
		  g.drawRect(prx,pry,dx,dy);		
		  else
	      g.fillRect(prx,pry,dx,dy);	
          repaint();
          Init = 0;
          
		  } 
	   }
	  else if(mode == 3) {
		  if(Init ==3) {
		  int dx,dy;
		  if(prx>rex) {
			  dx=prx-rex ;
			  prx=rex;
		  }
		  else dx=rex-prx;
		  if(pry>rey) {
			  dy=pry-rey;
			  pry=rey;
		  }
		  else dy=rey-pry;
		  if(solid==0)
		  g.drawOval(prx,pry,dx,dy);	
		  else
	      g.fillOval(prx,pry,dx,dy);	
          repaint();
          Init = 0;
          
		  } 
	   }
	  if(mode == 5 ) {
		    if(Init ==0){
		      g.setColor(backcolor);
		      g.drawLine(mox, moy,drx,dry);
	          g.drawLine(mox,moy,drx,dry);
	          mox = drx;
	          moy = dry;
	          repaint();    
		    }
		  } 
  }
  
  private class BoxListener implements ItemListener { 
	  @Override
	  public void itemStateChanged(ItemEvent e) {
             if(e.getItem() == "�Pˢ") {
            	mode = 1;
                fill.setEnabled(false);  
             }
               
             else if(e.getItem() == "ֱ��") {mode = 2 ;}
             else if(e.getItem() == "�E�A��") {mode = 3;}
             else if(e.getItem() == "����") {mode = 4;}
             else if(e.getItem() == "��Ƥ��") {mode = 5;}             
             if(fill.isSelected())  solid = 1; 
             else 
             solid = 0;
             if(mode==1) {
            	 fill.setEnabled(false); 
            	              }
             else fill.setEnabled(true); 
      }
  }
  
  private class ButtonsHandler implements ActionListener
  {
	    public void actionPerformed(ActionEvent e)
	    {
	    	if(e.getActionCommand()=="��") {
	    		penSize = 10;
	    	}
	    	if(e.getActionCommand()=="��") {
	    		penSize = 5;
	    	}	    	
	    	if(e.getActionCommand()=="С") {
	    		penSize = 3;
	    	}	
	    	
	    	if(e.getActionCommand()=="ǰ��ɫ") {
	    	  		color1=JColorChooser.showDialog(null,"�xɫ��", Color.WHITE);
	    	  	    foreground.setBackground(color1);
	    	}  		
		    if(e.getActionCommand()=="����ɫ") {
		        	backcolor=JColorChooser.showDialog(null,"�xɫ��", Color.WHITE);
		      	    background.setBackground(backcolor);
		        	paintPanel.setBackground(backcolor);
	  	  	    	}
		    if(e.getActionCommand()=="�������") {	   
		    	backcolor=Color.WHITE;
	        	paintPanel.setBackground(backcolor);
	      	    background.setBackground(backcolor);
  	  	    	}
	    }
   }
  //�����¼�
  private class MouseMotion extends MouseMotionAdapter {
	  
      public void mouseMoved(MouseEvent e) {
          mox = e.getX();
          moy = e.getY();            
          statusBar.setText("�Θ�λ�� �� (" + mox + "," +moy+")");
          Init =1;
      }
      public void mouseDragged(MouseEvent e) {
          drx = e.getX();
          dry = e.getY();         
          Init =0;
          repaint();
      }      
   }
  public class MouseAdapterDemo extends MouseAdapter { 
	    public void mousePressed(MouseEvent e) {
	    	prx = e.getX();
	    	pry = e.getY(); 	
	    	repaint();
	    }
	     
	    public void mouseReleased(MouseEvent e) {
	    	rex = e.getX();
	    	rey = e.getY();  
	    	Init = 3;
	    	repaint();	       
	    }
  }

  public static void main(String[] args) {
      SmallPainter test = new SmallPainter();
      test.setVisible(true);
  }
}