package com.masterspring.backenddayoff.service;

import com.masterspring.backenddayoff.dto.request.CreateHolidayRequest;
import com.masterspring.backenddayoff.dto.response.HolidayResponse;
import com.masterspring.backenddayoff.entity.Holiday;

import java.util.List;

public interface HolidayService {
    boolean createHoliday(CreateHolidayRequest createHolidayRequest);
    List<HolidayResponse> getAllHolidays();
}
