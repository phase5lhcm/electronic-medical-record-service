package com.emr.graphql.component.resolvers;

import com.emr.graphql.datasource.fake.FakeAlternativeMedicineDAO;
import com.netflix.dgs.codegen.generated.types.AlternativeMedicine;
import com.netflix.dgs.codegen.generated.types.AlternativeMedicineFilter;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DgsComponent
public class FakeAlternativeMedicineDataResolver {

    @DgsQuery(field = "alternativeMedicine")
    public List<AlternativeMedicine> getAlternativeMedicine(@InputArgument AlternativeMedicineFilter alternativeMedicineFilter) {
        if (alternativeMedicineFilter == null) {
            return FakeAlternativeMedicineDAO.ALTERNATIVE_MEDICINE_LIST;
        }
        System.out.println("your filter contains" + alternativeMedicineFilter);

        return FakeAlternativeMedicineDAO.ALTERNATIVE_MEDICINE_LIST.stream().filter(
                alternativeMedicine -> this.matchAlternativeMedicineFilter(alternativeMedicineFilter, alternativeMedicine)
        ).collect(Collectors.toList());
    }
    private boolean matchAlternativeMedicineFilter(@NotNull AlternativeMedicineFilter filter, AlternativeMedicine alternativeMedicine){
        // 1 check if service matches with service we have on record
        var isServiceMatch = StringUtils.containsIgnoreCase(alternativeMedicine.getService().toString(),
                StringUtils.defaultIfBlank(filter.getService(), StringUtils.EMPTY));
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
        var isPatientNameMatch = filter.getPatient() != null && !StringUtils.containsIgnoreCase(alternativeMedicine.getPatient().getName(),
                StringUtils.defaultIfBlank(filter.getPatient().getName(), StringUtils.EMPTY)
        );
        if(isPatientNameMatch){
            return false;
        }
        return true;
    }
 }
