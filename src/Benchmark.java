import java.awt.Color;
import java.util.Random;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

import algo.*;

public class Benchmark extends ApplicationFrame {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
* Cr�ation d'un test
* @param title Le titre de la fen�tre
* @param display indique si on affiche ou non la fenetre
*/
public Benchmark(String title,boolean display) {
	super(title);
	// Cr�ation du jeu de donn�e
	XYDataset dataset = createDataset();
	JFreeChart chart = createChart(dataset);
	if (display) { // Affichage de la fen�tre
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);
	}
}

/**
 * Taille minimale des donn�es
 */
static int sizeMin1 = 1; // Courbes th�oriques
static int sizeMin = 50; // Tri de tableau
/**
 * Taille maximale des donn�es
 */
static int sizeMax1 = 1000;// Courbes th�oriques
static int sizeMax = 1000;
/**
 * Pas de variation sur la taille des donn�es
 */
static int sizePas1 = 1;// Courbes th�oriques
static int sizePas = 50;
/** 
 * Nombre de tests effectu�s pour chaque taille
 */
static int NbTests = 200;

/**
 * Ajout d'une s�rie n�
 * @return la s�rie de donn�es
 */
public static XYSeries n2() {
	XYSeries series = new XYSeries("n�");
	for(int n=sizeMin1;n<sizeMax1;n+=sizePas1) {
		series.add(n, n*n); // Graph n�
	}
	return series;		
}

/**
 * Ajout d'une s�rie n^3
 * @return la s�rie de donn�es
 */
public static XYSeries n3() {
	XYSeries series = new XYSeries("n^3");
	for(int n=sizeMin1;n<sizeMax1;n+=sizePas1) {
		series.add(n, n*n*n); // Graph n^3
	}
	return series;		
}

/**
 * Ajout d'une s�rie c1
 * @return la s�rie de donn�es
 */
public static XYSeries c1() {
	XYSeries series = new XYSeries("1");
	for(int n=sizeMin1;n<sizeMax1;n+=sizePas1) {
		series.add(n, 1); // Graph 1
	}
	return series;		
}

/**
 * Ajout d'une s�rie en nlogn
 * @return la s�rie cr��e
 */
public static XYSeries nlogn() {
	XYSeries series = new XYSeries("n.log(n)");
	for(int n=sizeMin1;n<sizeMax1;n+=sizePas1) {
		series.add(n, n*Math.log(n)); // Graph nlogn
	}
	return series;	
}

/**
 * Ajout d'une s�rie en loglogn
 * @return la s�rie cr��e
 */
public static XYSeries loglogn() {
	XYSeries series = new XYSeries("log(log(n))");
	for(int n=10;n<sizeMax1;n+=sizePas1) {
		series.add(n, Math.log(Math.log(n))); // Graph loglogn
	}
	return series;	
}

/**
 * Ajout d'une s�rie en expn
 * @return la s�rie cr��e
 */
public static XYSeries expn() {
	XYSeries series = new XYSeries("exp(n)");
	for(int n=sizeMin1;n<sizeMax1;n+=sizePas1) {
		series.add(n, Math.exp(n)); // Graph expn
	}
	return series;	
}

private static int fact(int n) { return  n==0 ? 1 : n*fact(n-1); }

/**
 * Ajout d'une s�rie en factn
 * @return la s�rie cr��e
 */
public static XYSeries factn() {
	XYSeries series = new XYSeries("fact(n)");
	for(int n=sizeMin1;n<sizeMax1;n+=sizePas1) {
		series.add(n, fact(n)); // Graph expn
	}
	return series;	
}


/**
 * Ajout d'une s�rie 2^n
 * @return la s�rie de donn�es
 */
public static XYSeries pow2n() {
	XYSeries series = new XYSeries("2^n");
	for(int n=sizeMin1;n<sizeMax1;n+=sizePas1) {
		series.add(n, Math.pow(2, n)); // Graph 2^n
	}
	return series;		
}

