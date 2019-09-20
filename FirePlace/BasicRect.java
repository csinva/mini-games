import java.util.*;
public class BasicRect extends Rectangle{
	Set<Integer> possibleDirections = new HashSet<Integer>();
	public BasicRect(int x,int y){
		super(x,y);
		possibleDirections.add(1);
		possibleDirections.add(3);
		possibleDirections.add(5);
		possibleDirections.add(7);
	}
}