package HierachicalClustering;

import java.util.function.BiFunction;

/**
 * Implements single Linkage
 * @param <T> generic type
 */
public class SingleLinkage<T> extends Linkage<T>{
    /**
     * Constructor
     * @param distancefunction calc distance between to generic objects
     */
    public SingleLinkage(BiFunction<T, T, Double> distancefunction) {
        super(distancefunction);
    }

    public double calc(Cluster<T> a, Cluster<T> b) {
        double min = Double.MAX_VALUE;
        for (T p1 : a.getClusterElements())
            for(T p2: b.getClusterElements()){
                Pair<T,T> p = new Pair(p1,p2);
                double dis =-1;
                if(this.pairwise_distances.containsKey(p)){
                    dis = pairwise_distances.get(p);
                }else{
                    dis = distancefunction.apply(p1,p2);
                    pairwise_distances.put(p,dis);
                }

                if(dis<min)
                    min = dis;
            }
        return min;
    }


}
