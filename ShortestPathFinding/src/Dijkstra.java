/*K.W.A Thushini Maleesha Ranasinghe
UOW id: w1583023
IIT id: 2014013*/

import java.util.*;
public class Dijkstra {

    Node start;
    Node end;
    Node[][] gridArea;
 double mDistance;
 double eDistance;
 double cDistance;
 double totalCost;
 
 static int check=0;
    //Horizontal and VerticalDistance
    double hVDistance;

    //Diagonal Distance
    double dDistance;

    public Dijkstra(int metricNo) {
        switch (metricNo) {
            //for Manhattan distance
            case 0:
                check=1;
                
                hVDistance = 1.0;
                dDistance = 2.0;
                mDistance=1;
                break;
            //for Euclidean distance
            case 1:
                 
                hVDistance = 1.0;
                dDistance = 1.4;
                eDistance=1;
                break;
            //for Chebyshev distance
            case 2:
                 
                hVDistance = 1.0;
                dDistance = 1.0;
                cDistance=1;
                break;
        }

    }

    /**
     * @param matrix The boolean matrix from the framework given
     * @param si     start x value
     * @param sj     start y value
     * @param ei     end x value
     * @param ej     end x value
     * @return The path nodes
     */
    ArrayList<Node> distance(boolean[][] matrix, int si, int sj, int ei, int ej) {

        int size = matrix.length;

        start = new Node(si, sj);
        end = new Node(ei, ej);
        // The grid that is used to store nodes
        gridArea = new Node[size][size];

        // Creating nodes and finding blocked cells in matrix and mapping accordingly to our grid
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                gridArea[i][j] = new Node(i, j);
                if (matrix[i][j] == false) {
                    gridArea[i][j].blocked = true;
                }
            }
        }

        // setting start distance to 0.
        // All other nodes will have infinity distance at the beginning
        start.distance = 0;

        // a comparator object to deal with Priority Queue
        Comparator<Node> adjacencyComparator = (left, right) -> {
            if (left.distance > (right.distance)) {
                return 1;
            }
            return -1;
        };

        // Queue to store visiting nodes
        Queue<Node> queueB = new PriorityQueue(size, adjacencyComparator);

        queueB.add(start);

        while (queueB.size() > 0) {
            Node current = queueB.remove();
            Node t;
            // Top
            if (current.x - 1 >= 0) {

                // Top Top
                t = gridArea[current.x - 1][current.y];
                if (!t.visited && !t.blocked && t.distance > current.distance + hVDistance) {
                    t.distance = current.distance + hVDistance;
                    t.parent = current;
                    queueB.add(t);
                }
                if(check==0){
                // Top Left
                if (current.y - 1 > 0) {
                    t = gridArea[current.x - 1][current.y - 1];
                    if (!t.visited && !t.blocked && t.distance > current.distance + dDistance) {
                        t.distance = current.distance + dDistance;
                        t.parent = current;
                        queueB.add(t);
                    }
                }

                // Top Right
                if (current.y + 1 < size) {
                    t = gridArea[current.x - 1][current.y + 1];
                    if (!t.visited && !t.blocked && t.distance > current.distance + dDistance) {
                        t.distance = current.distance + dDistance;
                        t.parent = current;
                        queueB.add(t);
                    }
                }
            }
            }

            // Left
            if (current.y - 1 >= 0) {
                t = gridArea[current.x][current.y - 1];
                if (!t.visited && !t.blocked && t.distance > current.distance + hVDistance) {
                    t.distance = current.distance + hVDistance;
                    t.parent = current;
                    queueB.add(t);
                }
            }

            // Right
            if (current.y + 1 < size) {
                t = gridArea[current.x][current.y + 1];
                if (!t.visited && !t.blocked && t.distance > current.distance + hVDistance) {
                    t.distance = current.distance + hVDistance;
                    t.parent = current;
                    queueB.add(t);
                }
            }
            // Down
            if (current.x + 1 < size) {

                // Down Down
                t = gridArea[current.x + 1][current.y];
                if (!t.visited && !t.blocked && t.distance > current.distance + hVDistance) {
                    t.distance = current.distance + hVDistance;
                    t.parent = current;
                    queueB.add(t);
                }
if(check==0){
                // Down Left
                if (current.y - 1 >= 0) {
                    t = gridArea[current.x + 1][current.y - 1];
                    if (!t.visited && !t.blocked && t.distance > current.distance + dDistance) {
                        t.distance = current.distance + dDistance;
                        t.parent = current;
                        queueB.add(t);
                    }
                }

                // Down Right
                if (current.y + 1 < size) {
                    t = gridArea[current.x + 1][current.y + 1];
                    if (!t.visited && !t.blocked && t.distance > current.distance + dDistance) {
                        t.distance = current.distance + dDistance;
                        t.parent = current;
                        queueB.add(t);
                    }
                }
}
            }
            current.visited = true;
        }

        ArrayList<Node> path = new ArrayList<>();

        // Checking if a path exists
        if (!(gridArea[end.x][end.y].distance == Integer.MAX_VALUE)) {
            //Trace back the path
            Node current = gridArea[end.x][end.y];
            path.add(current);
            totalCost= totalCost+current.distance;

            while (current.parent != null) {
                path.add(current.parent);
                current = current.parent;
            }
        } else System.out.println("No possible path");
        if(mDistance==1){
           System.out.println("Total cost of Manhattan is : "+totalCost);
        }else if(cDistance==1){
            System.out.println("Total cost of Chebyshev is : "+totalCost);
        }
        else if(eDistance==1){
            System.out.println("Total cost of Euclidean is : "+totalCost);
        }
        

        return path;
    }


    class Node {
        int x;
        int y;
        double distance = Integer.MAX_VALUE;
        Node parent = null;
        boolean visited;
        boolean blocked;
        public int cost;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            
            
        }
        
    }
}
