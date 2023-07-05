package com.emr.graphql.util;

import com.emr.graphql.datasource.entity.*;
import com.netflix.dgs.codegen.generated.types.UserAuthToken;
import com.ocpsoft.pretty.time.PrettyTime;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class GraphqlBeanMapper {

    private static final PrettyTime PRETTY_TIME = new PrettyTime();
    private static final ZoneOffset ZONE_OFFSET = ZoneOffset.ofHours(5); // offset for EST

    // now let us map our graphql schemas to our entities
    // the entire path for the types is included because I did not differentiate any difference in name
    // when naming the Entity vs graphql schema types
    public static com.netflix.dgs.codegen.generated.types.Patient mapToGraphql(Patient original){
        var result = new com.netflix.dgs.codegen.generated.types.Patient();
        var messageCreateDateTime = original.getCreate_time().atOffset(ZONE_OFFSET);

        result.setPatientID(original.getPatiendId().toString());
        result.setName(original.getPatientName());
        result.setEmail(original.getEmail());
        result.setDOB(original.getDOB());
//        result.setAddress(original.getAddress());
        result.setGender(original.getGender());
        result.setContactNumber(original.getContactNumber());
//        result.setPrimary(original.getPrimary());
//        result.setPreferredPharmacy(original.getPreferredPharmacy());
        result.setDateProfileCreated(messageCreateDateTime.toLocalDate());
        result.setDateProfileCreated(original.getDateProfileCreated());

        return result;
    }
    public static com.netflix.dgs.codegen.generated.types.OfficeAssistant mapToGraphql(OfficeAssistant original){
        var result = new com.netflix.dgs.codegen.generated.types.OfficeAssistant();

        result.setId(original.getId().toString());
        result.setUsername(original.getName());
        result.setEmail(original.getEmail());

        return result;
    }
    public static com.netflix.dgs.codegen.generated.types.Enquiry mapToGraphql(Enquiry original){
        var result = new com.netflix.dgs.codegen.generated.types.Enquiry();
        var enquiryCreatedDateTime = original.getCreatedDateTime().atOffset(ZONE_OFFSET);
        var enquiryPostedBy = mapToGraphql(original.getEnquiryCreatedBy());
        var solution = mapToGraphql(original.getSolution());

        result.setId(original.getId().toString());
        result.setTitle(original.getTitle());
        result.setMessage(original.getMessage());
        result.setPatient(enquiryPostedBy);
        result.setCreatedDateTime(enquiryCreatedDateTime);
        result.setPrettyCreateDateTime(enquiryCreatedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MMM-DD hh:mm")));
        result.setEnquiryResponses(solution);

        return result;
    }
    public static com.netflix.dgs.codegen.generated.types.Solution mapToGraphql(Solution original){
        var result = new com.netflix.dgs.codegen.generated.types.Solution();
        var solutionCreateDateTime = original.getCreatedDateTime().atOffset(ZONE_OFFSET);
        var solutionCreatedBy = mapToGraphql(original.getSolutionCreatedBy());
        var patientEnquiry = mapToGraphql(original.getPatientEnquiry());

        result.setId(original.getId().toString());
        result.setCreatedDateTime(solutionCreateDateTime);
        result.setTitle(original.getTitle());
        result.setMessage(original.getMessage());
        result.setOfficeAssistant(solutionCreatedBy);
        result.setEnquiry(patientEnquiry);
        result.setPrettyCreateDateTime(solutionCreateDateTime.format(DateTimeFormatter.ofPattern("yyyy-MMM-DD hh:mm")));

        return result;
   }

   public static UserAuthToken mapToGraphql(UserToken original){
        var result = new UserAuthToken();
        var expiryDateTime = original.getExpiryTimestamp().atOffset(ZONE_OFFSET);

        result.setExpiryTime(expiryDateTime.toString());
        result.setAuthToken(original.getAuthToken());

        return result;
   }
}
