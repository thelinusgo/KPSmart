/**
 * Created by linus on 31/05/17.
 */
/**
 * This JS file is responsible for sending and receiving data from the server as JS files.
 */

var JSONObj = null; //initialize an empty JSONObj that can be accessed. It is set once a message is being passed in.

/**
 * Sends data from the website and converts it into a Json file.
 * @param message
 */
function sendData(json_message){
    if(json_message == null){
        alert("There must be a message to pass in");
    }

    /*Returns the message being passed in as a String. */
    var stringObj =  JSON.stringify(json_message);
    alert("String object: " + stringObj);
    //TODO need to send stringObj to server??

}
/**
 * Receives data from the server and places it back into the website.
 * @param message
 */
function receiveData(message){
    if(message == null){
        alert("There must be a message to pass in");
    }
    message = JSON.parse(message);

    // Event message JSON format example: {"messageType":"event","event":{"eventType":"Delivery Request","origin":"a","destination":"b",
    // "mailPriority":"InternAir","weight":"1","volume":"2","date":{"day":"1","month":"2","year":"2017"}}}
    if (message.messageType == "event"){
        addNewEvent(message.event);
    }

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


