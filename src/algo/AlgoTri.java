package algo;

import java.util.Random;

/**
 * Impl�mentation de plusieurs algorithmes de tri de tableau
 * @author Enguerran Grandchamp
 *
 */
public class AlgoTri extends Algorithme {
	/**
	 * Les donn�es � trier
	 */
	int[] data;
	
	/**
	 * Le nombre d'appels aux fonctions
	 */
	public static int nbAppels;
	
	/**
	 * Constantes permettant la s�lection des algorithmes
	 */
	public static final int TRI_BULLE_1 = 0;
	public static final int TRI_BULLE_2 = 1;
	public static final int TRI_BULLE_3 = 2;
	public static final int TRI_FUSION = 3;
	public static final int TRI_RAPIDE = 4;
	public static final int TRI_BOUSTROPHEDON = 5;
	public static final int TRI_SELECTION = 6;
	public static final int TRI_INSERTION_1 = 7;
	public static final int TRI_INSERTION_2 = 8;
	public static final int TRI_PAR_TAS = 9;
	public static final int TRI_FUSION_2 = 10;
	public static final int TRI_FUSION_3 = 11;
		
	/**
	 * Nom des algorithmes de tri pour l'affichage
	 */
	public static final String[] TRI_NAME = {"Tri � bulle 1", "Tri � bulle 2", "Tri � bulle 3", "Tri Fusion 1", "Tri rapide","Tri Boustrophedon","Tri Selection","Tri Insertion 1","Tri Insertion 2","Tri Par Tas","Tri Fusion 2","Tri Fusion 3"};
	
	/**
	 * Constucteur
	 * @param Name nom sp�cifi� par l'utilisateur
	 */
	public AlgoTri(String Name) {
		super(Name);
	}
	
	/**
	 * G�n�ration al�atoire d'un tableau d'entier
	 * @param size taille du tableau
	 * @return le tableau g�n�r�
	 */
	public int[] randomData(int size) {
		data = new int[size];
		Random random = new Random();
		
		for(int j=0;j<data.length;j++)
			data[j] = random.nextInt(1000);
		
		return data;
	}
	
	/**
	 * M�thode d'ex�cution des algos
	 * utilise le tableau interne data
	 * @param ALGO l'algo � ex�cuter
	 */
	public void run(int ALGO) {
		run(data,ALGO);
	}
	
	/**
	 * M�thode d'ex�cution des algos
	 * @param tab le tableau � trier
	 * @param ALGO l'algo � ex�cuter
	 */
	public void run(int[] tab,int ALGO) {
			nbAppels = 0;
		
			switch(ALGO) {
			case TRI_BULLE_1 : runtriBulle1(tab); break;
			case TRI_BULLE_2 : runtriBulle2(tab); break;
			case TRI_BULLE_3 : runtriBulle3(tab); break;
			case TRI_FUSION : runtriFusion(tab,0,tab.length-1,1); break;
			case TRI_FUSION_2 : runtriFusion(tab,0,tab.length-1,2); break;
			case TRI_FUSION_3 : runtriFusion(tab,0,tab.length-1,3); break;
			case TRI_BOUSTROPHEDON : runtriBoustrophedon(tab); break;
			case TRI_SELECTION : runtriSelection(tab); break;
			case TRI_RAPIDE : runtriRapide(tab,0,tab.length-1); break;
			case TRI_INSERTION_1 : runtriInsertion1(tab); break;
			case TRI_INSERTION_2 : runtriInsertion2(tab); break;
			case TRI_PAR_TAS : runtriParTas(tab); break;
			default: break;
			}
	}
	
	/**
	 * Permutation de deux valeurs d'un tableau (t[i] et t[j])
	 * 
	 * @param t le tableau
	 * @param i premier indice
	 * @param j deuxi�me indice
	 */
	public static void permuter(int[] t, int i, int j)
	{
	int a=t[i];
	t[i]=t[j];
	t[j]=a;
	}

	
	/**
	 * Algorithme du tri � bulle
	 * Version non rafin�e (on reparcours tous le tableau � chaque fois) 
	 * @param t le tableau � trier
	 */
	public static void runtriBulle1(int[] t)
	{
		boolean fin=false;
		nbAppels = 0;
		
		while(!fin)
		{
			fin=true;
			for (int i=0; i<t.length-1; i++)
			{
				if (t[i]>t[i+1])
				{
					nbAppels++;
					permuter (t, i, i+1);
					fin=false;
				}
			}
		}
	}

