package HierachicalClustering;

import java.util.function.BiFunction;

/**
 * Implements Average Linkage
 * @param <T> generic type
 */
public class AverageLinkage<T> extends Linkage<T> {
    /**
     * Constructor
     * @param distancefunction calc distance between to generic objects
     */
    public AverageLinkage(BiFunction<T, T, Double> distancefunction) {
        super(distancefunction);
    }

    @Override
    public double calc(Cluster<T> a, Cluster<T> b) {
        double sum = 0;
        for (T p1: a.getClusterElements())
            for(T p2: b.getClusterElements()){
                double dis = -1;
                Pair<T,T> p = new Pair(p1,p2);

                if(this.pairwise_distances.containsKey(p)){
                    dis = pairwise_distances.get(p);
                }else{
                    dis = distancefunction.apply(p1,p2);
                    pairwise_distances.put(p,dis);
                }
                sum += dis;
                }
        double linkage = 1.0/(a.size*b.size)*sum;
        return linkage;
    }
}
