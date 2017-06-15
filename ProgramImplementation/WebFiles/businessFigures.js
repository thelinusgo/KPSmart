/**
 * Created by Edward on 15/06/2017.
 */

function updateBusinessFigures(){
    var events = JSON.parse(sessionStorage.getItem("events"));
    var totalRevenue = 0;
    var totalExpenditure = 0;
    var totalNumEvents = 0;
    var amountOfMail = 0;
    var avgDeliverTime = 0;
    var criticalRoutes = 0;

    for (var i in events){
        alert("event: "+JSON.stringify(events[i]));
    }

}