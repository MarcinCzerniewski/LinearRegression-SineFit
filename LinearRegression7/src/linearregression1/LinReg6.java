/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linearregression1;

/**
 *
 * @author house
 */
public class LinReg6 {
     
    int NoS;
    int k,N; //numberOfDataPoints
    int M; //numberOfCoefficients
    double R; //maxinmum error

    double[] X; // input
    double[] Y; // output
    double[] A; // function coefficients
    double[] D;

    double x,y; //input output  value

    double R1,R2,R3,R8,R9;
    double A1,A2,A3,A4,A5,A6,d,N7,N8;

    boolean flag1,flag2;
 
    public LinReg6(int NoS){
        
        this.NoS=NoS;
        System.out.println("constructor LinReg6.class");
        X=new double[100];
        Y=new double[100];
        Y[49]=1.2;
        System.out.println("Y[49]="+Y[49]);
        A=new double[50];
        D=new double[50];
        x=0.0; y=0.0;
        flag1=false;
        flag2=false;
        
        //setSpikesValues1();

        N=6; R=.01;M=2; R1=0;R2=0;
        //est coeffs
        A[1]=1.0; D[1]=A[1]/10.0;
        A[2]=1.0; D[2]=A[2]/10.0;
        A[3]=1.0; D[3]=A[3]/10.0;

        //System.out.println("D1="+D[1]);
        //System.out.println("D2="+D[2]);
       // addNoise();
        //doIteration();
    }
    //get DF spikes from DFspikes.class
    public void getDFspikes(double[] spikes){
        
        Y=spikes; int L=spikes.length;
        System.out.println("LinReg6.class spikes Y from DFspikes.class L="+L);
        for (int i=1;i<L;i++){
         System.out.println("i="+i);   
        System.out.println(i +" "+ Y[i]);
    }
    }
    public double  getSineAmplitude(){
    return A[1];
}
    //returns phase in rad
    public double  getPhase(){
        System.out.println("phase="+Math.toDegrees(A[2]));
        return A[2];
    }
    public void clearVariables(){
        
        k=0;N=16;
        M=2; //numberOfCoefficients
        R=0.01;
        x=0;y=0;
        R1=R2=R3=R8=R9=0;
        A1=A2=A3=A4=A5=A6=d=N7=N8=0;
        A[1]=1.0; D[1]=A[1]/10.0;
        A[2]=1.0; D[2]=A[2]/10.0;
        A[3]=1.0; D[3]=A[3]/10.0;
        flag1=false;flag2=false;

    }
    //df spikes vs wt=alfa=X[i]
    public void setUpAngleValues(){

        X[1]=11.25;   X[2]=33.75;   X[3]=56.25;    X[4]=78.75;
        X[5]=101.25;  X[6]=123.75;  X[7]=146.25;   X[8]=168.75;
        X[9]=191.25;  X[10]=213.75; X[11]=236.25;  X[12]=258.75;
        X[13]=281.25; X[14]=303.75; X[15]=326.25;  X[16]=348.75;
        for(int j=1;j<=16;j++){X[j]=Math.toRadians(X[j]);}
      
           X[1]=0.19634954084936207; //11.25 deg; 22.5 deg 0.39269908169872414rad
        //   System.out.println("11.25 "+Math.toRadians(11.25));
        //    System.out.println("22.5 "+Math.toRadians(22.5));
        for (int i=2;i<NoS;i++){
         
            X[i]=X[i-1]+0.39269908169872414;
            
            System.out.println("--X["+i+"]="+X[i]);
        }
      
        System.out.println("--X["+1+"]="+X[1]+" N="+N);
      
    }
    public void setSpikesValues1(){
         
        Y[1]=1.666711;
        Y[2]=2.494409;      Y[3]=2.942356;
        Y[4]=2.942356;      Y[5]=2.494409;
        Y[6]=1.666711;      Y[7]=0.585271;
        Y[8]=-0.585270;     Y[9]=-1.666711;
        Y[10]=-2.494409;    Y[11]=-2.942356;
        Y[12]=-2.942356;    Y[13]=-2.494409;
        Y[14]=-1.666711;    Y[15]=-0.585270;
        Y[16]=0.585270;
/*
//A1x^2+A2
     Y[1]=5;  Y[2]=11;      Y[3]=21;
     Y[4]=35;      Y[5]=53; Y[6]=75;

//A1x^2+A2x
     Y[1]=5;  Y[2]=14;      Y[3]=27;
     Y[4]=44; Y[5]=65;      Y[6]=90;
*/

}
    public void randomizeSpikes(String noise,int NoS){
        
        double Noise=Double.parseDouble(noise);
      for(int J=1;J<NoS;J++){

	  System.out.print(J+"\t"+"  "+Y[J]+"\t");
          int Yampl=(int)(Y[J]+Y[J]*(Math.random()-0.5)*Noise/100.00);
          Y[J]=Yampl;
	  //Y[J]=Y[J]+3.0* Math.random()-0.5;
	  System.out.println("  "+Y[J]);
	 // System.out.println("");
  }

}
    public void addNoise(){
 
     //randomize
        System.out.println("ADD NOISE J  y");
     for(int J=1;J<=16;J++){

             System.out.println(J+"\t"+"  "+Y[J]+"\t");
             Y[J]=Y[J]+2*Math.random()-1.05;
             
             //System.out.println("  "+Y[J]);
             //System.out.println("Math.random()="+Math.random());
     }

     for(int J=1;J<=16;J++){X[J]=Math.toRadians(X[J]);}   
   }    
    // START ITERATION

