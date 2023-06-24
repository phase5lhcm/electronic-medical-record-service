package com.emr.graphql.component.resolvers;

import com.emr.graphql.datasource.fake.FakeAlternativeMedicineDAO;
import com.netflix.dgs.codegen.generated.DgsConstants;
import com.netflix.dgs.codegen.generated.types.AlternativeMedicine;
import com.netflix.dgs.codegen.generated.types.AlternativeMedicineFilter;
import com.netflix.dgs.codegen.generated.types.Patient;
import com.netflix.dgs.codegen.generated.types.PatientFilter;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@DgsComponent
public class FakeAlternativeMedicineDataResolver {

//    @DgsQuery(field = "alternativeMedicine")
//    public List<AlternativeMedicine> getAlternativeMedicine(@InputArgument AlternativeMedicineFilter alternativeMedicineFilter) {
//        if (alternativeMedicineFilter == null) {
//           // System.out.println("filter is empty");
//            return FakeAlternativeMedicineDAO.ALTERNATIVE_MEDICINE_LIST;
//        }
//      //  System.out.println("filter is full " + alternativeMedicineFilter);
//
//        return FakeAlternativeMedicineDAO.ALTERNATIVE_MEDICINE_LIST.stream().filter(
//                alternativeMedicine -> this.matchAlternativeMedicineFilter(alternativeMedicineFilter, alternativeMedicine)
//        ).collect(Collectors.toList());
//    }

    @DgsQuery(field = "alternativeMedicine")
    public List<AlternativeMedicine> getAlternativeMedicine(@InputArgument AlternativeMedicineFilter alternativeMedicineFilter) {
        if (alternativeMedicineFilter.getPatient() == null) {
            // System.out.println("filter is empty");
            return FakeAlternativeMedicineDAO.ALTERNATIVE_MEDICINE_LIST;
        }
        //  System.out.println("filter is full " + alternativeMedicineFilter);

        return FakeAlternativeMedicineDAO.ALTERNATIVE_MEDICINE_LIST.stream().filter(
                alternativeMedicine -> StringUtils.containsIgnoreCase(alternativeMedicine.getService().toString(), alternativeMedicineFilter.getService())
                        || alternativeMedicineFilter.getPatient().getMedicalRecordNumber().equals(alternativeMedicine.getPatient().getMedicalRecordNumber())
        ).collect(Collectors.toList());
    }
    private boolean matchAlternativeMedicineFilter(AlternativeMedicineFilter filter, AlternativeMedicine alternativeMedicine){
        // 1 check if service matches with service we have on record
        var isServiceMatch = StringUtils.containsIgnoreCase(filter.getService(),
                StringUtils.defaultIfBlank(alternativeMedicine.getService().toString(), StringUtils.EMPTY));
        if(!isServiceMatch){
            return false;
        }

        // 2 validate patient input data
        // TODO - add validation for patient MRN as well
        // realistically patients are verified via DOB, name and MRN
        // basically - if the patient name filter includes the actual patient's name

        // check MRN
//        if(!filter.getPatient().getMedicalRecordNumber().equals(alternativeMedicine.getPatient().getMedicalRecordNumber())){
//            return false;
//        }
        //  var isPatientMrnMatch = filter.getPatient().getMedicalRecordNumber().equals(alternativeMedicine.getPatient().getMedicalRecordNumber());
        var isPatientNameMatch = filter.getPatient() != null && StringUtils.containsIgnoreCase(filter.getPatient().getName(),
                StringUtils.defaultIfBlank(alternativeMedicine.getPatient().getName(), StringUtils.EMPTY)
        );
        if(!isPatientNameMatch){
            return false;
        }
        return true;
    }
 }
