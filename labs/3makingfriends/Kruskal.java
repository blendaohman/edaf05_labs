import java.util.*;

public class Kruskal {

    //	Disjoint Sets Data Structure
    private int[] friends; //

    public Kruskal(int n){
        this.friends = new int[n]; //Testade n men då funkade det ej. n+1 funkar:). Fattar inte rikgit vad denna gör.
    }


    /* Comment VAD GÖR DEN ENS  */
    private int find(int x){
        if(friends[x] == x){
            return x;
        }
        return find(friends[x]);
    }

    /* Comment vad gör den?? */
    private void unite(int x, int y){
        int fx = find(x);
        int fy = find(y);
        friends[fx] = fy;
    }

    /* Main method reads input, and finds weight of MST */
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);

        //	declaring the variables to load input
        int n,m;
        int a,b,w;

        //List of all our nodes in our tree, consisting of Pairs (name a, name b, and a weight w)
        ArrayList<Pair> nodes = new ArrayList<>();
        //	First row of input is some metadata
        n = scan.nextInt();
        m = scan.nextInt();

        // An instance of Kruskal class containing an arraylist of friends
        Kruskal kru = new Kruskal(n+1); //detta kan ju man ändra

        //Vad gör detta idk
        //	initialize friends for the disjoint sets
        for(int i=0;i<n;i++){
            kru.friends[i]=i;
        }

        // Continue reading rest of input
        for(int i=0;i<m;i++){
            a = scan.nextInt();
            b = scan.nextInt();
            w = scan.nextInt();
            nodes.add(new Pair(w,a,b)); // Adding nodes and their connection to noode list
        }

        //	Kruskals algorithm
        //	Variables for the Minimum Spanning Tree (MST)
        int treeWeight = 0;
        int treeEdges = 0;
        int	currentNode = 0;

        //	Sorting the nodes by weight using Comparator
        Collections.sort(nodes, new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                return p1.w - p2.w; //Giving us the smallest weighted pair
            }
        });

        //	Now we start comparing nodes to find the mimimum spanning tree
        while( ( treeEdges < n-1) || (currentNode < m) ){

            //	Getting the three propeties of this pair/connection of the current node
            a = nodes.get(currentNode).a;
            b = nodes.get(currentNode).b;
            w = nodes.get(currentNode).w;

            //	We check if nodes are ok to include and if they are in the same MST or not,
            // We do NOT want to create a cycle
            if( kru.find(a) != kru.find(b) ) {

                //	Uniting the trees when we know there is no cycle
                kru.unite(a,b);
                //	Adding total weight
                treeWeight += w;

                // Incrementing nbr of nodes in tree
                treeEdges++;
            }
            //	Going to the next node
            currentNode++;
        }
        //	Printing result
        System.out.println(treeWeight);
    }



}