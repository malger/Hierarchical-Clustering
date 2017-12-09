package HierachicalClustering;

import java.util.HashMap;
import java.util.function.BiFunction;

/**
 * Interface for Linkage Functions
 * @param <T> generic type for the distance function
 */
public abstract class Linkage<T> {
    final BiFunction<T,T,Double> distancefunction;
    final HashMap<Pair<T,T>,Double> pairwise_distances= new HashMap();
    /**
     * Constructor
     * @param distancefunction calc distance between to generic objects
     */
    public Linkage(BiFunction<T,T,Double> distancefunction){
        this.distancefunction = distancefunction;
    }
    public abstract double calc(Cluster<T> a,Cluster<T> b);
}
