package HierarchicalClustering;

import HierachicalClustering.Cluster;
import HierachicalClustering.ClusterAlgorithm;
import HierachicalClustering.SingleLinkage;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by maltegr on 30.08.17.
 */
public class tests {

    @Test
    public void single_linkage(){
        List<Double> vals = Arrays.asList(1d,2d,3d,10d);
        ClusterAlgorithm<Double> h = new ClusterAlgorithm<Double>(vals,new SingleLinkage<Double>((o, o2) -> Math.abs(o-o2)));
        h.cluster();
        Cluster<Double> c10 = new Cluster(10d);
        Cluster<Double> c1 = new Cluster(1d);
        Cluster<Double> c2 = new Cluster(2d);
        Cluster<Double> c3 = new Cluster(3d);
        Cluster<Double> top = new Cluster(c10,new Cluster(c1,new Cluster(c2,c3,1d),1d),7d);
        assertEquals(h.getClusters().iterator().next(),top);
    }

    @Test
    public void clustering_succeeds(){
        List<Double> d= new LinkedList<>();
        for (int i = 0; i < 500; i++) {
            d.add(Math.random()*100);

        }
        ClusterAlgorithm<Double> h = new ClusterAlgorithm<Double>(d,new SingleLinkage<Double>((o, o2) -> Math.abs(o-o2)));
        h.cluster();
        assertEquals(1,h.getClusters().size()); //terminates if only one cluster is left over
    }
}
