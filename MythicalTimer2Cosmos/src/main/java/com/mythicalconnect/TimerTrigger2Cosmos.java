package com.mythicalconnect;

import java.time.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with Timer trigger.
 */
public class TimerTrigger2Cosmos {
    /**
     * This function will be invoked periodically according to the specified schedule.
     */
    @FunctionName("TimerTrigger2Cosmos")
    public void run(
        @TimerTrigger(name = "timerInfo", schedule = "*/1 * * * * *") String timerInfo,
        @CosmosDBOutput(name = "outputItem",
          databaseName = "bulkImportDb",
          collectionName = "c3",
          connectionStringSetting = "AzureCosmosDbConnString")
        OutputBinding<String> outputItem,
        final ExecutionContext context
    ) {
        String addMemberRequestBody = String.format("{\"id\":\"%d\"}", Instant.now().toEpochMilli());
        outputItem.setValue(addMemberRequestBody);
    }
}
