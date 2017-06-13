/**
 * Created by Edward on 6/06/2017.
 */

// will need later when running from HTML files
// if (JSON.parse(sessionStorage.getItem("nodes")) == null){
//     var jsonFile = require('./cities.json');
//     sessionStorage.setItem("nodes", jsonFile);
// }
// var jsonNodes = JSON.parse(sessionStorage.getItem("nodes"));

var jsonNodes = require('./cities.json'); // reads json cities.json into JSON object

addVertices(); // for testing

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
            if(vertex === start){
             distances[vertex] = 0; //the distance of the start node is 0.
             nodes.enqueue(0, vertex);
            }else{
                distances[vertex] = INFINITY; //set the distance of the vertext to infinity
                nodes.enqueue(INFINITY, vertex); //push it onto the stack
            }
            previous[vertex] = null; //set the previous vertex to null.
        }


    }
}

/**
 * adds routes from JSON object to vertices in graph
 */
function addVertices(){
    // console.log(jsonNodes);
    var map = new Map();
    for (var i in jsonNodes.cities){
        var city  = jsonNodes.cities[i];
        map.addVertex(city.CityName, city.NeighbouringCities);
    }
    console.log(map.vertices);
}
