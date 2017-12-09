package HierachicalClustering;

import java.util.Objects;

/**
 * Class representing an unmodifiable order independent Pair
 * <br> Access using final public fields
 * @param <A> Generic Type for first object
 * @param <B> Generic Type for second object
 */
public class Pair<A,B>{
    public final A first; //first element
    public final B second; //second element

    /**
     * Default Constructor
     * @param a Object a
     * @param b Object b
     */
    public Pair(final A a,final B b){
        first =a;
        second = b;
    }
    @Override
    public int hashCode(){
        return Objects.hash(first)+Objects.hash(second);
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        if (!Pair.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Pair<A,B> other = (Pair<A, B>) obj;
        if (this.first.equals(other.first) && this.second.equals(other.second))
            return true;
        if (this.first.equals(other.second) && this.second.equals(other.first))
            return true;
        return false;

    }
    @Override
    public String toString(){
        return "("+first.toString()+","+second.toString()+")";
    }
}