	/**
	 * Algorithme du tri � bulle
	 * Pas de sortie avant la fin de l'algo m�me si le tableau est tri�
	 * On ne reparcours pas tout le tableau � chaque fois
	 * @param t le tableau � trier
	 */
	public static void runtriBulle2(int[] t)
	{
		nbAppels = 0;
		for (int i=t.length-1; i>=0; i--)
			for (int j=0; j<i; j++)
			{
				if (t[j]>t[j+1])
				{
					nbAppels++;
					permuter (t, j, j+1);
				}
			}
	}

	/**
	 * Algorithme du tri � bulle
	 * Sortie avant la fin de l'algo si le tableau est tri�
	 * @param t le tableau � trier
	 */
	public static void runtriBulle3(int[] t)
	{
		nbAppels = 0;
		for (int i=t.length-1; i>=0; i--)
		{
			boolean fin=true;
			for (int j=0; j<i; j++)	
			{
				if (t[j]>t[j+1])
				{
					nbAppels++;
					permuter (t, j, j+1);
					fin=false;
				}
			}
			if (fin) break; //sort de la boucle
		}
	}


/**
 * 
 * @param tab la tableau � trier
 * @return le tableau tri�
 */
	public static int[] triBulle4(int[] tab) {
		for(int i=0;i<tab.length-1;i++) {
			for(int j=i;j<tab.length-1;j++)
				if (tab[j]>tab[j+1]){
					int temp=tab[j];
					tab[j] = tab[j+1];
					tab[j+1] = temp;
				}
		}
		return tab;
	}

	
	/**
	 * Tri boustrophedon
	 * @param t le tableau � trier
	 */
	static void runtriBoustrophedon(int[] t)
	{
		nbAppels = 0;
		boolean b=false;
		while (!b)	{
			b=true;
			for (int i=0; i<t.length-1; i++)	{
				if (t[i]>t[i+1]){
					nbAppels++;
					permuter(t, i, i+1);
					b=false;
				}
			}
			for (int i=t.length-1; i>0; i--)	{
				if (t[i-1]>t[i])		{
					permuter(t, i-1, i);
					b=false;
				}
			}
		}
	}
	
	/**
	 * Algorithme du tri s�lection
	 * @param t le tableau � trier
	 */
	static public void runtriSelection(int[] t)
	{
		nbAppels = 0;
	for (int i=0; i<t.length-1; i++)	{
		int min=t[i];
		int indicemin=i;
		for (int j=i+1; j<t.length; j++)		{
			if (t[j]<min)			{
				min=t[j];
				indicemin=j;
			}
		}
		nbAppels++;
		permuter(t, i, indicemin);
	}
	}
	
	/**
	 * Algorithme du tri pas insertion prmei�re version
	 * @param t le tableau � trier
	 */
	static public void runtriInsertion1(int[] t)	{
		nbAppels = 0;
		for (int i=1; i<t.length; i++)	{
			for(int j=i; j>0; j--)	{
				if (t[j-1]>t[j]) {
					nbAppels++;
					permuter(t, j-1, j);
				}
				else break; //quitte la boucle
				// facultatif, mais �elimine des op�erations
				// inutiles
			}
		}
	}
	
