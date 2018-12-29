package create;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
public class ChessUI extends JFrame {
	/**
	 * 
	 */
	static String[][]a = {
    		{"-1","-2","-3","0","0"},
    		{"-4","-5","0","0","0"},
    		{"-6","0","0","0","6"},
    		{"0","0","0","5","4"},
    		{"0","0","3","2","1"}
    		};
	static int chessx,chessy,posx,posy;	
	static boolean[][] flag = new boolean[5][5];	
	private JButton[][] button = new JButton[5][5];	
	private static final long serialVersionUID = 1L;	
	private JTextField textField;//��ʾ��1
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ChessUI();                 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
	}
	public ChessUI() {
        initialize();
        flag_clear();        
	}	
	//ɫ�ӵı���	
	//who play first
	private JCheckBox whoplay;
	private JButton btn;
	private JTextField  t=null;
	private String s=null;
	static private int S=-1;
	private JTextField dHint=null;
	private NextStep N;
	protected int egg;
	/**
     * Initialize the contents of the frame.
     */
    private boolean flag_exis() {//�жϴ���
    	for(int i=0;i<5;i++) for(int j=0;j<5;j++) if(flag[i][j]==true) return true;
    	return false;
    }
    private void flag_clear() {
    	for(int i=0;i<5;i++) for(int j=0;j<5;j++) flag[i][j]=false;
    }
    private void buttonew() {
    	for(int i=0;i<5;i++) for(int j=0;j<5;j++) a[i][j]="0";
    	a[0][0]="-1";
    	a[0][1]="-2";
    	a[0][2]="-3";
    	a[1][0]="-4";
    	a[1][1]="-5";
    	a[2][0]="-6";
    	a[2][4]="6";
    	a[3][3]="4";
    	a[3][4]="5";
    	a[4][2]="3";
    	a[4][3]="2";
    	a[4][4]="1";
   }
    private int[] checkcs(String cs) {
		int i,j;
		int adrs[]=new int[2];
		//if(cs=="7"||cs=="-1") return null;
		for(i=0;i<5;i++) {
			for(j=0;j<5;j++) {
				if(cs.compareTo(a[i][j])==0) {
					adrs[0]=(i);
					adrs[1]=(j);
					return adrs;
				}
			}
		}
		adrs=new int[0];
		return adrs;
	}
    private boolean validmove(String chess) {
		if(egg>=8) return true;
    	if(chess.compareTo(Integer.toString(S))==0) return true;//chess exist with its address
		else {
			if((checkcs(Integer.toString(S)).length!=0)&&(chess.compareTo(Integer.toString(S))!=0))
				return false;//ɫ�Ӷ�Ӧ���� ѡ�Ĳ��Ƕ�Ӧ
			//int left=0,right=0;
			int lc=S-1,rc=S+1;
			while(lc>0) {
				if(checkcs(String.valueOf(lc)).length!=0) break;
				lc--;
			}
			while(rc<7) {
				if(checkcs(String.valueOf(rc)).length!=0) break;				
				rc++;
			}

			if(chess.compareTo(Integer.toString(rc))==0) {//right side hit
				return true;
			}
			if(chess.compareTo(Integer.toString(lc))==0) {//left side hit
				return true;				
			}
			return false;
		}		
	}
    private void initialize() {
        new JFrame();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 450);
        setTitle("Einstein");
        setBackground(new Color(144, 238, 144));
        setFont(new Font("Helvetica", Font.PLAIN, 14));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{60, 60, 60, 60, 60, 60, 60, 30, 30, 60, 30, 30, 60, 30, 30, 30, 30};
        gridBagLayout.rowHeights = new int[]{60, 60, 60, 60, 60, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        setVisible(true);       
        //Add the buttons in a 5x5 grid
        N =new NextStep();       
        for(int i = 0;i<5;i++) {
        	for(int j = 0;j<5;j++) {       		
        		button[i][j] = new JButton();
        		button[i][j].setFont(new Font("Consolas", Font.PLAIN, 20));
        		button[i][j].setForeground(new Color(255, 255, 255));       		
        		GridBagConstraints tmp = new GridBagConstraints();
        		tmp.fill = GridBagConstraints.BOTH;
        		tmp.gridx = j;
        		tmp.gridy = i;
        	
        		if(a[i][j]=="0") {
        			button[i][j].setText(" ");
        			button[i][j].setBackground(new Color(255,247,219));
        		}
        		if(a[i][j]=="1"||a[i][j]=="2"||a[i][j]=="3"||a[i][j]=="4"||a[i][j]=="5"||a[i][j]=="6") {
        			button[i][j].setText(a[i][j]);
        			button[i][j].setBackground(new Color(255,0,0));    //Human player = red;
        		}
        		if(a[i][j]=="-1"||a[i][j]=="-2"||a[i][j]=="-3"||a[i][j]=="-4"||a[i][j]=="-5"||a[i][j]=="-6") {
        			button[i][j].setText(a[i][j]);
        			button[i][j].setBackground(new Color(0,0,255));    //AI player = blue;
        		}
          		int I = i,J = j;
        		//��ÿ����ť������+�Ľ�
        		//flag[I][J] = false;//��ʼ״̬ û���
        		button[i][j].addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				if(S==-1) {return;}
        				if(a[I][J]=="1"||a[I][J]=="2"||a[I][J]=="3"||a[I][J]=="4"||a[I][J]=="5"||a[I][J]=="6"||egg>7)
        				{
        					if(!validmove(a[I][J])) {
        						dHint.setText("��ѡ���Ӳ��Ϸ�����ѡ����"+S+"���������");
        						return;
        					}
        					if(flag_exis()) {
            					flag_clear();
            					renew();
            				}
            				button[I][J].setBackground(new Color(255,191,202));//pink
            				dHint.setText("��ѡ��һ���߷���������Խ��");
            				flag[I][J] = true;//�Լ��İ�ť�����
        				}
        			}
        		});
       		add(button[i][j],tmp);
        	}
        }       
        /*
         * Add the remainders;
         */
        //Reminder
        textField = new JTextField();//��ʾ��
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setFont(new Font("", Font.BOLD, 16));
        textField.setBackground(new Color(144, 238, 144));//��ɫ
        textField.setColumns(20);
        textField.setText("��PLAY��ʼ��Ϸ");
        GridBagConstraints tmp = new GridBagConstraints();
        tmp.gridwidth = 8;
        tmp.fill = GridBagConstraints.BOTH;
        tmp.gridx = 6;
        tmp.gridy = 0;
        add(textField,tmp);
        
        //The "Go" button
        //�����߰�ť
        JButton btnLeft = new JButton("\u5DE6");
        btnLeft.setBackground(new Color(0, 255, 255));
        btnLeft.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_btnLeft = new GridBagConstraints();
        gbc_btnLeft.fill = GridBagConstraints.BOTH;
        gbc_btnLeft.gridx = 6;
        gbc_btnLeft.gridy = 4;
        add(btnLeft, gbc_btnLeft);
        btnLeft.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				
				for(int i = 0;i<5;i++) {
		        	for(int j = 0;j<5;j++) {
		        		if(flag[i][j]==true) {
		        			
		        			chessx = i;
		        			chessy = j;
		        			posx = chessx ;
		        			posy = chessy -1;
		        			if(posy==-1||posx==-1) {
		        				dHint.setText("����Խ�硢������ѡ��");
								return;
		        			}
		        			flag[i][j] = false;
		        			Playing();
		        			return;
		        		}
		        	}	
		        }
				if((egg>7)) {
					egg++;
					if(egg%2==0) {
						AIUpdate(a, false);
					}
				}
			}
		});
        
      //�����߰�ť
        JButton btnUp = new JButton("\u4E0A");
        btnUp.setBackground(new Color(0, 255, 255));
        btnUp.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_btnUp = new GridBagConstraints();
        gbc_btnUp.fill = GridBagConstraints.BOTH;
        gbc_btnUp.gridx = 9;
        gbc_btnUp.gridy = 4;
        add(btnUp, gbc_btnUp);
        btnUp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				for(int i = 0;i<5;i++) {
		        	for(int j = 0;j<5;j++) {
		        		if(flag[i][j]==true) {
		        			
		        			chessx = i;
		        			chessy = j;
		        			posx = chessx - 1;
		        			posy = chessy ;
		        			if(posy==-1||posx==-1) {
		        				dHint.setText("����Խ�硢������ѡ��");
		        				return;
		        			}
		        			flag[i][j] = false;
		        			Playing();
		        			return;
		        		}
		        	}
		        		
		        }
				
			}
		});
        
      //��б���߰�ť
        JButton btnOblique = new JButton("\u659C\u4E0A");
        btnOblique.setBackground(new Color(0, 255, 255));
        btnOblique.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_btnOblique = new GridBagConstraints();
        gbc_btnOblique.fill = GridBagConstraints.BOTH;
        gbc_btnOblique.gridx = 12;
        gbc_btnOblique.gridy = 4;
        add(btnOblique, gbc_btnOblique);
        btnOblique.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				for(int i = 0;i<5;i++) {
		        	for(int j = 0;j<5;j++) {
		        		if(flag[i][j]==true) {
		        			chessx = i;
		        			chessy = j;
		        			posx = chessx - 1;
		        			posy = chessy - 1;
		        			if(posy==-1||posx==-1) {
		        				dHint.setText("����Խ�硢������ѡ��");
		        				return;
		        			}
		        			flag[i][j] = false;
		        			Playing();
		        			return;
		        		}
		        	}
		        		
		        }
				
			}
		});
        //ɫ��
        btn = new JButton("PLAY");
		btn.setBackground(new Color(0, 255, 255));
		btn.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints btnDice = new GridBagConstraints();
        btnDice.fill = GridBagConstraints.BOTH;
        btnDice.gridx = 15;
        btnDice.gridy = 4;
        add(btn, btnDice);
		dHint = new JTextField();
		dHint.setHorizontalAlignment(SwingConstants.CENTER);
		dHint.setFont(new Font("", Font.BOLD, 16));
		dHint.setBackground(new Color(113,191,234));
		dHint.setColumns(20);
		GridBagConstraints dTmp = new GridBagConstraints();
        dTmp.gridwidth = 9;
        dTmp.fill = GridBagConstraints.BOTH;
        dTmp.gridx = 6;
        dTmp.gridy = 2;
        add(dHint,dTmp);
        new JLabel("");
		new JPanel();
		btn.setBounds(220,190,100,50);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(whoplay.isSelected()==true) {
					
						buttonew();
						flag_clear();
						renew();
					
					AIUpdate(a,true);
					btn.setText("RESTART");
					return;
				}else if(btn.getText().compareTo("RESTART")==0) {
					buttonew();
					flag_clear();
					renew();
				}
				S=1+(int)(Math.random()*6);//��������
				s=String.valueOf(S);
				for(int i=0;i<200;i++) { 
					int tem=1+(int)(Math.random()*6);
					String s1=String.valueOf(tem);
					t.setText(s1);
					t.setText("");
				}
				t.setText(s);
				textField.setText("\u8bf7\u9009\u62e9\u7ea2\u8272"+S+"\u53f7\u68cb\u5b50");
				dHint.setText("��"+S+"\u53f7\u68cb\u5b50\u5df2\u88ab\u5403\u6389\u8bf7\u9009\u62e9\u53f7\u7801\u6700\u8fd1\u7684\u7684\u68cb\u5b50");
				btn.setText("RESTART");
				//whoplay.setEnabled(false);
			}
			
		});
		
		t=new JTextField (300);
		Font f1=new Font("Consolas", Font.PLAIN,45);
	    t.setFont(f1);
		btn.setFont(new Font("Consolas", Font.PLAIN, 23));
		btn.setBounds(100, 100, 300, 100);
		GridBagConstraints tDice = new GridBagConstraints();
		tDice.fill = GridBagConstraints.BOTH;
		tDice.gridx = 15;
		tDice.gridy = 0;
		t.setBackground(new Color(144, 238, 144));
		t.setHorizontalAlignment(SwingConstants.CENTER);
        add(t, tDice);
        
        egg=0;
       whoplay=new JCheckBox("����");
       whoplay.setFont(new Font("", Font.BOLD, 24));
       whoplay.setBounds(100, 200,300,100);
       whoplay.setEnabled(true);
       GridBagConstraints Whopl = new GridBagConstraints();
       Whopl.fill =GridBagConstraints.BOTH;
       Whopl.gridx = 15;
       Whopl.gridy = 2;
       //whoplay.setBackground(Color.WHITE);
       whoplay.setHorizontalAlignment(SwingConstants.LEFT);
       whoplay.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			egg++;
			if(egg==4) dHint.setText("Version:0.98Beta");
			if(egg==6) dHint.setText("No more eggs");
			if(egg==8) dHint.setText("Entered Development Mode");
		}
	});
       add(whoplay,Whopl);
    }
	
    /*
     * Change the int[][] to String[][]
     */
	private String transfer(int num) {
		String tmp = null;
		switch(num) {
		case 1:tmp = "1";break;
		case 2:tmp = "2";break;
		case 3:tmp = "3";break;
		case 4:tmp = "4";break;
		case 5:tmp = "5";break;
		case 6:tmp = "6";break;
		case 0:tmp = " ";break;
		case -1:tmp = "-1";break;
		case -2:tmp = "-2";break;
		case -3:tmp = "-3";break;
		case -4:tmp = "-4";break;
		case -5:tmp = "-5";break;
		case -6:tmp = "-6";break;
		}
		
		return tmp;
	}
	
	/*
	 * Renew the a[][]
	 */
	private int Transfer(String src) {
		int tmp = 0;
		switch(src) {
		case "1":tmp = 1;break;
		case "2":tmp = 2;break;
		case "3":tmp = 3;break;
		case "4":tmp = 4;break;
		case "5":tmp = 5;break;
		case "6":tmp = 6;break;
		case " ":tmp = 0;break;
		case "-1":tmp = -1;break;
		case "-2":tmp = -2;break;
		case "-3":tmp = -3;break;
		case "-4":tmp = -4;break;
		case "-5":tmp = -5;break;
		case "-6":tmp = -6;break;
		}
		
		return tmp;
	}

	public int AIUpdate(String A[][],boolean isai) {//�����ߺ���������鴫��
		int AI[][] = new int[5][5];
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				AI[i][j] = Transfer(A[i][j]);
			}
		}
		int chess[][];
		//ɫ��
		int point=(int)(Math.random()*6)+1;
		int mypoint=1+(int)(Math.random()*6);
		textField.setText("AIͶ����"+point+"��,�Ұ���Ͷ��"+mypoint+"�㣬��˼��");
		s=String.valueOf(mypoint);
		t.setText(s);
		S=mypoint;
		dHint.setText("��"+mypoint+"\u53f7\u68cb\u5b50\u5df2\u88ab\u5403\u6389\u8bf7\u9009\u62e9\u53f7\u7801\u6700\u8fd1\u7684\u7684\u68cb\u5b50");
		if(isai==true) {
			
			chess=N.test_ai(AI,point);//����AI��Ӧ�������
		
		}else {
			chess=N.test(AI, mypoint);
			}
		
		for(int i = 0;i<5;i++) {
			for(int j=0;j<5;j++) {
				a[i][j] = transfer(chess[i][j]);
			}
		}
		renew();//��ӡGUI
		if(isai==false) {
			for(int i=0;i<5;i++) {
				for(int j=0;j<5;j++) {
					AI[i][j] = Transfer(a[i][j]);
				}
			}
			AIUpdate(a, true);
		}
		if(N.Eva(true, chess)>10000) {	
    		dHint.setText("���ź������ˣ���RESTART����");
    		S=-1;
    		return 0;
		}else if (N.Eva(true, chess)<-10000) {
			dHint.setText("��ϲ��Ӯ�ˣ�����һ��ô");
    		S=-1;
    		return 0;
		}
		//N.print(chess);//console
		return 1;
	}
	private void nextplay() {
		int AI[][] = new int[5][5];
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				AI[i][j] = Transfer(a[i][j]);
			}
		}
		//System.out.println(N.Eva(true, AI)+":"+N.Eva(false, AI));
    	if(N.Eva(true, AI)>10000)	
    		dHint.setText("���ź������ˣ���RESTART����");
    	else if(N.Eva(true, AI)<-10000)
    		dHint.setText("��ϲ��Ӯ�ˣ�����һ��ô");
    	else AIUpdate(a,true);
	}
    private void Playing() {
    	a[posx][posy] = a[chessx][chessy];
    	a[chessx][chessy] = " ";
    	renew();
    	
    	nextplay();
    }

	/*
	 * Renew the chessmap
	 */
	
	private void renew() {
		
		for(int i = 0;i<5;i++) {
        	for(int j = 0;j<5;j++) {
				if(a[i][j]==" "||a[i][j]=="0") {
        			button[i][j].setText(" ");
        			button[i][j].setBackground(new Color(255,247,219));
        		}
        		if(a[i][j]=="1"||a[i][j]=="2"||a[i][j]=="3"||a[i][j]=="4"||a[i][j]=="5"||a[i][j]=="6") {
        			button[i][j].setText(a[i][j]);
        			button[i][j].setBackground(new Color(255,0,0));    //Human player = red;
        		}
        		if(a[i][j]=="-1"||a[i][j]=="-2"||a[i][j]=="-3"||a[i][j]=="-4"||a[i][j]=="-5"||a[i][j]=="-6") {
        			button[i][j].setText(a[i][j]);
        			button[i][j].setBackground(new Color(0,0,255));    //AI player = blue;
        		}
        		
        	}
        }
		//flag_clear();
	}
	
	
}