    public void doIteration(){

	for ( k=1;k<=M;M++){

		  sub1000();
		  R1=R8;
		  N7=0;
		  N8=0;
		  d=D[k];
		  A[k]=A[k]+d;
		  sub1000();
          R2=R8;

		 if(R1<=R2){

			d=-d;
		    A[k]=A[k]+d;
		    R9=R1;
		    R1=R2;
		    R2=R9;

		 }


       flag1=true;
       while(flag1){

	     flag1=false;
	     N7=N7+1;
	     N8=N8+1;
	     A[k]=A[k]+d;
	     sub1000();
	     R3=R8;

         if(R3>R2){

            //System.out.println("R3>R2");
	        A[k]=A[k]-d*(1/(1+(R1-R2)/(R3-R2))+.5);
            D[k]=D[k]*N7/5.0;

           // System.out.println("R3>R2 Ak="+A[k]+" Dk="+D[k]+" k="+k);//ok
            k=k+1;if(k>2)k=1;


	     } else{

			       if(R3<=R2){

                       //System.out.println("R3<=R2");

     				   R1=R2;  R2=R3;

		               if (R1==R2&&R2==R3) { sub750();return;}

				       if(N8<10){ flag1=true;  }


				       if(N8>=10){ D[k]=D[k]*10.0;}
				   }


			   }//else of R3>R2

      }//end while


	 }//end k loop



}
    // end iteration print results
	public void sub750(){

	//for (int k=1;k<=M;M++){

		//System.out.println("coeff A"+k+"="+A[k]);
	//}
System.out.println("sub750 coeff A1="+A[1]);
System.out.println("sub750 coeff A2="+Math.toDegrees(A[2]));
	System.out.println("ROOT MEAN SQUARE ERROR="+R8+"\n");


	}
    //REM SUB FOR STANDARD DEVIATION
public void sub1000(){
	
/*
1000 REM SUB FOR STANDARD DEVIATION
1010 R8=0
1020 FOR J=1 TO N
1030 A1=A[1]
1040 A2=A(2)
1050 A3=A[3]
1060 A4=A[4]
1070 A5=A[5]
1080 X=X[J]
1090 REM  ENTER EQN AT 1100
1100 Y=A1*SIN(X+A2)
1110 R9=Y-Y[J]
1120 R8=R8+R9^2
1130 NEXT J
1140 R8=SQR(R8/N)
1150 RETURN
*/
 R8=0.0;

for (int j=1;j<=N;j++){

  A1=A[1];A2=A[2]; A3=A[3]; A4=A[4]; A5=A[5]; x=X[j];

  //  ENTER EQN here
  //y=A1*Math.sin(x+A2);
  y=A1*Math.sin(x+A2);
  R9=y-Y[j];
  R8=R8+R9*R9;

 // System.out.println("y="+y);
 // System.out.println("R9="+R9);

}

R8=Math.sqrt(R8/(double)N);
//System.out.println("R8="+R8+" count="+sub1000count);


}
}
