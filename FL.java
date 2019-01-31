import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.Random;

/*Font viewer v0.1 
  Author : @LCMr_music (LC Mohale)
*/
public class FL implements ActionListener {
	
	//Get fonts
	GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	Font fontList[] = env.getAllFonts();
	
	//Menubar and Menu
    JMenuBar mb = new JMenuBar();
	JMenu menu = new JMenu("Font : "+ fontList[0].getFontName());
	
	//Buttons
	JButton btnNext = new JButton("Next >>");
	JButton btnPrevious = new JButton("<< Previous");
	JButton btnRandom = new JButton("<< Random >>");
	
	//Text Area
	JTextArea ta = new JTextArea(800,350);
	          
	
	//Method for reading File contents
	String readFileContents(File contentFile)throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(contentFile));
		
		try{
			StringBuilder builtString = new StringBuilder();
			String line = reader.readLine();
			
			while(line != null){
				builtString.append(line);
				builtString.append("\n");
				line = reader.readLine();
			}
            return builtString.toString();	
		}finally{
			reader.close();
		}	
	}
	 
	//Button actions
	int i = 0;
	Random ran = new Random();
	public void actionPerformed(ActionEvent e){
		   if(e.getSource() == btnRandom){
				i = ran.nextInt(457);
				ta.setFont(new Font(fontList[i].getFontName(),Font.PLAIN,16));
				menu.setText("Font : " +fontList[i].getFontName());
			}

			if(e.getSource() == btnNext){
				if(i >= fontList.length-1){
					i = fontList.length-1;
				}else{
					i = i + 1;
				}
				ta.setFont(new Font(fontList[i].getFontName(),Font.PLAIN,16));
				menu.setText("Font : " +fontList[i].getFontName());
			}
			if(e.getSource() == btnPrevious){
				if(i <= 0){
					i = 0;
				}else{
					i = i - 1;
				}
				ta.setFont(new Font(fontList[i].getFontName(),Font.PLAIN,16));
				menu.setText("Font :" +fontList[i].getFontName());
				System.out.println("Btn Previous pressedi is" + i);
			}
		}
		
	public FL(){
		      
		   String fileContents = "";
		   
				try{
					//Get displayContent
					File file = new File ("C:\\Users\\MD_Mbhele\\Desktop\\g.txt");
				
					   //Call the read contents Method
					  fileContents = readFileContents(file);	
				}catch(Exception e){
					System.out.println(e.toString());
				}
				
				JFrame frame = new JFrame("@LCMr_music Font Viewer 0.1");
					   frame.setSize(800,550);
					   
				//Container for adding components to frame #CRUCIAL 
				Container frameContainer = frame.getContentPane();
				
				ta.setBackground(Color.yellow);
	            ta.setLineWrap(true);
			  
				//ScrollPane for making textArea scrollable
		        JScrollPane sp = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				
				//Main Panel & Low Panel
				JPanel pnlMain = new JPanel();
					   pnlMain.setSize(800,350);
					   pnlMain.setBackground(Color.yellow);
					   pnlMain.setLayout(new BorderLayout());
					   
				JPanel pnlLow = new JPanel();
					   pnlLow.setSize(800,200);
					   pnlLow.setBackground(Color.white);
					   pnlLow.setLayout(new BorderLayout());
					   
				
				//Listen to Events on Buttons
				btnNext.addActionListener(this);
				btnPrevious.addActionListener(this);
				btnRandom.addActionListener(this);

				//Add buttons to Panel
				pnlLow.add(btnRandom,BorderLayout.CENTER);
				pnlLow.add(btnNext,BorderLayout.EAST);
				pnlLow.add(btnPrevious,BorderLayout.WEST);
				
				//Add a Scroll Panel to Panel area to the main panel
				sp.setBackground(Color.red);
				pnlMain.add(sp,BorderLayout.CENTER);
				
				//Add contents to text area
				 ta.setText("WhatsApp chat with Carina Weideman & Dieter Bruijns\n");
				 ta.append("---------------------------------------------------------------------------------------------------------\n\n");
				 ta.append(fileContents);
				 
				//Add Panels to the Frame Container
				frame.add(pnlMain,BorderLayout.CENTER);
				frame.add(pnlLow,BorderLayout.SOUTH);
				
				//Enable frame to be closed with the Close button
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				//Add menuBar to frame
				mb.add(menu);
				frame.setJMenuBar(mb);
				//Show frame
				frame.setVisible(true);
	}
	
   public static void main(String args[]){
	   try{
		FL fontViewer = new FL();
	   }catch(Exception e){
		   System.out.println(e.toString());
	   }
   }	
}