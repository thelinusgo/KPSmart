/**
 * Created by linus on 31/05/17.
 */
/**
 * This JS file is responsible for sending and receiving data from the server as JS files.
 */

var JSONObj = null; //initialize an empty JSONObj that can be accessed. It is set once a message is being passed in.
var runLocally = true; // set to false when running with server, used for testing locally
/**
 * Sends data from the website and converts it into a Json file.
 * Format of stringObj e.g. '{"id":0, "data":{"messageType":"event",...}}'
 * @param message
 */
function sendData(json_message){
    if (runLocally){return;}
    if(json_message == null){
        alert("Error: There must be a message to pass in");
    }
    // attach id
    json_message = {"id":sessionStorage.getItem("id"), data:json_message};
    var stringObj =  JSON.stringify(json_message);

    //TODO need to send stringObj to server??

}
/**
 * Receives data from the server and places it back into the website.
 * @param message
 */
function receiveData(message){
    if(message == null){
        alert("Received null message");
    }
    message = JSON.parse(message);

    // Set id message format example: {"messageType":"setId", "id":0}
    if (message.messageType == "setId"){
        sessionStorage.setItem("id", message.id);
    }
    // Event message JSON format example: {"messageType":"event","event":{"eventType":"Delivery Request","origin":"a","destination":"b",
    // "mailPriority":"InternAir","weight":"1","volume":"2","date":{"day":"1","month":"2","year":"2017"}}}
    else if (message.messageType == "event"){
        addNewEvent(message.event);
    }

    else

    alert("message: " + message.toString());

    this.JSONObj = message;


    //TODO need to implement the rest of this part.
}



/**
 * Returns the JSON object that is stored from receiveData.
 * @returns {*}
 */
function getJSONObject(){
    return this.JSONObj;
}


