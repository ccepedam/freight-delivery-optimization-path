# freight-delivery-optimization-path

Author: Carlos Andres Cepeda

This is a project for Advanced Algortimts class. 
The optimization problem to solve is described on the .pdf document.
I combined the TSP and Shortes path algorithms with additional math tricks for solving the required problem.
Stack: Gradle, Java, JavaFx.
Note: requires google maps API private key on "src/main/java/My_Package/DATAGUI.java"

To build:
gradle fatJar

To execute:
cd build/libs
java -jar fdsgui-all-1.0-SNAPSHOT.jar


References:
https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/
DijkstraShortestPath.java
TravelingSalesmanHeldKarp.java
BinaryMinHeap.java
@author Tushar Roy