/**
 * Ajout d'une s�rie n^n
 * @return la s�rie de donn�es
 */
public static XYSeries pownn() {
	XYSeries series = new XYSeries("n^n");
	for(int n=sizeMin1;n<sizeMax1;n+=sizePas1) {
		series.add(n, Math.pow(n, n)); // Graph n^n
	}
	return series;		
}


public static void rechercherInTab(int[] tab,int element){
	for(int i = 0; i < tab.length && tab[i] != element; i++); 
}

/**
 * Ajout d'une s�rie de Temps moyen recherche en fonction de (n)
 * @return la s�rie de mesures effectu�es
 */
public static XYSeries rechercheEnFonctionDen(AlgoTri algo) {
	
	XYSeries series = new XYSeries("Temps moyen de recherche en fonction de (n)");
	double temps = 0;
	Random random = new Random();
	final int element = random.nextInt(1000);
	
	for(int n=sizeMin1;n<sizeMax1;n+=sizePas1) {
		int[] tab = algo.randomData(n);
		long debutTps = System.nanoTime()/1000;
		rechercherInTab(tab, element);
		long finTps = System.nanoTime()/1000;
		temps = finTps-debutTps;
		series.add(n, temps); // Graph TempsMoyenRechercheEnFonctionDen
	}
	return series;		
}

/**
 * Lancement des algorithmes de tri
 * @param algo l'objet � ex�cuter
 * @param ALGO l'algorithme � ex�cuter
 * @return la s�rie de mesures effectu�es
 */

public static XYSeries runAlgo(AlgoTri algo,int ALGO) {
	System.out.println("Run " + algo.Name + " " + AlgoTri.TRI_NAME[ALGO]);
	XYSeries series = new XYSeries(AlgoTri.TRI_NAME[ALGO]);
	double temps = 0; // Le temps �coul�
	int nbAppels = 0; // Le nombre d'instruction
	int n = 0; // la taille des donn�es
	
	/*
	 * A COMPLETER avec des appels � algo.run en faisant varier la taille du tableau et en ex�cutant plusieurs tests 
	 */
	for(n=50;n<1000;n+=50) {
		int[] tab = algo.randomData(n);
		
		for(nbAppels =0; nbAppels<199; nbAppels++){
			// Mesure du temps �coul� entre cet instant ....
			long debutTps = System.nanoTime()/1000; //currentTimeMillis();
			algo.run(tab,ALGO);
			// ... et cet instant ci
			long finTps = System.nanoTime()/1000; //currentTimeMillis();
			temps = finTps-debutTps;
		} 
		
		temps /= 200;
		//  Ajout � la s�rie d'un point d'abscisse n et d'ordonn�e temps  
		series.add(n, temps);
	}
	// ou Ajout � la s�rie d'un point d'abscisse n et d'ordonn�e nbAppels 
	// series.add(n, nbAppels);
	
	return series;
}

/**
 * Ajout d'une s�rie n�/2
 * @return la s�rie de donn�es
 */
public static XYSeries n2on2() {
	XYSeries series = new XYSeries("n�/2");
	for(int n=sizeMin1;n<sizeMax1;n+=sizePas1) {
		series.add(n, Math.pow(n, 2)/2); // Graphn�/2
	}
	return series;		
}

/**
 * Ajout d'une s�rie 100n-3
 * @return la s�rie de donn�es
 */
public static XYSeries Cent_n_Moins_3() {
	XYSeries series = new XYSeries("100n-3");
	for(int n=sizeMin1;n<sizeMax1;n+=sizePas1) {
		series.add(n, 100*n-3); // Graph 100n-3
	}
	return series;		
}

/**
 * Ajout d'une s�rie n^4/100
 * @return la s�rie de donn�es
 */
public static XYSeries nPow4on100() {
	XYSeries series = new XYSeries("n^4/100");
	for(int n=sizeMin1;n<sizeMax1;n+=sizePas1) {
		series.add(n, Math.pow(n, 4)/100); // Graph n^4/100
	}
	return series;		
}


