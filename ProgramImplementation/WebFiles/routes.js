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

    /**
     * Adds a vertex to vertices
     * @param cityName name of city e.g. Manila
     * @param edges array of connecting cities and costs e.g. [ { CityName: 'Auckland', Distance: '7' } ]
     */
    this.addVertex = function(cityName, edges){
        this.vertices[cityName] = edges;
    }



    while(!nodes.isEmpty()) {
        smallest = nodes.dequeue();

        if(smallest === finish) {
            path = [];

            while(previous[smallest]) {
                path.push(smallest);
                smallest = previous[smallest];
            }

            break;
        }

        if(!smallest || distances[smallest] === INFINITY){
            continue;
        }

        for(neighbor in this.vertices[smallest]) {
            alt = distances[smallest] + this.vertices[smallest][neighbor];

            if(alt < distances[neighbor]) {
                distances[neighbor] = alt;
                previous[neighbor] = smallest;

                nodes.enqueue(alt, neighbor);
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
        map.addVertex(city.CityName, city.NeighbouringCities);
    }
    console.log(map.vertices);
}
