/**
 * Created by Edward on 1/06/2017.
 */


/** Example event JSON format: {"eventType":"Delivery Request","origin":"a","destination":"b",
* "mailPriority":"InternAir","weight":"1","volume":"2","date":{"day":"1","month":"2","year":"2017"}}
*/

/**
 * Adds a new event to the local storage of events
 * @param event : event to be added
 */
function addNewEvent(event){
    var events = JSON.parse(sessionStorage.getItem("events"));
    events.push(event);
    sessionStorage.setItem("events",JSON.stringify(events))

    // add route to discontinuedRoutes
    if (event.eventType=="Discontinue Route"){
        discontinueRoute(event.origin, event.destination);
    }
    else if (event.eventType == "Request Delivery"){
        findShortestRoute(event.origin, event.destination);
    }
    else if (event.eventType == "Transport Cost Update"){
        addRoute(event);
        alert(sessionStorage.getItem("cityNodes"));
    }
}


/**
 * Imports events from server
 */
function importEvents(){
    //TODO: implement once integrated with server
}