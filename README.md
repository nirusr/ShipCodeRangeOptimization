# ShipCodeRangeOptimization
Java Coding Exercise
BACKGROUND
Sometimes items cannot be shipped to certain zip codes, and the rules for these restrictions are stored as a series of ranges of 5 digit codes. 
For example if the ranges are:
[94133,94133] [94200,94299] [94600,94699]
Then the item can be shipped to zip code 94199, 94300, and 65532, but cannot be shipped to 94133, 94650, 94230, 94600, or 94299.
Any item might be restricted based on multiple sets of these ranges obtained from multiple sources.
PROBLEM
Given a collection of 5 digit ZIP code ranges (each range includes both their upper and lower bounds), provide an algorithm that produces the minimum number of ranges required to represent the same restrictions as the input . 

Solution:
1. Clone the repository

2. Clean and build using Maven

3. Create a txt file with following entries (each line with two zipcodes, separated by comma)
   filename: zipcode.txt
   Eg.
    
    94133,94133
       
    94200,94299
    
    94600,94699
    
    94200,94299
    
    94300,94305
    
4. Execute the java program
java -cp target/ShipCodeRangeOptimization-1.0-SNAPSHOT.jar com.srini.optimize.NonShippableZip  <directory name/zipcode.txt>
e.g. java -cp target/ShipCodeRangeOptimization-1.0-SNAPSHOT.jar com.srini.optimize.NonShippableZip ../zipcode.txt

5. Ouput the optimized range of zipcode (sysout)
