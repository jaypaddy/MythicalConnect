package com.mythicalconnect;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import java.util.*;
import java.io.*;
import java.net.*;

/**
 * Azure Functions with Event Hub trigger.
 */
public class EH2Cosmos {
    /**
     * This function will be invoked when an event is received from Event Hub.
     */
    @FunctionName("EH2Cosmos")
    public void run(
        @EventHubTrigger(name = "message", 
        eventHubName = "connectme", 
        connection = "AzureEventHubConnection", 
        consumerGroup = "connectmecg", 
        cardinality = Cardinality.ONE) String message,
        @CosmosDBOutput(name = "outputItem",
          databaseName = "bulkImportDb",
          collectionName = "returns",
          connectionStringSetting = "AzureCosmosDbConnString")
        OutputBinding<String> outputItem,
        final ExecutionContext context
    ) {
        context.getLogger().info("EH2Cosmos");
        context.getLogger().info(message);
        /*Processing*/
        /*Call another Function*/
        /*Wait of 10 Seconds*/
        try {
            //Thread.sleep(6000L);
            URL url = new URL("https://cmeapi-uat.azurewebsites.net/api/getParticipantByTeamId?teamId=84dfd1e1-8b54-40b9-a139-0fb60e40d960");
            //make connection
            URLConnection urlc = url.openConnection();
            urlc.setAllowUserInteraction(false);
    
            //get result
            BufferedReader br = new BufferedReader(new InputStreamReader(urlc
                .getInputStream()));
            String l = null;
            while ((l=br.readLine())!=null) {
                context.getLogger().info(l);
            }
            br.close();
        }
        catch (Exception ex){
            context.getLogger().info(ex.getMessage());
        }
        outputItem.setValue(message);
    }
}
