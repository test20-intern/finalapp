package com.example.salesBackend.Service;

import com.example.salesBackend.Dto.Response.ClicksCountRecordsDTO;
import com.example.salesBackend.Entity.ClicksCountRecords;
import com.example.salesBackend.Repo.ClicksCountRecordsRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ClicksCountRecordsService {

    @Autowired
    private ClicksCountRecordsRepo clicksCountRecordsRepo;
    @Autowired
    private ModelMapper modelMapper;

    public void clicksCountRecordsUpdate(ClicksCountRecordsDTO clicksCountRecordsDTO){
       try{
           ClicksCountRecords clicksCountRecords = clicksCountRecordsRepo.findByEmpIdAndLoginDate(clicksCountRecordsDTO.getEmpId(), LocalDate.now());
           ClicksCountRecords saveClicksCountRecords = new ClicksCountRecords();


           if(clicksCountRecords != null){
               clicksCountRecords = updateCount(clicksCountRecords, clicksCountRecordsDTO.getFeature());
               saveClicksCountRecords = modelMapper.map(clicksCountRecords, ClicksCountRecords.class);
           }
           else {
               saveClicksCountRecords.setEmpId(clicksCountRecordsDTO.getEmpId());
               saveClicksCountRecords.setEmpName(clicksCountRecordsDTO.getEmpName());
               saveClicksCountRecords.setCategory(clicksCountRecordsDTO.getCategory());
               saveClicksCountRecords.setBirthday(0);
               saveClicksCountRecords.setReports(0);
               saveClicksCountRecords.setCollectionReports(0);
               saveClicksCountRecords.setDashboard(0);
               saveClicksCountRecords.setPolicyInquiry(0);
               saveClicksCountRecords.setLoginDate(LocalDate.now());
               saveClicksCountRecords = updateCount(saveClicksCountRecords, clicksCountRecordsDTO.getFeature());
           }
           if(clicksCountRecordsRepo.save(saveClicksCountRecords) != null){
               System.out.println(saveClicksCountRecords);
               System.out.println("success");
           }

       }catch (Exception exception){
           throw exception;
       }
    }

    public ClicksCountRecords updateCount(ClicksCountRecords clicksCountRecords, String feature)
    {
        if(feature.equals("Birthdays")){clicksCountRecords.setBirthday(clicksCountRecords.getBirthday()+1);}
        if(feature.equals("Reports")){clicksCountRecords.setReports(clicksCountRecords.getReports()+1);}
        if(feature.equals("Policy Inquiry")){clicksCountRecords.setPolicyInquiry(clicksCountRecords.getPolicyInquiry()+1);}
        if(feature.equals("Dashboard")){clicksCountRecords.setDashboard(clicksCountRecords.getDashboard()+1);}
        if(feature.equals("Collection Report")){clicksCountRecords.setCollectionReports(clicksCountRecords.getCollectionReports()+1);}

        return clicksCountRecords;
    }

}
