package server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import client.controllers.MainAppController;
import client.controllers.ScatterChartPresenter;
import client.controllers.TableViewController;
import javafx.stage.Stage;
import utils.SimpleCSVReader;
public class DatasetManager  implements IDatasetManager {
	//na ftiakso listes me datasets names na krataei polla datasets kai na ta fortonei me ta canonical paths
	private  String datasetName="";
	private String canonicalPath="";
	private TreeMap<String,ArrayList<String[]>> tree = new TreeMap<String,ArrayList<String[]>>();//TreeMap gia na kratisoume datasets me vasi ta datasets names.
	private ArrayList<String[]> data = new ArrayList<String[]>();
	@Override
	public int registerDataset(String datasetName, String canonicalPath) {
		
		SimpleCSVReader cvsReader = new SimpleCSVReader(); //kanoume load dataset ayti ti fora oxi gia epidiksei alla gia na to kratisoume se ena treemap.
		ArrayList<String []> data1 = new ArrayList<String []>(); 
		data1 = cvsReader.load(canonicalPath);
		this.datasetName = datasetName;
		this.canonicalPath = canonicalPath;
		this.data.addAll(data1);
		if(tree.containsKey(datasetName))//tsekaroume an xrisimopoieite to kleidi me datasetname(koino onoma) kai epistrefoume 1 stin analogi periptosi
		{
			System.out.println("Theres already a Dataset with that name,Try again.");
			return 1;
		}
		tree.put(datasetName, data1); //pername to data sto treemap me kleidi to onoma tou.
		
		
		
		
		return 0;
	}

	
	@Override
	
	public String[] retrieveDataset(String datasetName, ArrayList<String[]> data) {
			int i;
			if(this.tree.containsKey(datasetName))
			{
				ArrayList<String []> tempData = new ArrayList<String []>(); //ftiahnoume mia prosorini tempData oste na kratisoume kai na fortosoume to apothikeymeno dataset.
				
				tempData.addAll(tree.get(datasetName)); //epistrefei me vasi to kleidi to dataset.
				String[] k = tempData.get(0); //pernoume to header kai to apothikevoume sto k.
				tempData.remove(0); //afairoume tin proti grammi (header) oste na perasoume mono ta stoiheia.
				for(i=0; i<tempData.size(); i ++)
				{
					String[] n = tempData.get(i);
					data.add(n);
				}
				
				
				
				
				
				return k;}//epistrefoume to header kai to data afou allaksame tin metavliti.
		else
			return null;
		
		
	}

	@Override
	public int filterDataset(String originalDatasetName, String newDatasetName, String filterColumnName,
			String filterValue) {
			 ArrayList<String[]> filtered = new ArrayList<String[]>(); //to dataset pou kratame to filtrarismeno mas dataset me neo onoma.
			 
			if (this.tree.containsKey(originalDatasetName))
			{
				this.data.addAll(tree.get(originalDatasetName)); //fortonoume sto data to palio dataset me to originaldatasetname.
				
				
				filtered.add(this.data.get(0));
				
			        
			        for (String[] col: this.data) { //psahnoume ana grammi na vroume to katalilo collum me kleidi to filtervalue.
			        	
			        	for(String c: col)
			        	{
			        		 if (c.equals(filterValue)) {
					                filtered.add(col);// an to vroume pername tin grammi pou psaksame.
					            }
			        	}
			           
			        }
			     tree.put(newDatasetName, filtered); //vazoume sto treemap mas to neo dataset me to neo tou onoma.epistrefoume 1 an ola pane kala.
			    
				return 1;
				
			}
		
		
		
		return 0;
	}

	@Override
	public ArrayList<String[]> getDatasetProjection(String datasetName, ArrayList<String> attributeNames) {
		// prepei na vro tropo na spaso ta collumns eite me to csvReader na ton allakso eite na ta kano objects kapos eite na peirakso tin lista na tin spaso se ipolistes
		ArrayList<String[]>  tempD = new ArrayList<String[]>();
		ArrayList<String[]>  scat = new ArrayList<String[]>();	
		try{
		
		String[] hed = this.data.get(0); //kratame to header tou data mas.
		
		int xAxis=0,yAxis=0; // oi times gia tous aksones mas.
		boolean flagX=false,flagY = false;  //flags gia na doume an iparhoun ta attributeNames sto header mas oste na ta kanoume project.
		for(int i =0; i<hed.length;i++)
		{
			if (hed[i].equals(attributeNames.get(0))){//an kapio apo to header tairiazei to xAxis pernei timi tou i kai i boolean ginete alithis.
				xAxis =i;
				flagX = true;
			}
			if (hed[i].equals(attributeNames.get(1))){ //paromoia me parapano.
				yAxis =i;
				flagY = true;
			}
			
		}
		if (flagX== false || flagY == false) //an mia apo tis dio einai false tote epistrefoume null dioti den mporei na ginei projection me mia h kamia x,y.
		{
			return null;
		}
		
		
		if (this.tree.containsKey(datasetName)) //edo tora ehoume tous celtics p.x theloume na kratisoume ta dio pedia PTS kai W.
		{
			tempD.addAll(tree.get(datasetName)); //pername sto prosorino dataset tempD to dataset me to datasetName pou dosame.
		
		}
		
		int i = 0;
		String[] tokens;
		String[] fal = new String[2]; // o prosorinos pinakas apo Strings fal krataei 2 times ton X kai Y p.x X= POINTS Y= WINS
		while(i<tempD.size())
		{
			tokens = tempD.get(i);
			fal[0] = tokens[xAxis];// me vasi ta X kai Y pername antistiha ston fal ta nea header .
			fal[1] = tokens[yAxis];
			//System.arraycopy(tokens, 3, fal, 0, 2);
			scat.add(fal.clone()); //sto teliko dataset pou tha epistrafei gia na ginei projection pername ta zitoumena X,Y kai gemizoume ton pinaka me 2 kolones kai oses grammes xriazete.
			i++;
		}
		scat.remove(0); //aferoume to header giati den to hriazomaste
		
		
		}catch (Exception e) {
	        e.printStackTrace();
	    }
		return scat; //epistrefoume to neo dataset gia na ginei projection
	}


	public static Object getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	

}
