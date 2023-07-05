package com.emr.graphql.datasource.fake.resolvers.patientportal;

import com.netflix.dgs.codegen.generated.DgsConstants;
import com.netflix.dgs.codegen.generated.types.OfficeAssistant;
import com.netflix.dgs.codegen.generated.types.OfficeAssistantInput;
import com.netflix.dgs.codegen.generated.types.OfficeAssistantResponse;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.web.bind.annotation.RequestHeader;

@DgsComponent
public class OfficeAssistantDataResolver {
        @DgsQuery(field = "officeAssistant")
        public OfficeAssistant accountInfo(@RequestHeader(name = "authToken", required = true) String authToken){
            return null;
        }

        // let's create out mutations :)
        @DgsMutation(field = "officeAssistantCreate")
        public OfficeAssistantResponse createOfficeAssistant
    (@InputArgument(name = "officeAssistant")OfficeAssistantInput officeAssistantInput){
            return null;
    }

    @DgsMutation
   public OfficeAssistantResponse officeAssistantLogin
           (@InputArgument(name = "officeAssistant") OfficeAssistantInput officeAssistantLoginInput){
            return null;
   }


}
