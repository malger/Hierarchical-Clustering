package HierachicalClustering;

import java.util.*;
import java.util.Map.Entry;

/**
 * Hierarchical clustering algorithm
 * @param <T> Any type of object<br> provide the distance function in the linkage object
 */
public class ClusterAlgorithm<T> {

    List<Cluster<T>> clusters ;
    Linkage la;
    HashMap<Pair<Cluster<T>,Cluster<T>>,Double> linkage_values  = new HashMap<>();
    int initialsize;
    int max_linkage =-1;
    /**
     * Constructor with max_linkage constrain
     * @param objects collection of generic objects
     * @param la Linkage Algorithm
     * @param max_linkage a linkage threshold afterwards the cluster algorithm stops
     */
    public ClusterAlgorithm(Collection<T> objects,Linkage la, int max_linkage){
        this(objects,la);
        this.max_linkage=max_linkage;
    }

    /**
     * Constructor
     * @param objects collection of generic objects
     * @param la Linkage Algorithm
     */
    public ClusterAlgorithm(Collection<T> objects,Linkage la){
        //init clusters
        this.la=la;
        this.initialsize = objects.size();
        clusters = new ArrayList<>(objects.size());
        Iterator<T> it = objects.iterator();
        for (int i = 0; i < objects.size(); i++){
            clusters.add(i,new Cluster<T>(it.next()));
        }
        for(Cluster c_1 : clusters){
            for(Cluster c_2: clusters){
                if (c_1==c_2)
                    continue;
                Pair<Cluster<T>,Cluster<T>> linkagepair = new Pair<>(c_1,c_2);
                if(!linkage_values.containsKey(linkagepair))
                    linkage_values.put(linkagepair,la.calc(c_1,c_2));
            }
        }
    }

    /**
     * @return the remaining clusters as unmodifiable List
     */
    public Collection<Cluster<T>> getClusters() {
        return Collections.unmodifiableList(clusters);
    }

    /**
     * @return the first cluster of the remaining ones.
     */
    public Cluster<T> getFirstCluster(){
        return getClusters().iterator().next();
    }


    /**
     * clusters the data based on linkage function
     * @return if max_linkage is reached or only one cluster left
     */
    public void cluster(){
        if(clusters.size()<=1)
            return;

        //get pair of minimum linkage
        Entry<Pair<Cluster<T>,Cluster<T>>, Double> min = Collections.min(linkage_values.entrySet(),
                Comparator.comparingDouble(Entry::getValue));

        //max_linkage reached
        if(max_linkage >=0 && min.getValue()>max_linkage)
            return;
        Pair<Cluster<T>,Cluster<T>> p = min.getKey();
        //move clusters to merged one, remove references in list
        Cluster merged = new Cluster<T>(p.first,p.second,min.getValue());
        boolean t = clusters.remove(p.first);
        boolean tt = clusters.remove(p.second);
        //remove all references of the unmerged cluster from linkage hashmap
        List<Pair<Cluster<T>,Cluster<T>>> remove_pairs = new LinkedList<>();
        for( Pair<Cluster<T>,Cluster<T>> c : linkage_values.keySet()){
            if(p.first==c.first || p.first == c.second|| p.second==c.second || p.second == c.first)
                remove_pairs.add(c);
        }
        for ( Pair<Cluster<T>,Cluster<T>> c : remove_pairs)
            linkage_values.remove(c);
        //create all pairwise linkages
        for(Cluster c_1 : clusters){
            Pair<Cluster<T>,Cluster<T>> p_merged = new Pair<>(c_1,merged);
            linkage_values.put(p_merged,la.calc(p_merged.first,p_merged.second));
        }
        clusters.add(merged);
        cluster(); //recursion
        }



}



