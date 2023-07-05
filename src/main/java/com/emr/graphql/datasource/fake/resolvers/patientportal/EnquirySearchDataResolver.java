package com.emr.graphql.datasource.fake.resolvers.patientportal;

import com.netflix.dgs.codegen.generated.DgsConstants;
import com.netflix.dgs.codegen.generated.types.MessageSearchFilter;
import com.netflix.dgs.codegen.generated.types.Searchable;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;

@DgsComponent
public class EnquirySearchDataResolver {
    @DgsQuery
            public List<Searchable> messageSearch
            (@InputArgument MessageSearchFilter filter){
        return null;
    }
}
