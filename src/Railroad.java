/* Johnny Knights
 * Dr. Steinberg
 * COP3503 Spring 2024
 * Programming Assignment 5
 */
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;
import java.io.BufferedReader;


class Edge
{
    String src;
    String dest;
    int weight;
    public Edge(String src, String dest, int weight)
    {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
    //overriding toString method so I can see what is inside my arraylist
    public String toString() {
        return src + "---" + dest + "\t$" + weight;
     }

}
class DisjointSetImproved
{
	int [] rank;
	int [] parent;
    int n;
	//this is the subset method
    public DisjointSetImproved(int n)
    {
        rank = new int[n];
        parent = new int[n];
        this.n = n;
        makeSet();
    }
	
	// Creates n sets with single item in each
    public void makeSet()
    {
        for (int i = 0; i < n; i++) 
		{
            parent[i] = i;
        }
    }
	
	//path compression/ find our parent for set
	public int find(int x)
    {
        if (parent[x] != x) 
		{
            parent[x] = find(parent[x]);
        }
 
        return parent[x];
    }
	
	//union by rank
	public void union(int x, int y)
    {
        int xRoot = find(x), yRoot = find(y);
 
        if (xRoot == yRoot)
            return;
		
        if (rank[xRoot] < rank[yRoot])
            parent[xRoot] = yRoot;
		
        else if (rank[yRoot] < rank[xRoot])
            parent[yRoot] = xRoot;
        else 
        {
            parent[yRoot] = xRoot;
            rank[xRoot] = rank[xRoot] + 1;
        }
    }
    public static void printSets(int[] universe, DisjointSetImproved ds)
    {
        for (int i: universe) 
		{
            System.out.print(ds.find(i) + " ");
        }
 
        System.out.println();
    }
}

public class Railroad {
    //To store number of tracks so we can use it to see if we are in a cycle
    private int numOfTracks;
    //To store file name 
    private String fileOfAllPossibleTracks;
    //To store my sets when I make them
    private ArrayList<Edge> sets;

    public void readFile()
    {
        //simple file i/o stuff
        try
        {
            File file = new File(fileOfAllPossibleTracks);
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine())
            {
                //reads entire line first
                String line = reader.nextLine();
                //sees if this is a blank line or not
                if (!line.trim().isEmpty()) {
                    //seperate scanner for single lines
                    Scanner lineScanner = new Scanner(line);
                    if (lineScanner.hasNext()) {
                        //get our vertexes and costs
                        String src = lineScanner.next();
                        String dest = lineScanner.next();
                        String tempWeight = lineScanner.next();

                        //changing our cost from string to int
                        int weight = Integer.parseInt(tempWeight);
                        //adds the edge to our set
                        sets.add(new Edge(src, dest, weight));
                        System.out.println("This is var:" + src + " " + dest + " " + weight);
                        System.out.println(sets.size() + " " + sets.toString());
                        
                    }
                    lineScanner.close();
                }
            }
            reader.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    //numOfTracks is the number of lines in each text file
    //allPossible tracks take in the txt file for the tracks
    //Do file i/o for txt files and test if file i/o works and store in string array
    public Railroad(int numOfTracks, String fileOfAllPossibleTracks)
    {
        this.numOfTracks = numOfTracks;
        this.fileOfAllPossibleTracks = fileOfAllPossibleTracks;
        sets = new ArrayList<Edge>();
    }
    public String buildRailroad()
    {
        //System.out.println(src + "---" + dest + "\t$" + resultingtracks[i].weight + "\n");
        readFile();
        return ".";
    }
}