	/**
	 * Algorithme de tri par insertion deuxi�me version
	 * @param t le tableau � trier
	 */
	static public void runtriInsertion2(int[] t)
	{
		nbAppels = 0;
		for (int i=1; i<t.length; i++) {
			int j=i;
			while(j>0 && t[j-1]>t[j]) {
				nbAppels++;
				permuter(t, j-1, j);
				j--;
			}
		}
	}


	
	/**
	 * Algorithme de tri fusion premi�re version
	 * @param t le tableau � trier
	 * @param d�but indice de d�but de la partie � trier
	 * @param fin indice de la fin de la partie � trier
	 * @param version version de la fusion � ex�cuter (1, 2, 3)
	 */
	public void runtriFusion (int [] tab, int d�but, int fin,int version)
	{
		
	        int milieu;
	        if(d�but<fin)   {
	                milieu = (d�but+fin)/2;
	                nbAppels++;
	                runtriFusion(tab, d�but, milieu,version);
	                nbAppels++;
	                runtriFusion(tab, milieu+1, fin,version);
	                nbAppels++;
	                switch(version) {
	                	case 1 :fusionner1 (tab, d�but, milieu, fin);break;
	                	case 2 :fusionner2 (tab, d�but, milieu, fin);break;
	                	case 3 :fusionner3 (tab, d�but, milieu, fin);break;
	                }
	        }
	}
	
	/**
	 * Tableau temporaire
	 */
	public int [] old_tab = new int[10000];
	 
	/**
	 * Fusion de deux tableaux avec recopie uniquement des �l�ments n�cessaires
	 * @param tab le tableau � fusionner
	 * @param d�but d�but du tableau
	 * @param milieu milieu du tabelau
	 * @param fin fin du tableau
	 */
	public void fusionner1 (int tab[], int d�but, int milieu, int fin) {
	        //int [] old_tab = (int[]) tab.clone();
			/*for(int i=0;i<old_tab.length;i++)
				old_tab[i] = 0;*/
			for(int i=0;i<=milieu;i++) //for(int i=0;i<tab.length;i++)
				old_tab[i] = tab[i];
			
			
	        int i1 = d�but; //indice dans la premi�re moiti� de old_tab
	        int i2 = milieu+1; // indice dans la deuxi�me moiti� de old_tab
	        int i = d�but; //indice dans le tableau tab
	 
	        while (i1<= milieu && i2 <= fin)   {
	                //quelle est la plus petite t�te de liste?
	                if(old_tab[i1] <= tab[i2])  {
	                        tab[i] = old_tab[i1];
	                        i1++;
	                }
	                else  {
	                        tab[i] = tab[i2]; // old_tab[i2]
	                        i2++;
	                }
	                i++;
	        }
	        if (i<=fin)   {
	                while(i1<=milieu) { // le reste de la premi�re moiti�
	                        tab[i]=old_tab[i1];
	                        i1++;
	                        i++;
	                }
	                while(i2<=fin) { // le reste de la deuxi�me moiti�
	                        tab[i]=tab[i2];
	                        i2++;
	                        i++;
	                }
	        }
	}
	
