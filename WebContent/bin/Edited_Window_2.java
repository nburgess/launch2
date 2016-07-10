//applet
import javax.swing.JApplet;
import java.awt.*;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javax.swing.Timer;
import javax.swing.border.Border;

public class Edited_Window_2 extends JApplet
{
	/**
	 * TODO:
	 *  Window 1 and Window 2 need to be linked correctly
	 *  Design a Section to ease out the experimental stages of the CA Model
	 *  Implement variable Layer Size with rounded edges
	 */
	private static final long serialVersionUID = 1L;

	final int DEFAULT_STEP = 2;
	final int TIME_STEP = 800;
	final int DEFAULT_LAYERS=20;
	final int DEFAULT_LAY1=1;
	final int DEFAULT_LAY2=5;
	final int DEFAULT_LAY3=10;
	final int DEFAULT_LAY4=20;
	final int DEFAULT_ROWS[] = {100, 300};
	final int DEFAULT_COLS[] = {100, 400};
	final int DEFAULT_ITER[] = {150 , 40 };

	JButton btnRunSimulation;
	private static int s_m = 0;
	public Graphics2D drawImage;
	static Canvas canvas;
	static Canvas canvas_1;
	static Canvas canvas_2;
	static Canvas canvas_3;

	private static float Pixels[][][];//3d Matrix of pixels
  /*******************/
  private static double frequency;
  private static double pressure[][][];//pressure

  private static double [] normYoungsMod;
  private static double [] normThickness;

  /*******************/
	private static int Lay1;
	private static int Lay2;
	private static int Lay3;

  private static int Rows;//x rows of pixel
	private static int Cols;//y cols of pixel
	private static int Layers;//z layer of pixel

	public static long current_time= 0;
	public static long prev_time = 0;
	static double ii =0;
	Timer timer;
	JLabel number = new JLabel("0.00");
	//public static JFrame frmTissueInflammationSimulation = new JFrame();
	public int border_flag = 1;
	public int layer_shift = 50;

	/**
	 * Launch the application.
	 */
	 //public ImageMatrix2 mtrx;
	public void init(){
			final double omega = 13;
			final double normalizedPressure = 1;
			final double youngsMod1 = 1;
			final double youngsMod2 = 1;
			final double youngsMod3 = 1;
			final double thickness1 = 2;
			final double thickness2 = 2;
			final double thickness3 = 2;
			final int layers = 3;
	    //mtrx = new ImageMatrix2(100, 100, layers, omega, normalizedPressure, youngsMod1, youngsMod2, youngsMod3, thickness1, thickness2, thickness3);
			ImageMatrix2(100, 100, layers, omega, normalizedPressure, youngsMod1, youngsMod2, youngsMod3, thickness1, thickness2, thickness3);

			BorderLayout borderLayout = new BorderLayout(8,8);
			setLayout(borderLayout);
			setSize(2000,900);


			//add(mtrx);


		//	p1 = new Panel(new BorderLayout(2,2));
			//p1.setSize(100,getSize().height);
			//add(p1,"East");
			//p2 = new Panel(new BorderLayout(2,2));
			//p2.setSize(100,getSize().height);
			//add(p2,"West");
			/**
						 * Creating Layer 1 for the simulation window
						 */
						JLabel layerLabel_1 = new JLabel("Layer 1");
						layerLabel_1.setPreferredSize(new Dimension(149, 25));
						layerLabel_1.setForeground(new Color(0, 128, 128));
						layerLabel_1.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 17));
						layerLabel_1.setBounds(715, 252, 228, 26);
						add(layerLabel_1);

						/**
						 * Creating Layer 2 for the simulation window
						 */
						JLabel layerLabel_2 = new JLabel("Layer 2");
						layerLabel_2.setPreferredSize(new Dimension(149, 25));
						layerLabel_2.setForeground(new Color(0, 128, 128));
						layerLabel_2.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 17));
						layerLabel_2.setBounds(515, 290, 228, 26);
						add(layerLabel_2);

						/**
						 * Creating Layer 3 for the simulation window
						 */
						JLabel layerLabel_3 = new JLabel("Layer 3");
						layerLabel_3.setPreferredSize(new Dimension(149, 25));
						layerLabel_3.setForeground(new Color(0, 128, 128));
						layerLabel_3.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 17));
						layerLabel_3.setBounds(300, 335, 228, 26);
						add(layerLabel_3);

	}
	public void run(){
		prev_time = System.currentTimeMillis();
		ii=0;
	//	final JApplet pnlBoxes = new Edited_Window_2;
	//	pnlBoxes.setName("Tissue Inflammation Simulation");
	//	pnlBoxes.setSize(new Dimension(2000, 900));
		for(int g = 0;g<150;g++)
		{
			ActionListener actionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					{
						ii++;
						{
							/**
						 	* Simulate the corrosion model by painting the layers
						 	*/
							simulate();
						}
					}
					//pnlBoxes.paint(pnlBoxes.getGraphics());
					repaint();
				}
			};

			Timer timer = new Timer(1000, actionListener );
			timer.start();
		}
	}
