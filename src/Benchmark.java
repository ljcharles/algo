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
* Création d'un test
* @param title Le titre de la fenêtre
* @param display indique si on affiche ou non la fenetre
*/
public Benchmark(String title,boolean display) {
	super(title);
	// Création du jeu de donnée
	XYDataset dataset = createDataset();
	JFreeChart chart = createChart(dataset);
	if (display) { // Affichage de la fenêtre
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);
	}
}

/**
 * Taille minimale des données
 */
static int sizeMin1 = 1; // Courbes théoriques
static int sizeMin = 50; // Tri de tableau
/**
 * Taille maximale des données
 */
static int sizeMax1 = 1000;// Courbes théoriques
static int sizeMax = 1000;
/**
 * Pas de variation sur la taille des données
 */
static int sizePas1 = 1;// Courbes théoriques
static int sizePas = 50;
/** 
 * Nombre de tests effectués pour chaque taille
 */
static int NbTests = 200;

/**
 * Ajout d'une série n²
 * @return la série de données
 */
public static XYSeries n2() {
	XYSeries series = new XYSeries("n²");
	for(int n=sizeMin1;n<sizeMax1;n+=sizePas1) {
		series.add(n, n*n); // Graph n²
	}
	return series;		
}

/**
 * Ajout d'une série n^3
 * @return la série de données
 */
public static XYSeries n3() {
	XYSeries series = new XYSeries("n^3");
	for(int n=sizeMin1;n<sizeMax1;n+=sizePas1) {
		series.add(n, n*n*n); // Graph n^3
	}
	return series;		
}

/**
 * Ajout d'une série c1
 * @return la série de données
 */
public static XYSeries c1() {
	XYSeries series = new XYSeries("1");
	for(int n=sizeMin1;n<sizeMax1;n+=sizePas1) {
		series.add(n, 1); // Graph 1
	}
	return series;		
}

/**
 * Ajout d'une série en nlogn
 * @return la série créée
 */
public static XYSeries nlogn() {
	XYSeries series = new XYSeries("n.log(n)");
	for(int n=sizeMin1;n<sizeMax1;n+=sizePas1) {
		series.add(n, n*Math.log(n)); // Graph nlogn
	}
	return series;	
}

/**
 * Ajout d'une série en loglogn
 * @return la série créée
 */
public static XYSeries loglogn() {
	XYSeries series = new XYSeries("log(log(n))");
	for(int n=10;n<sizeMax1;n+=sizePas1) {
		series.add(n, Math.log(Math.log(n))); // Graph loglogn
	}
	return series;	
}

/**
 * Ajout d'une série en expn
 * @return la série créée
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
 * Ajout d'une série en factn
 * @return la série créée
 */
public static XYSeries factn() {
	XYSeries series = new XYSeries("fact(n)");
	for(int n=sizeMin1;n<sizeMax1;n+=sizePas1) {
		series.add(n, fact(n)); // Graph expn
	}
	return series;	
}


/**
 * Ajout d'une série 2^n
 * @return la série de données
 */
public static XYSeries pow2n() {
	XYSeries series = new XYSeries("2^n");
	for(int n=sizeMin1;n<sizeMax1;n+=sizePas1) {
		series.add(n, Math.pow(2, n)); // Graph 2^n
	}
	return series;		
}

/**
 * Ajout d'une série n^n
 * @return la série de données
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
 * Ajout d'une série de Temps moyen recherche en fonction de (n)
 * @return la série de mesures effectuées
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
 * @param algo l'objet à exécuter
 * @param ALGO l'algorithme à exécuter
 * @return la série de mesures effectuées
 */

