/**
 * Created by Edward on 15/06/2017.
 */

function updateBusinessFigures(){
    var events = JSON.parse(sessionStorage.getItem("events"));
    var cityNodes = JSON.parse(sessionStorage.getItem("cityNodes"));
    var totalRevenue = 0;
    var totalExpenditure = 0;
    var totalEvents = 0;
    var totalMail = 0;
    var avgDeliveryTimes = 0;
    var criticalRoutes = [];

    for (var i in events){
        var event = events[i];
        if (event.eventType == "Delivery Request"){
            // Calculate totalRevenue
            totalRevenue += parseFloat(event.customerPrice);
            // Calculate totalExpenditure
            totalExpenditure += parseFloat(event.transportCost);
            // Calculate totalMail
            totalMail++;
        }
        // Calculate totalEvents
        totalEvents++;
    }

    for (var i in cityNodes.cities){
        var city = cityNodes.cities[i];
        for (var j in city.NeighbouringCities){
            var neighbour = city.NeighbouringCities[j];
            var transportCost = parseFloat(neighbour.PricePerGram)+parseFloat(neighbour.PricePerCC);
            var customerPrice = parseFloat(neighbour.CustomerPricePerGram)+parseFloat(neighbour.CustomerPricePerCC);
            if (transportCost>customerPrice){
                criticalRoutes.push({"origin":city.CityName, "destination":neighbour.CityName})
            }
        }
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