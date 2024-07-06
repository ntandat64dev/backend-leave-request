package com.masterspring.backenddayoff.controller;

import com.masterspring.backenddayoff.dto.request.CreateHolidayRequest;
import com.masterspring.backenddayoff.dto.response.HolidayResponse;
import com.masterspring.backenddayoff.entity.Holiday;
import com.masterspring.backenddayoff.service.HolidayService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/holiday")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class HolidayController {
    HolidayService holidayService;

    @GetMapping
    public ResponseEntity<List<HolidayResponse>> getListHoliday() {
        return ResponseEntity.ok(holidayService.getAllHolidays());
    }

    @PostMapping
    public ResponseEntity<Boolean> createHoliday(@RequestBody CreateHolidayRequest createHolidayRequest) {
        boolean result = holidayService.createHoliday(createHolidayRequest);
        return ResponseEntity.ok(result);
    }

}