	/**
	 * Fusion de deux tableaux avec recopie manuelle de tous les �l�ments
	 * @param tab le tableau � fusionner
	 * @param d�but d�but du tableau
	 * @param milieu milieu du tabelau
	 * @param fin fin du tableau
	 */
	public void fusionner2 (int tab[], int d�but, int milieu, int fin)
	{
			for(int i=0;i<old_tab.length;i++)
				old_tab[i] = 0;
			for(int i=0;i<tab.length;i++)
				old_tab[i] = tab[i];
			
			
	        int i1 = d�but; //indice dans la premi�re moiti� de old_tab
	        int i2 = milieu+1; // indice dans la deuxi�me moiti� de old_tab
	        int i = d�but; //indice dans le tableau tab
	 
	        while (i1<= milieu && i2 <= fin)  {
	                //quelle est la plus petite t�te de liste?
	                if(old_tab[i1] <= old_tab[i2]) {
	                        tab[i] = old_tab[i1];
	                        i1++;
	                }
	                else {
	                        tab[i] = old_tab[i2]; 
	                        i2++;
	                }
	                i++;
	        }
	        if (i<=fin)  {
	                while(i1<=milieu) { // le reste de la premi�re moiti�
	                        tab[i]=old_tab[i1];
	                        i1++;
	                        i++;
	                }
	                while(i2<=fin){ // le reste de la deuxi�me moiti�
	                        tab[i]=old_tab[i2];
	                        i2++;
	                        i++;
	                }
	        }
	}
	
	
	/**
	 * Fusion de deux tableaux avec clonage java
	 * @param tab le tableau � fusionner
	 * @param d�but d�but du tableau
	 * @param milieu milieu du tabelau
	 * @param fin fin du tableau
	 */
	public void fusionner3 (int tab[], int d�but, int milieu, int fin)
	{
	        int [] old_tab = (int[]) tab.clone();
			for(int i=0;i<tab.length;i++)
				old_tab[i] = tab[i];
			
			
	        int i1 = d�but; //indice dans la premi�re moiti� de old_tab
	        int i2 = milieu+1; // indice dans la deuxi�me moiti� de old_tab
	        int i = d�but; //indice dans le tableau tab
	 
	        while (i1<= milieu && i2 <= fin) {
	                //quelle est la plus petite t�te de liste?
	                if(old_tab[i1] <= old_tab[i2]) {
	                        tab[i] = old_tab[i1];
	                        i1++;
	                }
	                else {
	                        tab[i] = old_tab[i2]; 
	                        i2++;
	                }
	                i++;
	        }
	        if (i<=fin)  {
	                while(i1<=milieu) {  // le reste de la premi�re moiti�
	                        tab[i]=old_tab[i1];
	                        i1++;
	                        i++;
	                }
	                while(i2<=fin) {// le reste de la deuxi�me moiti�
	                        tab[i]=old_tab[i2];
	                        i2++;
	                        i++;
	                }
	        }
	}
	
	
	/**
	 * Algorithme du tri rapide (quicksort)
	 * @param tableau le tableau � trier
	 * @param d�but l'indice de d�but du tableau
	 * @param fin l'indice de fin du tableau
	 */
	public static void runtriRapide(int [] tableau, int d�but, int fin) {
	    if (d�but < fin) {
	        int indicePivot = partition(tableau, d�but, fin);
	        nbAppels++;
	        runtriRapide(tableau, d�but, indicePivot-1);
	        nbAppels++;
	        runtriRapide(tableau, indicePivot+1, fin);
	    }
	}
	 
	/**
	 * Partition du tableau
	 * @param t le tableau � partitionner
	 * @param d�but l'indice de d�but du tableau
	 * @param fin l'indice de fin du tableau
	 * @return le pivot
	 */
	public static int partition (int [] t, int d�but, int fin) {
	    int valeurPivot = t[d�but];
	    int d = d�but+1;
	    int f = fin;
	    while (d < f) {
	        while(d < f && t[f] >= valeurPivot) f--;
	        while(d < f && t[d] <= valeurPivot) d++;
	        int temp = t[d];
	        t[d]= t[f];
	        t[f] = temp;
	    }
	    if (t[d] > valeurPivot) d--;
	    t[d�but] = t[d];
	    t[d] = valeurPivot;
	    return d;
	}
	
	 /**
	  * 
	  * @param arbre
	  * @param noeud
	  * @param n
	  */
     private static void Tamiser(int[] arbre, int noeud, int n)
     {
    	
         int k = noeud;
         int j = 2 * k;

         while (j <= n)  {
             if ((j < n) && (arbre[j] < arbre[j + 1]))
                 j++;

             if (arbre[k] < arbre[j])  {
            	 nbAppels++;
                 permuter(arbre,k,j);
                 k = j;
                 j = 2 * k;
             }
             else
                 break;
         }
     }

     /**
      * Algorithme de tri par tas
      * @param arbre le tableau � trier
      */
     public static void runtriParTas(int[] arbre)
     {
    	 nbAppels = 0;
 		
         for (int i = arbre.length - 1; i >= 0; i--) {
        	 nbAppels++;
             Tamiser(arbre, i, arbre.length - 1);
         }

         for (int i = arbre.length - 1; i >= 1; i--)  {
        	 nbAppels++;
             permuter(arbre,i,0);
             Tamiser(arbre, 0, i - 1);
         }
     }
}