public static XYSeries runAlgo(AlgoTri algo,int ALGO) {
	System.out.println("Run " + algo.Name + " " + AlgoTri.TRI_NAME[ALGO]);
	XYSeries series = new XYSeries(AlgoTri.TRI_NAME[ALGO]);
	double temps = 0; // Le temps écoulé
	int nbAppels = 0; // Le nombre d'instruction
	int n = 0; // la taille des données
	
	/*
	 * A COMPLETER avec des appels à algo.run en faisant varier la taille du tableau et en exécutant plusieurs tests 
	 */
	for(n=50;n<1000;n+=50) {
		int[] tab = algo.randomData(n);
		
		for(nbAppels =0; nbAppels<199; nbAppels++){
			// Mesure du temps écoulé entre cet instant ....
			long debutTps = System.nanoTime()/1000; //currentTimeMillis();
			algo.run(tab,ALGO);
			// ... et cet instant ci
			long finTps = System.nanoTime()/1000; //currentTimeMillis();
			temps = finTps-debutTps;
		} 
		
		temps /= 200;
		//  Ajout à la série d'un point d'abscisse n et d'ordonnée temps  
		series.add(n, temps);
	}
	// ou Ajout à la série d'un point d'abscisse n et d'ordonnée nbAppels 
	// series.add(n, nbAppels);
	
	return series;
}

/**
 * Ajout d'une série n²/2
 * @return la série de données
 */
public static XYSeries n2on2() {
	XYSeries series = new XYSeries("n²/2");
	for(int n=sizeMin1;n<sizeMax1;n+=sizePas1) {
		series.add(n, Math.pow(n, 2)/2); // Graphn²/2
	}
	return series;		
}

/**
 * Ajout d'une série 100n-3
 * @return la série de données
 */
public static XYSeries Cent_n_Moins_3() {
	XYSeries series = new XYSeries("100n-3");
	for(int n=sizeMin1;n<sizeMax1;n+=sizePas1) {
		series.add(n, 100*n-3); // Graph 100n-3
	}
	return series;		
}

/**
 * Ajout d'une série n^4/100
 * @return la série de données
 */
public static XYSeries nPow4on100() {
	XYSeries series = new XYSeries("n^4/100");
	for(int n=sizeMin1;n<sizeMax1;n+=sizePas1) {
		series.add(n, Math.pow(n, 4)/100); // Graph n^4/100
	}
	return series;		
}


/** 
 * Création des séries de données
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
	 * A compléter avec des appels à runAlgo()
	 */
	
	AlgoTri exo2 = new AlgoTri("exo 2");
	dataset.addSeries(rechercheEnFonctionDen(exo2));
	
	AlgoTri exo3 = new AlgoTri("exo 3");
    /*
     *       Complexité moyenne                        */
    dataset.addSeries(runAlgo(exo3,0)); //TRI_BULLE_1
    dataset.addSeries(runAlgo(exo3,5)); //TRI_BOUSTROPHEDON 
    dataset.addSeries(runAlgo(exo3,8)); //TRI_INSERTION_2
    dataset.addSeries(runAlgo(exo3,11)); //TRI_FUSION_3
    dataset.addSeries(runAlgo(exo3,4)); //TRI_RAPIDE
    dataset.addSeries(runAlgo(exo3,9)); //TRI_PAR_TAS*/
	
	/*
     *       Complexité Théorique                    */    
    dataset.addSeries(runAlgo(exo3,1)); //TRI_BULLE_2
    dataset.addSeries(runAlgo(exo3,5)); //TRI_BOUSTROPHEDON 
    dataset.addSeries(runAlgo(exo3,7)); //TRI_INSERTION_1
    dataset.addSeries(runAlgo(exo3,10)); //TRI_FUSION_2
    dataset.addSeries(runAlgo(exo3,4)); //TRI_RAPIDE
    dataset.addSeries(runAlgo(exo3,9)); //TRI_PAR_TAS*/
	
	return dataset;
}
/**
* Création d'un graphe
* @param dataset les séries à afficher
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
// récupération d'une référence vers l'affichage
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
* Création du panneau d'affichage 
*
* @return le panneau d'affichage.
*/
public static JPanel createDemoPanel() {
JFreeChart chart = createChart(createDataset());
return new ChartPanel(chart);
}

/**
* Point d'entrée du programme
*
* @param args ignored.
*/
public static void main(String[] args) {
boolean display = true;
	Benchmark bench = new Benchmark("Complexité",display);
if (display) {
	bench.pack();
	RefineryUtilities.centerFrameOnScreen(bench);
	bench.setVisible(true);
}
}
}