/** 
 * Cr�ation des s�ries de donn�es
 * @param algoType
 * @return
 */
private static XYDataset createDataset() {

	XYSeriesCollection dataset = new XYSeriesCollection();
	
	dataset.addSeries(c1());
	dataset.addSeries(n2());
	dataset.addSeries(n3());
	dataset.addSeries(nlogn());
	dataset.addSeries(loglogn());
	dataset.addSeries(expn());
	dataset.addSeries(factn());
	dataset.addSeries(pownn());
	dataset.addSeries(pow2n());
	
	dataset.addSeries(n2on2());
	dataset.addSeries(Cent_n_Moins_3());
	dataset.addSeries(nPow4on100());
	
	/*
	 * A compl�ter avec des appels � runAlgo()
	 */
	
	AlgoTri exo2 = new AlgoTri("exo 2");
	dataset.addSeries(rechercheEnFonctionDen(exo2));
	
	AlgoTri exo3 = new AlgoTri("exo 3");
    /*
     *       Complexit� moyenne                        */
    dataset.addSeries(runAlgo(exo3,0)); //TRI_BULLE_1
    dataset.addSeries(runAlgo(exo3,5)); //TRI_BOUSTROPHEDON 
    dataset.addSeries(runAlgo(exo3,8)); //TRI_INSERTION_2
    dataset.addSeries(runAlgo(exo3,11)); //TRI_FUSION_3
    dataset.addSeries(runAlgo(exo3,4)); //TRI_RAPIDE
    dataset.addSeries(runAlgo(exo3,9)); //TRI_PAR_TAS*/
	
	/*
     *       Complexit� Th�orique                    */    
    dataset.addSeries(runAlgo(exo3,1)); //TRI_BULLE_2
    dataset.addSeries(runAlgo(exo3,5)); //TRI_BOUSTROPHEDON 
    dataset.addSeries(runAlgo(exo3,7)); //TRI_INSERTION_1
    dataset.addSeries(runAlgo(exo3,10)); //TRI_FUSION_2
    dataset.addSeries(runAlgo(exo3,4)); //TRI_RAPIDE
    dataset.addSeries(runAlgo(exo3,9)); //TRI_PAR_TAS*/
	
	return dataset;
}
/**
* Cr�ation d'un graphe
* @param dataset les s�ries � afficher
* @return a chart.
*/
@SuppressWarnings("deprecation")
private static JFreeChart createChart(XYDataset dataset) {
JFreeChart chart = ChartFactory.createXYLineChart(
"Comparaison des tris", // chart title
"n", // x axis label
"Temps", // y axis label
dataset, // data
PlotOrientation.VERTICAL,
true, // include legend
true, // tooltips
false // urls
);
// Couleur de fond
chart.setBackgroundPaint(Color.white);
// r�cup�ration d'une r�f�rence vers l'affichage
XYPlot plot = (XYPlot) chart.getPlot();
plot.setBackgroundPaint(Color.lightGray);
plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
plot.setDomainGridlinePaint(Color.white);
plot.setRangeGridlinePaint(Color.white);
XYLineAndShapeRenderer renderer
= (XYLineAndShapeRenderer) plot.getRenderer();
renderer.setShapesVisible(true);
renderer.setShapesFilled(true);

NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

return chart;
}

/**
* Cr�ation du panneau d'affichage 
*
* @return le panneau d'affichage.
*/
public static JPanel createDemoPanel() {
JFreeChart chart = createChart(createDataset());
return new ChartPanel(chart);
}

/**
* Point d'entr�e du programme
*
* @param args ignored.
*/
public static void main(String[] args) {
boolean display = true;
	Benchmark bench = new Benchmark("Complexit�",display);
if (display) {
	bench.pack();
	RefineryUtilities.centerFrameOnScreen(bench);
	bench.setVisible(true);
}
}
}