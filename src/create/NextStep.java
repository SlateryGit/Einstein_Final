package create;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




/**
 *
 * @author dear��
 */
class Poin{//�����ߵ����������ֵ
    int x,y,num;
}

public class NextStep {
    
    static int [][]nextc=new int[7][7];
    //add by slate
    private int[][] tab;
    public NextStep(int[][] tab1) {
    	tab = new int[5][5];
		//���
		int i,j;
		for(i=0;i<5;i++) for(j=0;j<5;j++) tab[i][j]=tab1[i][j];
    	
    }

    NextStep() {
    	tab = new int[5][5];
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //
    public void print(int [][]dchess){
        for(int i=0;i<5;i++)
        {
        for(int j=0;j<5;j++){
            System.out.print(dchess[i][j]+" ");
        }
        System.out.println("");
        }
        System.out.println("");
    }
    
    
    private void up(int [][]chess,Poin p){
        int xx=p.x,yy=p.y;
        if(p.x>0){
            chess[xx-1][yy]=chess[xx][yy];
            chess[xx][yy]=0;
        }
    }
    private void left(int [][]chess,Poin p){
        int xx=p.x,yy=p.y;
        if(p.y>0){
            chess[xx][yy-1]=chess[xx][yy];
            chess[xx][yy]=0;
        }
    }
    
    private void down(int [][]chess,Poin p){
        int xx=p.x,yy=p.y;
        if(p.x<4){
            chess[xx+1][yy]=chess[xx][yy];
            chess[xx][yy]=0;
    }
    }
    private void right(int [][]chess,Poin p){
        int xx=p.x,yy=p.y;
        if(p.y<4){
            chess[xx][yy+1]=chess[xx][yy];
            chess[xx][yy]=0;
        
    }
    }
    
    private void dori(int [][]chess,Poin p){
        int xx=p.x,yy=p.y;
        if(p.y<4&&xx<4){
            chess[xx+1][yy+1]=chess[xx][yy];
            chess[xx][yy]=0;
        
    }
    }
    private void uple(int [][]chess,Poin p){
        int xx=p.x,yy=p.y;
        if(p.y>0&&xx>0){
            chess[xx-1][yy-1]=chess[xx][yy];
            chess[xx][yy]=0;
        }
    }
    private void copy(int [][]a,int [][]b){
        
        for(int tmd=0;tmd<5;tmd++)
                        for(int tmb=0;tmb<5;tmb++){
                            a[tmd][tmb]=b[tmd][tmb];
                        }
    }
    
    
    private int [][]chess=new int[7][7];
    int num;
    int [][]dchess=new int[7][7];
    int maxx;
    int ddeep=8;
    private int min(int deep,int alpha,int beta){
        Poin []p=new Poin[15];
        int lp=2;//�����Ӹ���
        int r=0,l=7;//�����ӿ���
        int maxmin=-65535000;//��С������ֵ
        int minmax=65535000;
        //print(chess);
        
        
        for(int i=0;i<10;i++)
        {
        p[i]=new Poin();
        p[i].num=0;
        }
        int eva=Eva(false,chess);
        if(deep==ddeep) {
            return eva;
        }
        
        if(eva>=65536||eva<=-65535){
            return eva-deep;
            
        }
        
            lp=0;
            for(int i=0;i<5;i++)//�жϿ�����������
            for(int j=0;j<5;j++){
                if(chess[i][j]<0){
                    p[lp].x=i;
                    p[lp].y=j;
                    p[lp].num=chess[i][j];
                    lp++;
                }
            }
            
            int xiaokk=65535000;//��ǰ��Сֵ
            for(int i=0;i<lp;i++){//ÿ������������
                Poin pi=new Poin ();
                int [][]lchess=new int[7][7];
                copy(lchess,chess);
                
                pi=p[i];
                if(pi.num==0) continue;
                if(p[i].x<4)
                {
                    down(chess,pi);
                    int lookn=max(deep+1,alpha,beta);
                    copy(chess,lchess);
                    if(lookn<xiaokk)
                    {
                        xiaokk=lookn;
                        if(lookn<beta) beta=lookn;
                        if(beta<alpha) break;
                    }
                    
                    
                }
                if(p[i].y<4)
                {
                    
                    right(chess,pi);
                    int lookn=max(deep+1,alpha,beta);
                    copy(chess,lchess);
                    if(lookn<xiaokk){
                        xiaokk=lookn;
                        if(lookn<beta) beta=lookn;
                        if(beta<alpha) break;
                    }
                        
                }
                if(p[i].x<4&&p[i].y<4)
                {
                    dori(chess,pi);
                    int lookn=max(deep+1,alpha,beta);
                    copy(chess,lchess);
                    if(lookn<xiaokk){
                        xiaokk=lookn;
                        if(lookn<beta) beta=lookn;
                        if(beta<alpha) break;
                    } 
                }
//                if(xiaokk<minmax)
//                {
//                    minmax=xiaokk;
//                    
//                }
                
        }
        
        
        return xiaokk;
    }
    
    //*************************************************************************
    public int max(int deep,int alpha,int beta){
        Poin []p=new Poin[15];
        int lp=2;//�����Ӹ���
        int r=0,l=7;//�����ӿ���
        int maxmin=-65535000;//��С������ֵ
        int minmax=65535000;
        //print(chess);
        
        for(int i=0;i<10;i++)
        {
        p[i]=new Poin();
        p[i].num=0;
        }
        
        int eva=Eva(false,chess);
        
        if(deep==ddeep) {
            return eva;
        }
        
        if(eva>=65536||eva<=-65535) return eva-deep;
        
        if(deep==0){//��һ��
            for(int i=0;i<5;i++){//�жϿ�����������
	            if(lp==1) break;
	            for(int j=0;j<5;j++){
	                if(num==chess[i][j]){
	                    p[0].num=num;
	                    p[0].x=i;
	                    p[0].y=j;
	                    lp=1;
	                    break;
	                }
	                if(chess[i][j]<num&&chess[i][j]>r){
	                    r=chess[i][j];
	                    p[0].num=chess[i][j];
	                    p[0].x=i;
	                    p[0].y=j;
	                }
	                else
	                    if(chess[i][j]>num&&chess[i][j]<l){
	                        l=chess[i][j];
	                        p[1].num=chess[i][j];
	                        p[1].x=i;
	                        p[1].y=j;
	                    }
	            }
            }
        }
        else{//���ǵ�һ��  �����Ӷ�����
            lp=0;
                for(int i=0;i<5;i++)//�жϿ�����������
                for(int j=0;j<5;j++){
                    if(chess[i][j]>0){
                        p[lp].x=i;
                        p[lp].y=j;
                        p[lp].num=chess[i][j];
                        lp++;
                    }
            }
        }
        int xiaokk=-65535000;//��ǰ���ֵ
        for(int i=0;i<lp;i++){//ÿ�����������߶�����i�����ߣ�
            
            Poin pi=new Poin();//��ǰ�߶��ĵ�
            int [][]lchess=new int [7][7];
            copy(lchess,chess);
            pi=p[i];
            if(pi.num==0) continue;
            
            
            if(p[i].x>0&&p[i].y>0)
            {
                uple(chess,pi);
                //print(chess);
                int lookn=min(deep+1,alpha,beta);
                
                if(lookn>xiaokk)
                {
                    xiaokk=lookn;
                    if(lookn>alpha) alpha=lookn;
                    if(deep==0) copy(dchess,chess);
                }
                copy(chess,lchess);
                if(alpha>beta) break;
                
                
                //print(chess);
            }
            if(p[i].x>0)//�߶������ַ�ʽ
            {
                up(chess,pi);
                //print(chess);
                int lookn=min(deep+1,alpha,beta);
                
                if(lookn>xiaokk)
                {
                    xiaokk=lookn;
                    if(lookn>alpha) alpha=lookn;
                    if(deep==0) copy(dchess,chess);
                }
                copy(chess,lchess);
                if(alpha>beta) break;
            }
            if(p[i].y>0)
            {
                left(chess,pi);
                int lookn=min(deep+1,alpha,beta);
                
                
                if(lookn>xiaokk){
                    xiaokk=lookn;
                    if(lookn>alpha) alpha=lookn;
                    if(deep==0) copy(dchess,chess);
                }
                copy(chess,lchess);
                if(alpha>beta) break;
            }
//            if(maxmin<xiaokk)
//            {
//                maxmin=xiaokk;
//            }
        }
        return xiaokk;
    }
    
    
    public int next(int [][]cchess,int nnum,boolean isme,int deep){//isme0����    //���� ɫ�� ˭�� ���
        
                        for(int tmd=0;tmd<5;tmd++)
                        for(int tmb=0;tmb<5;tmb++){
                            chess[tmd][tmb]=cchess[tmd][tmb];
                        }
                        
                        num=nnum;
                        int meide=max(deep,-37566777,37566777);
                        
                        copy(cchess,dchess);
                        
        return meide;
        
        
    }
     

    public int[][] test(int [][]chess,int num){
        
        next(chess, num, false, 0);
        return chess;
    }
    

    
    
    

//add by slate    
    
  //ת���Ƕȣ���ת180~
  	public int[][] revert(int[][] tab) {
  		int[][] ret =new int[5][5];
  		int i,j,temp1,temp2;
  		for(i=0;i<5;i++) {
  			for(j=0;j<5-i;j++) {
  				temp1=-1*tab[i][j];
  				temp2=-1*tab[4-i][4-j];
  				ret[i][j]=temp2;
  				ret[4-i][4-j]=temp1;
  			}
  		}
  		
  		return ret;
  	}    
    
    
    
    
  	public int[][] test_ai(int [][]chess,int num){
        int[][] ret=new int[5][5];
        ret=revert(chess);
        //��ת	
        //print(ret);
        int putt;
		putt=next(ret, num, false, 0);
                
		int[][] ret2=new int[5][5];
        if(putt>=65520||putt==-65535)
        {
            return revert(ret);
        	/*for(int i=0;i<5;i++)
                for(int j=0;j<5;j++)
                    ret2[i][j]=6;*/
            
        }
        else
        	ret2=revert(ret);
		
        return ret2;
    }
    
  	//===============EvaluFc
  	
  	private int ct_me() {
		
		int i,j,c=0;
		for(i=0;i<5;i++) 
			for(j=0;j<5;j++) 
				if(tab[i][j]>6)
					return -1;
				else if((tab[i][j]>0)&&(tab[i][j]<7))//count 1-6
					c++;
		return c;
	}
	private int ct_ai() {
		//count -1
		int i,j,c=0;
		for(i=0;i<5;i++) 
			for(j=0;j<5;j++) 
				if(tab[i][j]<-6)
					return -1;
				else if((tab[i][j]<0)&&(tab[i][j]>-7))
					c++;
		return c;
	}
	//�鿴 cs���ӵ�λ��,û�з���0length
	private int[] checkcs(int cs) {
		int i,j;
		int adrs[]=new int[2];
		for(i=0;i<5;i++) {
			for(j=0;j<5;j++) {
				if(cs==tab[i][j]) {
					adrs[0]=i;
					adrs[1]=j;
					return adrs;
				}
					 
			}
		}
		adrs=new int[0];
		return adrs;
	}
	private int checknearcs(boolean isleft,int cs) {
		//�鿴�ض�������Χ�Ƿ�������
		if((Math.abs(cs))==0||Math.abs(cs)>6) {
			return 0;
		}
		if (cs>0) {//people
			if (isleft) {
				if(checkcs(cs-1).length>0) {
					return 2;
				}else if(cs==1){//�鿴1�����
					return 0;
				}else {
					return checknearcs(isleft, cs-1);
				}
			}else {//���ұ�
				if(checkcs(cs+1).length>0) {
					return 2;
				}else if(cs==6){//�鿴1�����
					return 0;
				}else {
					return checknearcs(isleft, cs+1);
				}
			}
			
			
		}else if (cs<0) {//ai
			if (isleft) {//ai cs 's left
				if(checkcs(cs+1).length>0) {
					return 2;
				}else if(cs==-1){//�鿴-1�����
					return 0;
				}else {
					return checknearcs(isleft, cs+1);
				}
			}else {//���ұ�
				if(checkcs(cs-1).length>0) {
					return 2;
				}else if(cs==-6){//�鿴1�����
					return 0;
				}else {
					return checknearcs(isleft, cs-1);
				}
			}
		}
		
		
		
		return 0;
	}
	
	
	private int reflex() {
		//�����
		int point=1,rex=0;
		int[] adr=new int[2];
		for(;point<7;point++) {
			adr=checkcs(-1*point);
			if(adr.length!=0) {
				//���ڸõ��� ��ֱ�Ӷ�Ӧһ������
				rex+=2;
			}else {
				rex+=checknearcs(false, -1*point)+checknearcs(true, -1*point);
			}
		}
		int rival_rex=0;
		for(point=1;point<7;point++) {
			adr=checkcs(point);
			if(adr.length!=0) {
				//���ڸõ��� ��ֱ�Ӷ�Ӧһ������
				rival_rex+=2;
			}else {
				rival_rex+=checknearcs(false, point)+checknearcs(true, point);
			}
		}
		
		return rex-rival_rex;
	}

	public int Eva(boolean isme,int[][] table ){
		//isme=true ai����������ģ��ľ�������
		//isme=0 �ˣ���������ģ��ľ�������
		//eg. Eva(true,table)�����ж��Լ�ai�ߵ����ӵķ���
		//win =65536 lost = -65535
		
		tab = new int[5][5];
		//���
		int i,j;
		for(i=0;i<5;i++) for(j=0;j<5;j++) tab[i][j]=table[i][j];
		
		
		int res=0;
		if (table==null)
			return 0;
		if(isme==false) 
			return Eva(true, revert(table));
		
		//==========���
		//if()
		
		
		int mychess=ct_ai();
		int enemy=ct_me();
		if((mychess==0)||(tab[0][0]>0)) return -65535;
		if((enemy==0)||(tab[4][4]<0)) return 65536;
		
		
		//�������Ƶ�Ȩ��
		res+= 20*(mychess-enemy);
		
		int mywei[]=new int[7];
		int emwei[]=new int[7];
		//==============attack
		int adres[]=new int[2];
		
		//=========my
		//3 (3,1) 4(1,1) 5(0,2)
		//3 =1*12 4=1*12 5=0*12
		//Ȩֵ���ߵ���4��4�������ٲ��� 4-��
		for(i=-6;i<0;i++) {
			adres=checkcs(i);
			if(adres.length==0) mywei[-1*i]=0;
			else if(i==-1||i==-6) mywei[-1*i]=20*Math.min((adres[0]), (adres[1]))+10;
			else mywei[-1*i]=15*Math.min((adres[0]), (adres[1]))+10;
		}
		
		
		for(i=1;i<7;i++) res+=mywei[i];
		
		//==enm
		for(i=1;i<7;i++) {
			adres=checkcs(i);
			if(adres.length==0) mywei[i]=0;
			else if(i==1||i==6) emwei[i]=20*Math.min((4-adres[0]), (4-adres[1]))+10;
			else emwei[i]=15*Math.min((4-adres[0]), (4-adres[1]))+10;
		}
		for(i=1;i<7;i++) res-=emwei[i];
		
		
		
		//===============defense
		//check heart 
		if((tab[0][0]<-1)&&(tab[0][0]>-6))
			res+=40;
		if((tab[0][0]==-1)||(tab[0][0]==-6))
			res+=48;
		//check shell
		if((tab[0][1]<0)&&(tab[0][1]<0)) {
			res+=32;
		}else if((tab[0][1]<0)||(tab[0][1]<0)){
			res+=25;
		}
		
		
		if((tab[4][4]>1)&&(tab[4][4]<6))
			res-=40;
		if((tab[4][4]==-1)||(tab[4][4]==-6))
			res-=48;
		//check shell
		if((tab[4][3]<0)&&(tab[3][4]<0)) {
			res-=32;
		}else if((tab[4][3]<0)||(tab[3][4]<0)){
			res-=25;
		}
		
		
		
		//res-=25;
		
		int maxcs;//Ȩ����������,����
		int nxcs;
		for(i=maxcs=1;i<6;i++) {
			if(mywei[maxcs]<mywei[i+1]) {
				maxcs=i+1;
			}
		}
		
		adres=checkcs(-1*maxcs);
		if(adres.length>0) {
			if(adres[0]*adres[1]>0) {//���Ӳ��ڱ߽�
				int x=adres[0],y=adres[1];
				int fg=1;
				if(tab[x-1][y-1]==-1||tab[x-1][y-1]==-6) 
					res+=12;
				else if (tab[x-1][y-1]<0) {
					res-=1;
					fg =0;
				}else {
					res+=12;
				}
				if(fg==1) {//б����Ԯ��
					if(tab[x-1][y]<0||tab[x][y-1]<0) {
						res+=6;
						if((tab[x-1][y]<0)&&(tab[x][y-1]<0))
							res+=2;
					}
					
				}else {
					if(tab[x-1][y]<0||tab[x][y-1]<0) {
						res+=5;
						if((tab[x-1][y]<0)&&(tab[x][y-1]<0))
							res+=15;
					}
					
				}
		
			}else if(adres[0]<3||adres[1]<3){
				//weit chess at side 
				if(adres[0]*adres[1]==0)	
					res+=10*(3-Integer.max(adres[0], adres[1]));
			}else {
				res-=20;
			}
			//��������㷨
			//ǿ�Ӽ��
			int x=adres[0];
			int y=adres[1];
			if((adres[0]>=2)&&(adres[1]>=2)) {
				res+=10;
				//in ����3*3��
				int statx=0;
				while(x<4) {
					x++;
					while(y<4) {
						y++;
						if(tab[x][y]>0) {
							
							statx+=1;
						}
					}
				}
				while(x<4) {
					x++;
					while(y<3) {
						y++;
						if(tab[x][y+1]>0) {
							
							statx+=1;
						}
					}
				}
				while(x<3) {
					x++;
					while(y<4) {
						y++;
						if(tab[x+1][y]>0) {
							
							statx+=1;
						}
					}
				}
				res-=5*statx;
				/*
				int staty=0;
				x=adres[0];
				y=adres[1];
				while(y<5) {
					y++;
					while(x<5) {
						x++;
						if(tab[x][y]>0)
							staty=1;
					}
				}*/
				if(statx==0) {
					res+=10;
					for(i=1;i<7;i++) res-=mywei[i];//�����Լ����Ȩ��
					res-= 30*(mychess-enemy);
					
					res-=10*(mychess-enemy);//�Լ���1����20��
					
					res+=20*Math.min((adres[0]), (adres[1]));
					
				}
			}
			
			
			
			
		}
		
		
		
		if(mychess>1) {
			//��ڶ���
			int v;
			
			for(i=maxcs=nxcs=1;i<7;i++) {
				v=mywei[i];
				if(v>mywei[nxcs]) {
					if(v>=mywei[maxcs]) {
						nxcs=maxcs;//ԭ�����ֵ��ڶ���
						maxcs=i;//���ֵ����Ϊ��ǰֵ
					}else {
						nxcs = i;//��ǰֵΪ�ڶ���
					}
				}
			}
			
			adres=checkcs(-1*nxcs);
			if(adres.length!=0) {
				if(adres[0]*adres[1]>0) {//���Ӳ��ڱ߽�
					int x=adres[0],y=adres[1];
					int fg=1;
					if(tab[x-1][y-1]==-1||tab[x-1][y-1]==-6) 
						res+=6;
					else if (tab[x-1][y-1]<0) {
						res-=1;
						fg =0;
					}else {
						res+=6;
					}
					if(fg==1) {//б����Ԯ��
						if(tab[x-1][y]<0||tab[x][y-1]<0) {
							res+=3;
							if((tab[x-1][y]<0)&&(tab[x][y-1]<0))
								res+=1;
						}
						
					}else {
						if(tab[x-1][y]<0||tab[x][y-1]<0) {
							res+=3;
							if((tab[x-1][y]<0)&&(tab[x][y-1]<0))
								res+=8;
						}
						
					}
			
				}else  if(adres[0]<3||adres[1]<3){
					//weit chess at side 
					if(adres[0]*adres[1]==0)	
						res+=10*(3-Integer.max(adres[0], adres[1]));
				}else{
						res-=10;
					}	
			}
		}
		
		
		
		res+=reflex();
		
		return res;
	}
	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
    
}
