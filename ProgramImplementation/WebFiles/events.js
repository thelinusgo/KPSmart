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

    alert("the event type:" + event.eventType);

    // add route to discontinuedRoutes
    if (event.eventType=="Discontinue Route"){
        discontinueRoute(event.origin, event.destination);
    }
    else if (event.eventType == "Delivery Request"){
        findShortestPath(event.origin, event.destination, event.weight, event.volume);
        if(sessionStorage.getItem("totalDistance") == 0){
            return;
        }
    }
    else if (event.eventType == "Transport Cost Update"){
        addRoute(event);
    }
    else if (event.eventType == "Customer Price Update"){
        addCustomerPriceToRoute(event);
    }
    events.push(event);
    sessionStorage.setItem("events",JSON.stringify(events));
    
    updateBusinessFigures();
}


/**
 * Imports events from server
 */
function importEvents(){
    //TODO: implement once integrated with server
}