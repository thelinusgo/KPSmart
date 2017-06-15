/**
 * Created by Edward on 6/06/2017.
 */

var testing = false;

// setup discontinued routes
var jsonNodes;
var discontinuedRoutes;
if (testing){
    jsonNodes = require('./cities.json'); // reads json cities.json into JSON object
    discontinuedRoutes = {"routes":[]};
    var map = createMap(); // for testing
    findShortestRoute(map);
} else{
    $.getJSON('./cities.json', function(response){
        jsonNodes = response;
        // read cities.json into cityNodes variable in sessionStorage
        if (JSON.parse(sessionStorage.getItem("cityNodes")) == null){
            sessionStorage.setItem("cityNodes", JSON.stringify(jsonNodes));
        }
        else {
            jsonNodes = JSON.parse(sessionStorage.getItem("cityNodes"));
        }
    })

    // set up discontinued routes
    if (JSON.parse(sessionStorage.getItem("discontinuedRoutes")) == null){
        sessionStorage.setItem("discontinuedRoutes", '{"routes":[]}');
    }
    discontinuedRoutes = JSON.parse(sessionStorage.getItem("discontinuedRoutes"));

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
 * Creates a map from cities in cities.json
 */
function createMap(){
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
    return map;
}

function  findShortestPath(origin, destination) {
    var map = createMap();
    findShortestRoute(map, origin, destination);

}


function findShortestRoute(map, origin, destination){
    //console.log(map.vertices);

    console.log("shortest path: ");
    var city1 = origin;
    var city2 = destination;
    // console.log(map.vertices[city1]);
    var array = map.calculateShortestPath(city1, city2).concat(city1).reverse();

    var c1;
    sessionStorage.setItem("totalDistance", 0);
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
                sessionStorage.setItem("totalDistance", parseInt(sessionStorage.getItem("totalDistance"))+ parseInt(map.vertices[c2][neighbour].Distance));
                //console.log("adding to total distance "+c1+ " to "+c2+ " ("+parseInt(map.vertices[c2][neighbour].Distance)+")");
            }
        }

        c1=c2;
    }
    console.log("Path = "+array);
    console.log("Distance "+ sessionStorage.getItem("totalDistance"));
    alert("Successfully requested a delivery. \n The path taken is: " + array + " \n The total distance travelled is " + sessionStorage.getItem("totalDistance"));
    // console.log("length: " + array.length);

    for(i in array){
        console.log(array[i]);
    }
}

/**
 * Adds route to list of discontinuedRoutes
 * @param origin
 * @param destination
 */
function discontinueRoute(origin, destination){
    discontinuedRoutes.routes.push({"origin":origin,"destination":destination});
    if (!testing){sessionStorage.setItem("discontinuedRoutes", JSON.stringify(discontinuedRoutes));}
}

/**
 * Adds route to map
 * @param event
 */
function addRoute (event){
    var map = createMap();
    if (routeExists(event))return;
    // add route
    //check if origin node already exists
    for (var i in jsonNodes.cities) {
        var city = jsonNodes.cities[i];
        if (city.CityName == event.origin) {
            //origin node already exists, add to neighbour nodes
            city.NeighbouringCities.push({"CityName":event.destination, "Distance":event.duration,
                "PricePerGram":event.pricePerGram, "PricePerCC":event.pricePerCC, "CustomerPricePerGram":event.pricePerGram+5, "CustomerPricePerCC":event.pricePerCC+5});
            sessionStorage.setItem("cityNodes", JSON.stringify(jsonNodes));
            return;
        }
    }
    // origin node does not already exist
    jsonNodes.cities.push({"CityName":event.origin,"NeighbouringCities":[{"CityName":event.destination, "Distance":event.duration,
        "PricePerGram":event.pricePerGram, "PricePerCC":event.pricePerCC, "CustomerPricePerGram":event.pricePerGram+5, "CustomerPricePerCC":event.pricePerCC+5}]});
    sessionStorage.setItem("cityNodes", JSON.stringify(jsonNodes));
}

/**
 * Checks if a route already exists and updates prices
 * @param origin
 * @param destination
 * @returns {boolean}
 */
function routeExists(event){
    for (var i in jsonNodes.cities){
        var city = jsonNodes.cities[i];
        if (city.CityName == event.origin){
            for (var j in city.NeighbouringCities){
                var neighbour = city.NeighbouringCities[j];
                if (neighbour.CityName == event.destination){
                    if (event.eventType == "Transport Cost Update") {
                        jsonNodes.cities[i].NeighbouringCities[j].Distance = event.duration;
                        jsonNodes.cities[i].NeighbouringCities[j].PricePerGram = event.pricePerGram;
                        jsonNodes.cities[i].NeighbouringCities[j].PricePerCC = event.pricePerCC;
                    } else if (event.eventType == "Customer Price Update"){
                        jsonNodes.cities[i].NeighbouringCities[j].CustomerPricePerGram = event.pricePerGram;
                        jsonNodes.cities[i].NeighbouringCities[j].CustomerPricePerCC = event.pricePerCubic;
                    }
                    sessionStorage.setItem("cityNodes", JSON.stringify(jsonNodes));
                    return true;
                }
            }
        }
    }
    return false;
}

/**
 * Adds price for customer to ship on a route
 * @param event
 */
function addCustomerPriceToRoute(event) {
    if (routeExists(event)){
        alert("Customer Price updated successfully");
        alert(JSON.stringify(jsonNodes));
    } else {
        alert("Invalid origin or destination, route does not exist");
    }
}

function getDuration(origin, destination){
    for (var i in jsonNodes.cities){
        var city = jsonNodes.cities[i];
        if (city.CityName == origin){
            for (var j in city.NeighbouringCities){
                if (city.NeighbouringCities[j]==destination){
                    return city.NeighbouringCities[i].Distance;
                }
            }
        }
    }
}