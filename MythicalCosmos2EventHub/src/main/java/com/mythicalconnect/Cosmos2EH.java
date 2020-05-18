package com.mythicalconnect;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with Cosmos DB trigger.
 */
public class Cosmos2EH {
    /**
     * This function will be invoked when there are inserts or updates in the specified database and collection.
     */
    @FunctionName("Cosmos2EH")
    public void run(
        @CosmosDBTrigger(
            name = "items",
            databaseName = "bulkImportDb",
            collectionName = "c3",
            connectionStringSetting = "AzureCosmosDbConnString",
            createLeaseCollectionIfNotExists = true,
            maxItemsPerInvocation=1
        )
        String item,
        @EventHubOutput(name = "event", 
        eventHubName = "connectme", 
        connection = "AzureEventHubConnection") OutputBinding<Object> toEH,
        final ExecutionContext context
    ) {


        context.getLogger().info(item.toString());
        toEH.setValue(item);
       // context.getLogger().info("Documents count: " + items.length);
       // int nI=0;
       /* for (String obj : items) {
            toEH.setValue(obj); 
            nI++;
            context.getLogger().info("To EH : " + nI);  
            context.getLogger().info(obj.toString());
        }*/
    }
}
