/**
 * Created by Edward on 6/06/2017.
 */

var testing = true;

// setup discontinued routes
var jsonNodes;
var discontinuedRoutes;
if (testing){
    jsonNodes = require('./cities.json'); // reads json cities.json into JSON object
    discontinuedRoutes = {"routes":[]};
    discontinueRoute("Sydney", "Auckland");//for testing
    addVertices(); // for testing
}
else{
    if (JSON.parse(sessionStorage.getItem("discontinuedRoutes")) == null){
        console.log("null discontinue")
        sessionStorage.setItem("discontinuedRoutes", '{"routes":[]}');
    }
    discontinuedRoutes = JSON.parse(sessionStorage.getItem("discontinuedRoutes"));var jsonNodes;
    $.getJSON('./cities.json', function(response){
        jsonNodes = response;
    })
}

function PriorityQueue () {
    this.nodes = [];

    this.enqueue = function (priority, key) {
        this.nodes.push({key: key, priority: priority });
        this.sort();
    };
    this.dequeue = function () {
        return this.nodes.shift().key;
    };
    this.sort = function () {
        this.nodes.sort(function (a, b) {
            return a.priority - b.priority;
        });
    };
    this.isEmpty = function () {
        return !this.nodes.length;
    };
}

function Map() {
    this.vertices = {}; // city edges pairs e.g. Manila: [ { CityName: 'Auckland', Distance: '7' } ]
    var INFINITY = 1/0; //constant variable for calculating infinity


    /**
     * Adds a vertex to vertices
     * @param cityName name of city e.g. Manila
     * @param edges array of connecting cities and costs e.g. [ { CityName: 'Auckland', Distance: '7' } ]
     */
    this.addVertex = function(cityName, edges){
        this.vertices[cityName] = edges;
    }

    /**
     * Calculates the shortest path between two cities.
     * Uses the priorityQueue in the above implementation
     * @param startCity
     * @param endCity
     */
    this.calculateShortestPath = function(startCity, endCity){


        var nodes = new PriorityQueue(); //a priority queue full of nodes.
        var distances = {};
        var previous = {}; //previously visited nodes.
        var path = []; //store the path being currently traversed.
        var smallest, vertex, neighbor, alt;

        for(vertex in this.vertices){
            //console.log(vertex);
            if(vertex === startCity){
             distances[vertex] = 0; //the distance of the start node is 0.
             //console.log("initialising start :" + vertex);
             nodes.enqueue(0, vertex);
            }else{
                distances[vertex] = INFINITY; //set the distance of the vertext to infinity
              //  console.log("initializing others :" + vertex);
                nodes.enqueue(INFINITY, vertex); //push it onto the stack
            }
            previous[vertex] = null; //set the previous vertex to null.
        }

        while(!nodes.isEmpty()) {
            smallest = nodes.dequeue();
          //  console.log("removing :" + smallest);

            if(smallest === endCity) {
            //    console.log("Found end");
                path = [];

                while(previous[smallest]) {
                  //  console.log("adding to path");
                    path.push(smallest);
                    smallest = previous[smallest];
                }

                break;
            }

            if(!smallest || distances[smallest] === INFINITY){

                continue;
            }

            for(neighbor in this.vertices[smallest]) {
                alt = parseInt(distances[smallest]) + parseInt(this.vertices[smallest][neighbor].Distance);
               // console.log(neighbor);
               // console.log(alt);
                //console.log(smallest + " neighbour : "+ neighbor + " distance " + this.vertices[smallest][neighbor].Distance);
                //console.log(distances[this.vertices[smallest][neighbor].CityName]);
                if(alt < distances[this.vertices[smallest][neighbor].CityName]) {
                    distances[this.vertices[smallest][neighbor].CityName] = alt;
                    previous[this.vertices[smallest][neighbor].CityName] = smallest;
                   // console.log("adding :" + neighbor);
                    nodes.enqueue(alt, this.vertices[smallest][neighbor].CityName);
                }
            }
        }
        return path;
    };
}

/**
 * adds routes from JSON object to vertices in graph
 */
function addVertices(){
    // console.log(jsonNodes);
    var map = new Map();
    for (var i in jsonNodes.cities){
        var city  = jsonNodes.cities[i];
        // delete discontinued routes
        for (var j in discontinuedRoutes.routes){
            var discontinuedRoute = discontinuedRoutes.routes[j];
            if (discontinuedRoute.origin == city.CityName){
                for (var k in city.NeighbouringCities){
                    if (discontinuedRoute.destination == city.NeighbouringCities[k].CityName){
                        city.NeighbouringCities.splice(k,1);
                    }
                }
            }
        }
        if (city.NeighbouringCities.length==0)continue;
        //add vertex
        map.addVertex(city.CityName, city.NeighbouringCities);
    }
    //console.log(map.vertices);

    console.log("shortest path: ");
    var city1 = jsonNodes.cities[0].CityName;
    var city2 = jsonNodes.cities[1].CityName;
   // console.log(map.vertices[city1]);
    var array = map.calculateShortestPath(city1, city2).concat(city1).reverse();

    var c1;
    var totalDistance=0;
    for (c2 in array){

        c2 = array[c2];
        //console.log(c2);
        if(c2 == city1){
            c1=city1;
           // console.log("Found first");
            continue;
        }
        for(neighbour in map.vertices[c2]){
         //   console.log(map.vertices[c2][neighbour].CityName);
            if(map.vertices[c2][neighbour].CityName == c1){
                totalDistance = parseInt(totalDistance)+ parseInt(map.vertices[c2][neighbour].Distance);
                //console.log("adding to total distance "+c1+ " to "+c2+ " ("+parseInt(map.vertices[c2][neighbour].Distance)+")");

            }
        }

        c1=c2;
    }
    console.log("Path = "+array);
    console.log("Distance "+totalDistance);

   // console.log("length: " + array.length);

    for(i in array){
        console.log(array[i]);
    }



}


function discontinueRoute(origin, destination){
    console.log("routes 1"+JSON.stringify(discontinuedRoutes));
    discontinuedRoutes.routes.push({"origin":origin,"destination":destination});
    if (!testing)sessionStorage.setItem("discontinuedRoutes", JSON.stringify(discontinuedRoutes));
    console.log("routes "+JSON.stringify(discontinuedRoutes));
}