/*	public static void Show (final double omega, final double normalizedPressure,
                           final double youngsMod1, final double youngsMod2, final double youngsMod3,
                           final double thickness1, final double thickness2, final double thickness3, final int layers) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {


					final JApplet pnlBoxes = new Edited_Window_2();

					pnlBoxes.setName("Tissue Inflammation Simulation");
					pnlBoxes.setSize(new Dimension(2000, 900));

					frmTissueInflammationSimulation.getContentPane().add(pnlBoxes);
					frmTissueInflammationSimulation.setVisible(true);


					/**
					 * Function Call to to create ImageMatrix
					 * Function Description outlined below
					 */
  /*        ImageMatrix(100, 100, layers, omega, normalizedPressure, youngsMod1, youngsMod2, youngsMod3, thickness1, thickness2, thickness3);
          ii = 0;
					for(int g = 0;g<150;g++)
					{
						ActionListener actionListener = new ActionListener() {
							public void actionPerformed(ActionEvent actionEvent) {
								{
									ii++;
									{
										/**
										 * Simulate the corrosion model by painting the layers
										 */
		/*								simulate();
									}
								}
								pnlBoxes.paint(pnlBoxes.getGraphics());
							}
						};

						Timer timer = new Timer(1000, actionListener );
						timer.start();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
 	}
*/
	/**
	 * Create the application.
	 */

	public Edited_Window_2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

	}

	/**
	 * Java call to Paint the layer windows
	 */
	public void paint(Graphics g) {

		int Rows = 100;
		int Cols = 100;
		int f = 2;

		int r = 0;
		int gr = 0;
		int b = 0;
		int r2 = 0;
		int gr2 = 0;
		int b2 = 0;
		int r3 = 0;
		int gr3 = 0;
		int b3 = 0;
		int r4 = 0;
		int gr4 = 0;
		int b4 = 0;

		//frmTissueInflammationSimulation.repaint();
		repaint();
		{

			for(int i=0;i<Rows;i++)

			{

				for(int j=0;j<Cols;j++)

				{

					if (true)

					{

						/**
						 * Fill the 'border outline' for the layers
						 */
						if ((i==0 ||i==Rows-1|| j==0 || j==Cols-1) && (border_flag == 1))

						{
							border_flag = 1;
							g.setColor(new Color(255,255,255));

							/**
						     * Fills the specified rectangle.
						     * The rectangle is filled using the graphics context's current color.
						     *        x   the <i>x</i> coordinate
						     *                         of the rectangle to be filled.
						     *        y   the <i>y</i> coordinate
						     *                         of the rectangle to be filled.
						     *          width    the width of the rectangle to be filled.
						     *          height   the height of the rectangle to be filled.
                                                     * fillRect(int x-coordinate, int y-coordinate, int width, int height)
						     * create four layers border with white color
                                                     */
							g.fillRect((int)(j*f+10),(int)(i*f+2*Rows-130-(-115)),f,f);

							g.fillRect((int)(j*f+10 + 1.6* Cols+50),(int)(i*f+1.33*Rows-40-(-45)),f,f);

							g.fillRect((int)(j*f + 3.2 *Cols+10+100),(int)(i*f+50 + 0.67*Rows-(25)),f,f);

							g.fillRect((int)(j*f+4.8*Cols+10+150), (int)(i*f+150-(95)),f,f);

						}

						/**
						 * Fill the rectangles for all of our 4 layers
						 */
						else
						{
							/**
							 * Each layer consists of "layer_size" pixels (imagine it as a small rectangle)
							 * Simulation 'repaints' each particular pixel value
							 * depending on the parameter settings of the Cellular Automata
							 */

							jetR(Pixels[i][j][Lay3]);
							g.setColor(new Color(r2,gr2, b2));
							g.setColor(new Color(jetR(Pixels[i][j][Lay3]), jetG(Pixels[i][j][Lay3]), jetB(Pixels[i][j][Lay3])));
							g.fillRect((int)(j*f+10 + 1.6* Cols+50),(int)(i*f+1.33*Rows-40-(-45)),f,f);
							// UPPER ARC

							jetR(Pixels[i][j][Lay2]);
							g.setColor(new Color(r3,gr3, b3));
							g.setColor(new Color(jetR(Pixels[i][j][Lay2]), jetG(Pixels[i][j][Lay2]), jetB(Pixels[i][j][Lay2])));
							g.fillRect((int)(j*f + 3.2 *Cols+10+100),(int)(i*f+50 + 0.67*Rows-(25)),f,f);
							// UPPER ARC

							jetR(Pixels[i][j][Lay1]);
							g.setColor(new Color(r4,gr4,b4));
							g.setColor(new Color(jetR(Pixels[i][j][Lay1]), jetG(Pixels[i][j][Lay1]), jetB(Pixels[i][j][Lay1])));
							g.fillRect((int)(j*f+4.8*Cols+10+150), (int)(i*f+150-(95)),f,f);
							// UPPER ARC

							//UPPER ARC
							g.setColor(new Color(255,255,215));
						}
					}
				}
			}
		}
	}
	/**
	 * Method for the class Image Matrix
	 * Initialize the pixel values, number of rows/columns, number of layers
	 * Also specifies the parameter values for the Cellular Automata
	 */
	public static void ImageMatrix2(int rows, int cols, int layers, double omega, double normalizedPressure,
                                 double youngsMod1, double youngsMod2, double youngsMod3,
                                 double thickness1, double thickness2, double thickness3)
{
    Rows = rows;
		Cols = cols;
		Layers = layers;
		Lay1 = (int) thickness1;
		Lay2 = (int) thickness2;
		Lay3 = (int) thickness3;
    frequency=omega;
    double thicknessSum=thickness1+thickness2+thickness3;
    double normThickness1=thickness1/(thicknessSum);
    double normThickness2=thickness2/(thicknessSum);
    double normThickness3=thickness3/(thicknessSum);
    double youngsModComposite=(normThickness1*youngsMod1)+(normThickness2*youngsMod2)+(normThickness3*youngsMod3);

    double normYoungsMod1=(youngsMod1/youngsModComposite);
    double normYoungsMod2=(youngsMod2/youngsModComposite);
    double normYoungsMod3=(youngsMod3/youngsModComposite);

    normYoungsMod= new double [Layers];
		normThickness= new double [Layers];

		int lay1End = (int) (Layers * normThickness1);
		int lay2End = (int) ((Layers * normThickness2) + lay1End);
		int lay3End = Layers;
		for(int i=0; i < lay1End; i++)
		{
			normYoungsMod[i]=normYoungsMod1;
			normThickness[i] = normThickness1;
		}
		for(int i=lay1End; i < lay2End; i++)
		{
			normYoungsMod[i]=normYoungsMod2;
			normThickness[i] = normThickness2;
		}
		for(int i=lay2End; i < lay3End; i++)
		{
			normYoungsMod[i]=normYoungsMod3;
			normThickness[i] = normThickness3;
		}

		/**
		 * Define the parametric value matrices for our Cellular Automata
		 */
		//The class here is the 3-dimensional array of floats,doubles, etc.
		//Instantiating the arrays.
		Pixels = new float[Rows][Cols][Layers];
    pressure = new double[Rows][Cols][Layers];

		/**
		 * Populate the parametric value matrices from the initial settings specified by the user
		 * Each experiment varies these initial settings and we aim to optimize the parameters for our Cellular Automata
		 */
		for(int i = Rows-1; i >= 0; i--)
			for(int j = Cols-1; j >= 0; j--)
				for (int k=Layers-1; k>=0; k--)
				{
					Pixels[i][j][k]=0;
          pressure[i][j][k]=normalizedPressure;

				}
	}
	/**
	 * Setting the 'Red' Color values for our jet stream to be used while repainting the layers
	 * The animation of the layer simulation displays the corrosion on each layer by painting the affected pixels with streams of RGB
	 * Essentially this function type casts the pixel values coming into the function call and depending on the rules, returns out a
	 * particular combination of RGB values that spray each layer depicting corrosion
	  * 0-31 return 0
         * 32-95 return <1
         * 96-159 return 1
         * 160-223 return <1
	 */
	private float jetR(double v)
	{

		int c = (int)v;

		if(c<96) return 0;

		else if(c<160) return (((float)c)-95)/64;

		else if(c<224) return 1;

		else return (288-((float)c))/64;

	}


	/**
	 * Setting the 'green' Color values for our jet stream to be used while repainting the layers
	 * The animation of the layer simulation displays the corrosion on each layer by painting the affected pixels with streams of RGB
	 * Essentially this function type casts the pixel values coming into the function call and depending on the rules, returns out a
	 * particular combination of RGB values that spray each layer depicting corrosion
	   * 0-31 return 0
         * 32-95 return <1
         * 96-159 return 1
         * 160-223 return <1
	 */
	private float jetG(double v)

	{

		int c = (int)v;

		if(c<32) return 0;

		else if(c<96) return (((float)c)-31)/64;

		else if(c<160) return 1;

		else if(c<224) return (224-((float)c))/64;

		else return 0;

	}
	/**
	 * Setting the 'blue' Color values for our jet stream to be used while repainting the layers
	 * The animation of the layer simulation displays the corrosion on each layer by painting the affected pixels with streams of RGB
	 * Essentially this function type casts the pixel values coming into the function call and depending on the rules, returns out a
	 * particular combination of RGB values that spray each layer depicting corrosion
	   * 0-31 return 0
         * 32-95 return <1
         * 96-159 return 1
         * 160-223 return <1
	 */
	private float jetB(double v)

	{

		int c = (int)v;

		if(c<32) return (((float)c)+33)/64;

		else if(c<96) return 1;

		else if(c<160) return (160-((float)c))/64;

		else return 0;

	}

	/**
	 * Apply the Cellular Automata to our Corrosion model using the parameters defined by the user
	 * The birth rate, death rate, transition rate and the growth rate are calculated based on the parameter settings (ph, temp, pot, concen)
	 * Apply the CA rules and propagate the corrosion in our model
	 */
	private static double applyCA(float unw, float un, float une, float uw, float uc, float ue, float usw, float us, float use, float nw, float n, float ne, float w, float c, float e, float sw, float s, float se, float lnw, float ln, float lne, float lw, float lc, float le, float lsw, float ls, float lse, double P, double E, double T, int kk)

	{
    double d;
		double re=0;
		/**
		 * These are the rules defined for our Cellular Automata algorithm
		 * Modifying these rules will change our model and propagate the corrosion accordingly
		 */
                double rnd1 = Math.random()*(3 - 1) + 1;
                double rnd2 = Math.random()*(3 - 1) + 1;
                double rnd3 = Math.random()*(3 - 1) + 1;
                double rnd4 = Math.random()*(3 - 1) + 1;

                double k1= rnd1*P*E*Math.pow(T,-1);
                double k2= rnd2*P*E*Math.pow(T,-1);
                double k3= rnd3*P*E*Math.pow(T,-1);
                double k4= rnd4*P*E*Math.pow(T,-1);

                double bottom = Math.sin(frequency*(c+lnw)) + Math.sin(frequency*(c+lsw)) + Math.sin(frequency*(c+lse))+ Math.sin(frequency*(c+lne)) + Math.sin(frequency*(c+lw)) + Math.sin(frequency*(c+ls)) + Math.sin(frequency*(c+le)) + Math.sin(frequency*(c+ln));

                double top = Math.sin(frequency*(c+unw)) + Math.sin(frequency*(c+usw)) + Math.sin(frequency*(c+use))+ Math.sin(frequency*(c+une)) + Math.sin(frequency*(c+uw)) + Math.sin(frequency*(c+us)) + Math.sin(frequency*(c+ue)) + Math.sin(frequency*(c+un));

                double corrosion_c = Math.sin(frequency*(c+w)) + Math.sin(frequency*(c+n)) + Math.sin(frequency*(c+e))+ Math.sin(frequency*(c+s)) + Math.sin(frequency*(c+uc)) + Math.sin(frequency*(c+lc));

                double corrosion_d = Math.sin(frequency*(c+nw)) + Math.sin(frequency*(c+sw)) + Math.sin(frequency*(c+ne))+ Math.sin(frequency*(c+se)) + top + bottom;

                double corrosion_x=Math.sin(frequency*c);
                double growthmodel= c + k1*corrosion_x + k2*corrosion_c+ k3*corrosion_d+k4;

                /**
                 * growth model =  0.054
		 * Calculate the relevant probabilities of the birth rate, death rate etc.
		 */
		double birthprob=0.00405;
		double deathprob=0.263;
		double tranrate=1.184;
		double growthrate=1.043;
               // System.out.println(growthmodel);
        	/**
		 * These are the rules defined for our Cellular Automata algorithm
		 * Modifying these rules will change our model and propagate the corrosion accordingly
		 */
		int in=0, in1=0;
		int dn=0, dn1=0;

		if (e>=1) in++;
		if (w>=1) in++;
		if (s>=1) in++;
		if (n>=1) in++;

		if (nw>=1) dn++;
		if (ne>=1) dn++;
		if (sw>=1) dn++;
		if (se>=1) dn++;

		if (e>=20) in1++;
		if (w>=20) in1++;
		if (s>=20) in1++;
		if (n>=20) in1++;

		if (nw>=20) dn1++;
		if (ne>=20) dn1++;
		if (sw>=20) dn1++;
		if (se>=20) dn1++;

		if (ue>=1) in++;
		if (uw>=1) in++;
		if (us>=1) in++;
		if (un>=1) in++;

		if (unw>=1) dn++;
		if (une>=1) dn++;
		if (usw>=1) dn++;
		if (use>=1) dn++;
		if (uc>=1) dn++;


		if (ue>=20) in1++;
		if (uw>=20) in1++;
		if (us>=20) in1++;
		if (un>=20) in1++;

		if (unw>=20) dn1++;
		if (une>=20) dn1++;
		if (usw>=20) dn1++;
		if (use>=20) dn1++;
		if (uc>=20) dn1++;


		if (le>=1) in++;
		if (lw>=1) in++;
		if (ls>=1) in++;
		if (ln>=1) in++;

		if (lnw>=1) dn++;
		if (lne>=1) dn++;
		if (lsw>=1) dn++;
		if (lse>=1) dn++;
		if (lc>=1) dn++;


		if (le>=20) in1++;
		if (lw>=20) in1++;
		if (ls>=20) in1++;
		if (ln>=20) in1++;

		if (lnw>=20) dn1++;
		if (lne>=20) dn1++;
		if (lsw>=20) dn1++;
		if (lse>=20) dn1++;
		if (lc>=20) dn1++;

		d = Math.random();//Math.random() returns a number from 0.0 to 1.0.
		if (c<1)
		{
			if ((((d<=birthprob || (in+dn)>=4)&& kk==0) || (in+dn)>=8))
			{
				re=1;
				return re;
			}
			if (d>birthprob)
			{
				re=0;
			}
			return re;
		}

		if (c>=1 && c<=4)
		{
			if (d<deathprob && (in+dn)<=6)
			{
				re=0;
				return re;
			}
		}

		if (c>=1 && c<=20)
		{
			re=c + tranrate;
			return re;
		}

		if (c>20)
		{
			//re=c+(int) (growthrate * (1+0.1*in1+0.05+dn1));
                        re = growthmodel;
		}

		if (re>255)
		{
			re=255;
		}
               //test
                if (re<0)
		{
			re=255;
		}
		//test
                return re;


	}

	/**
	 * Method call to SIMULATE the corrosion model
	 * This function takes in parameter values from the user settings -- ph, pot, temp, conc, diffus, charge, ii, pc, tc, potc, cc
	 * Depending on the range of these parameters (phmax, tmax etc.)
	 * It creates the tmp_matrix for each layer completely
         * @return string
	 */
	public static String simulate()
	{
		//Everything is initialzed to zero
		float tmp_mtrx[][][] = new float[Rows+2][Cols+2][Layers+2];

    double pressureMin = 9.3, pressureMax = 19.3;

		double dP = 0;

		double temp1;
		int ini=0;
		int dea=0;
		int gro=0;
		int sat=0;


		/**
		 * Create the tmp_matrix for each Layer twice
		 */
		for(int times = 0; times<2; times++)
		{
			for(int i=0; i<Rows; i++)
			{
				for(int j=0; j<Cols; j++)
				{
					for (int k=0; k<Layers; k++)
					{
						tmp_mtrx[i+1][j+1][k+1]=Pixels[i][j][k];
					}
				}
			}
			//Is not needed.
			for(int j=0; j<Cols+2; j++)
			{
				for (int k=0; k<Layers+2; k++)
				{
					tmp_mtrx[0][j][k]=0;
					tmp_mtrx[Rows+1][j][k]=0;
				}
			}
			for(int i=0; i<Rows+2; i++)
			{
				for (int k=0; k<Layers+2; k++)
				{
					tmp_mtrx[i][0][k]=0;
					tmp_mtrx[i][Cols+1][k]=0;
				}
			}
			for(int i=0; i<Rows+2; i++)
			{
				for (int j=0; j<Cols+2; j++)
				{
					tmp_mtrx[i][j][0]=0;
					tmp_mtrx[i][j][Layers+1]=0;
				}
			}
			/**
			 * Once you populate the tmp_matrix
			 * Calculate the dh, dp, dc, dt values for each cell of the Matrix by introducing "RANDOMNESS"
			 * Please note that you need to do this for each Layer individually
			 */
			for(int i=0; i<Rows; i++)
			{
				for(int j=0; j<Cols; j++)
				{
					for (int k=0; k<Layers; k++)
					{
						/**
						 * Precondition for estimate the dh, dp, dc and dt values
						 */
						if (tmp_mtrx[i+1][j+1][k+1]>40)
						{
                //Revisit
                dP = 0.0;
						}

						/**
						 * Precondition for estimate the dh, dp, dc and dt values
						 */
						if (tmp_mtrx[i+1][j+1][k+1]<=40)
						{
              //Revisit.
							dP = 0.0;
						}
						/**
						 * Recalibrate the pressure and omega based of values of dO and dP.
						 */
						pressure[i][j][k]+=dP;
						temp1=Pixels[i][j][k];
						/**
						 * ReCalibrate the maximum and minimum ranges of these parameter values based on the above calculations.
						 */
						if (pressure[i][j][k]>pressureMax)
            {
                pressure[i][j][k]=pressureMax;
            }
						/**
						 * FINALLY! After all the calculations have been done, Apply these settings to our Cellular Automata Model
						 * The Birth Rate, Death Rate, Transition Rate and the Growth Rate are used to propagate the corrosion effects through time
						 *
						 * Experiments are performed on this CA Model by varying the above Parameter and Rule Settings
						 */
						Pixels[i][j][k] = (float)applyCA(tmp_mtrx[i][j][k],tmp_mtrx[i][j+1][k],tmp_mtrx[i][j+2][k], tmp_mtrx[i+1][j][k],
						tmp_mtrx[i+1][j+1][k],tmp_mtrx[i+1][j+2][k],tmp_mtrx[i+2][j][k],tmp_mtrx[i+2][j+1][k],tmp_mtrx[i+2][j+2][k],
						tmp_mtrx[i][j][k+1],tmp_mtrx[i][j+1][k+1],tmp_mtrx[i][j+2][k+1], tmp_mtrx[i+1][j][k+1],tmp_mtrx[i+1][j+1][k+1],
						tmp_mtrx[i+1][j+2][k+1],tmp_mtrx[i+2][j][k+1],tmp_mtrx[i+2][j+1][k+1],tmp_mtrx[i+2][j+2][k+1],tmp_mtrx[i][j][k+2],
						tmp_mtrx[i][j+1][k+2],tmp_mtrx[i][j+2][k+2], tmp_mtrx[i+1][j][k+2],tmp_mtrx[i+1][j+1][k+2],tmp_mtrx[i+1][j+2][k+2],tmp_mtrx[i+2][j][k+2],
						tmp_mtrx[i+2][j+1][k+2],tmp_mtrx[i+2][j+2][k+2],
						pressure[i][j][k], normYoungsMod[k], normThickness[k],k);

						if (pressure[i][j][k]<pressureMin) pressure[i][j][k]=pressureMin;

            //checking for previous state temp1 holds the value before the applyCA of pixels
						if (temp1==0 && Pixels[i][j][k]>0)
						{
							ini++;
						}

						if (temp1>0 && Pixels[i][j][k]==0)
						{
							dea++;
						}

						if (temp1<=20 && Pixels[i][j][k]>20)
						{
							gro++;
							if (temp1<255 && Pixels[i][j][k]==255)
							{
								sat++;
							}
						}
					}
				}
			}
		}
		return Double.toString(((double)((int)(((ini))*1000)))/1000.0) + ",\t " + Double.toString((double)((int)(dea*1000)/1000.0)) + ",\t" + Double.toString((double)((int)(gro*1000)/1000.0))+ ",\t" + Double.toString((double)((int)(sat*1000)/1000.0))+ ", ";
	}
	/**
	 * Method call for Action Performed
	 */
	public void actionPerformed(ActionEvent e)
	{
		String actionCommand = e.getActionCommand();

		if("Start".equals(actionCommand))
		{
			timer.start();
		}
		if("Stop".equals(actionCommand))
		{
			timer.stop();
			number.setVisible(true);
		}
	}
}
