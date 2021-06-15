package linearregression1;


  public class DFspikes{

     LinRegGui gui;
     double Ao;//ref ampl
     double As; // DFsinewave ampl

     //alfa sampling steps in deg rather than time
     double alfa1,alfa2,alfa3,alfa4,alfa5,alfa6,alfa7,alfa8,alfa9;
     double alfa10,alfa11,alfa12,alfa13,alfa14,alfa15,alfa16;


     double beta; // df spikes phase deg
     double[] dfSpikes;   // demodulated spikes
     double[] refSpikes;  // ref spikes
     double[] y1;         // hold spikes temp
     double[] y2;         // df spikes

     double V1max;        //df spikes max val
     int V1maxIndex;

     double V2max;        //df spikes max val
     int V2maxIndex;


     double V1min;        //df spikes min val
     int V1minIndex;

     double V2min;        //df spikes min val
     int V2minIndex;

     int NoS; //num of spikes - 16 per period



  public DFspikes(LinRegGui gui,int NoS){

    this.gui=gui;
    this.NoS=NoS;
    dfSpikes  = new double[NoS];
    refSpikes = new double[NoS];
    y1= new double[NoS];
    y2= new double[NoS];
    beta=22.5; //bearing
    beta = Math.toRadians(beta);

    Ao=3.0;
    
    /*
	alfa1=11.25;    alfa1=Math.toRadians(alfa1);    // 0.585270
	alfa2=33.75;    alfa2=Math.toRadians(alfa2); 	 //1.666711
	alfa3=56.25;    alfa3=Math.toRadians(alfa3);	// 2.494409
	alfa4=78.75;    alfa4=Math.toRadians(alfa4);	// 2.942356
	alfa5=101.25;   alfa5=Math.toRadians(alfa5);	// 2.942356
	alfa6=123.75;	alfa6=Math.toRadians(alfa6);	// 2.494409
	alfa7=146.25;	alfa7=Math.toRadians(alfa7);	// 1.666711
	alfa8=168.75;	alfa8=Math.toRadians(alfa8);	// 0.585271
	alfa9=191.25;	alfa9=Math.toRadians(alfa9);	//-0.585270
	alfa10=213.75;	alfa10=Math.toRadians(alfa10);	//-1.666711
	alfa11=236.25;	alfa11=Math.toRadians(alfa11);	//-2.494409
	alfa12=258.75;	alfa12=Math.toRadians(alfa12);	//-2.942356
	alfa13=281.25;	alfa13=Math.toRadians(alfa13);	//-2.942356
	alfa14=303.75;	alfa14=Math.toRadians(alfa14);	//-2.494409
	alfa15=326.25;	alfa15=Math.toRadians(alfa15);	//-1.666711
	alfa16=348.75;	alfa16=Math.toRadians(alfa16);	//-0.585270
*/

    setRefSpikeArray();
    printRefSpikes();
    printAlfa();
    System.out.println("");
    setUpDFspikeArray(beta);
    printDfSpikes();
    System.out.println("");
    findMaxValueSpikes();
    randomizeSpikes();

  }

//reference sinewave spikes no bearing
  public void setRefSpikeArray_old(){


	  y1[1]=Ao*Math.sin(alfa1);
	  y1[2]=Ao*Math.sin(alfa2);
	  y1[3]=Ao*Math.sin(alfa3);
	  y1[4]=Ao*Math.sin(alfa4);
	  y1[5]=Ao*Math.sin(alfa5);
	  y1[6]=Ao*Math.sin(alfa6);
	  y1[7]=Ao*Math.sin(alfa7);
	  y1[8]=Ao*Math.sin(alfa8);
	  y1[9]=Ao*Math.sin(alfa9);
	  y1[10]=Ao*Math.sin(alfa10);
	  y1[11]=Ao*Math.sin(alfa11);
	  y1[12]=Ao*Math.sin(alfa12);
	  y1[13]=Ao*Math.sin(alfa13);
	  y1[14]=Ao*Math.sin(alfa14);
	  y1[15]=Ao*Math.sin(alfa15);
	  y1[16]=Ao*Math.sin(alfa16);




  }
  public void setRefSpikeArray(){

    double alfa=0.0; double alfaRad=0.0;
    alfa=11.25;    alfaRad=Math.toRadians(alfa);    // 0.585270

    for (int i=1;i<NoS;i++){
        System.out.println("angle="+alfa);
        y1[i]=Ao*Math.sin(alfaRad);
        alfa=alfa+22.5;
        alfaRad=Math.toRadians(alfa);
      }
  }
 // generate received DF spikes y2[] alfa=wt in deg 11.25 33.75 56.25... beta pahse shift / bearing
  //brg in degrees
  public void setUpDFspikeArray(double brg){
    System.out.println("setUpDFspikeArray() brg="+brg);
    double alfa=11.25; double alfaRad=0.0;
    alfa=alfa+brg;
    alfaRad=Math.toRadians(alfa);    // 0.585270


    for (int i=1;i<NoS;i++){
        //System.out.println("y2 alfa="+alfa);
        y2[i]=Ao*Math.sin(alfaRad);
         System.out.println(i+"\t"+alfa+"\t"+alfaRad+"\t"+y2[i]);
        alfa=alfa+22.5;
        alfaRad=Math.toRadians(alfa);

    }
  }
  public void randomizeSpikes(){
    if (gui.checkBox_noise.isSelected()){
        double r=.01;
        for (int i=1; i<17;i++){
            if(i%2==0){y1[i]=y1[i]-r*Math.random();}else{
             y1[i]=y1[i]+r*Math.random();
            }
        }
    }
 }


  public void printRefSpikes(){

	  for(int i=1;i<=16;i++){

		  System.out.println("y1["+i+"]="+y1[i]);
	  }

  }

// received DF spikes y2[] alfa=wt in deg 11.25 33.75 56.25... beta pahse shift / bearing
  public void setUpDFspikeArray_old(double brg){

          beta=Math.toRadians(brg);
	  y2[1]=Ao*Math.sin(alfa1+beta);
	  y2[2]=Ao*Math.sin(alfa2+beta);
	  y2[3]=Ao*Math.sin(alfa3+beta);
	  y2[4]=Ao*Math.sin(alfa4+beta);
	  y2[5]=Ao*Math.sin(alfa5+beta);
	  y2[6]=Ao*Math.sin(alfa6+beta);
	  y2[7]=Ao*Math.sin(alfa7+beta);
	  y2[8]=Ao*Math.sin(alfa8+beta);
	  y2[9]=Ao*Math.sin(alfa9+beta);
	  y2[10]=Ao*Math.sin(alfa10+beta);
	  y2[11]=Ao*Math.sin(alfa11+beta);
	  y2[12]=Ao*Math.sin(alfa12+beta);
	  y2[13]=Ao*Math.sin(alfa13+beta);
	  y2[14]=Ao*Math.sin(alfa14+beta);
	  y2[15]=Ao*Math.sin(alfa15+beta);
	  y2[16]=Ao*Math.sin(alfa16+beta);
  }
    public void printDfSpikes(){

  	  for(int i=1;i<=16;i++){

  		  System.out.println("y2["+i+"]="+y2[i]);
  	  }

    }

  //get max Value DFspikes index

  public void findMaxValueSpikes(){

      V1max=y2[1];V2max=y2[1];
      V1min=y2[1];V2min=y2[1];

      double V=0;

     //find first max spike
  	  for(int i=1;i<=16;i++){

          V=y2[i]; if (V>V1max) { V1max=V;  V1maxIndex=i; }

  	  }

  	  System.out.println("V1max="+V1max+" index="+V1maxIndex);


  	 //find second max spike
    	  for(int i=1;i<=16;i++){

            if(i!=V1maxIndex){

               V=y2[i]; if (V>V2max) { V2max=V; V2maxIndex=i;}
	        }

  	    }

  	   System.out.println("V2max="+V2max+" index="+V2maxIndex);
  }

public void printAlfa(){
	System.out.println("alfa1="+alfa1);
	System.out.println("alfa2="+alfa2);
	System.out.println("alfa3="+alfa3);
	System.out.println("alfa4="+alfa4);
	System.out.println("alfa5="+alfa5);
	System.out.println("alfa6="+alfa6);
	System.out.println("alfa7="+alfa7);
	System.out.println("alfa8="+alfa8);
	System.out.println("alfa9="+alfa9);

	System.out.println("alfa10="+alfa10);
	System.out.println("alfa11="+alfa11);
	System.out.println("alfa12="+alfa12);
	System.out.println("alfa13="+alfa13);
	System.out.println("alfa14="+alfa14);
	System.out.println("alfa15="+alfa15);
	System.out.println("alfa16="+alfa16);



}
//gets DF spikes phase shift in deg
public void getBeta(double B){
    beta=Math.toRadians(B);
}
/*
  public static void main(String[] args) {

		DFspikes spt= new DFspikes1();

  }
*/
    }