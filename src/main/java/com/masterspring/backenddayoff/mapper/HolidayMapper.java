package com.masterspring.backenddayoff.mapper;

import com.masterspring.backenddayoff.dto.request.CreateHolidayRequest;
import com.masterspring.backenddayoff.dto.response.HolidayResponse;
import com.masterspring.backenddayoff.entity.Holiday;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HolidayMapper {
    Holiday toHoliday(CreateHolidayRequest createHolidayRequest);
    @Mapping(target = "userName", source = "user.fullName")
    HolidayResponse toHolidayResponse(Holiday holiday);
}
