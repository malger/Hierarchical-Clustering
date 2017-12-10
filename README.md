# [Hierarchical-Clustering](https://github.com/malger/Hierarchical-Clustering)
A java implementation of hierarchical clustering. No external dependencies needed, generic implementation.

## Supports different Linkage approaches:
* Average Linkage
* Single Linkage
* Your Linkage function (if you extend the Linkage class)


## Example usage with single Linkage
```java
List<Double> vals= Arrays.asList(1d,2d,10d); //your data
Linkage la = new SingleLinkage<Double>((o, o2) -> Math.abs(o-o2));  //lamda distance function
ClusterAlgorithm<Double> h = new ClusterAlgorithm<Double>(vals,la); 
h.cluster(); //run 
System.out.println(h.getFirstCluster());
System.out.println("Top Cluster distance: "+h.getFirstCluster().distance);


```

#### Sample output for example above
```
((2.0 , 1.0) , 10.0)
Top Cluster distance: 8.0
```

## Implementation Details:
* Clusters are modeled similar to a binary tree (a cluster contains right,left child)
* Linkage values between all Pairs are hashed

