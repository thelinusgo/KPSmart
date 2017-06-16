/**
 * Created by Edward on 15/06/2017.
 */

function updateBusinessFigures(){
    var events = JSON.parse(sessionStorage.getItem("events"));
    var totalRevenue = 0;
    var totalExpenditure = 0;
    var totalEvents = 0;
    var totalMail = 0;
    var avgDeliveryTimes = 0;
    var criticalRoutes = 0; // TODO implement if have time



    for (var i in events){
        var event = events[i];

        if (event.eventType == "Delivery Request"){
            // Calculate totalRevenue
            totalRevenue += parseFloat(event.customerPrice); //TODO: use this when customerPrice exists
            // Calculate totalExpenditure
            totalExpenditure += parseFloat(event.transportCost); //TODO: use this when transportCost exists
            // Calculate totalMail
            totalMail++;
            // Average delivery times TODO: implement if have time
            if (event.priority == "InternAir"){

            } else if (event.priority == "InternStd"){

            } else if (event.priority == "DomestAir"){

            } else if (event.priority == "DomestStd"){

            }
        }
        // Calculate totalEvents
        totalEvents++;
    }

    var businessFigs = JSON.parse(sessionStorage.getItem("businessFigs"));

    businessFigs.totalRevenue = totalRevenue;
    businessFigs.totalExpenditure = totalExpenditure;
    businessFigs.eventCount = totalEvents;
    businessFigs.mailDelivered = totalMail;
    businessFigs.avgDeliveryTimes = avgDeliveryTimes;
    businessFigs.criticalRoutes = criticalRoutes;

    sessionStorage.setItem("businessFigs", JSON.stringify(businessFigs));
}