package com.masterspring.backenddayoff.service.impl;

import com.masterspring.backenddayoff.dto.request.CreateHolidayRequest;
import com.masterspring.backenddayoff.dto.response.HolidayResponse;
import com.masterspring.backenddayoff.entity.Holiday;
import com.masterspring.backenddayoff.entity.User;
import com.masterspring.backenddayoff.exception.AppException;
import com.masterspring.backenddayoff.mapper.HolidayMapper;
import com.masterspring.backenddayoff.repository.HolidayRepositoy;
import com.masterspring.backenddayoff.repository.UserRepository;
import com.masterspring.backenddayoff.service.HolidayService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HolidayServiceImpl implements HolidayService {
    HolidayRepositoy holidayRepositoy;
    HolidayMapper holidayMapper;
    UserRepository userRepository;

    @Override
    public boolean createHoliday(CreateHolidayRequest createHolidayRequest) {
        User user = userRepository.findById(createHolidayRequest.getUserId())
                .orElseThrow(() -> new AppException(404, "User Not Found"));
        if(user.getRole() == 2) throw new AppException(401, "Unauthorized");
        if(holidayRepositoy.existsByDateBetween(createHolidayRequest.getStartDate()))
            throw new AppException(404, "Holiday already exists");
        Holiday holiday = holidayMapper.toHoliday(createHolidayRequest);
        LocalDate endDate = createHolidayRequest.getStartDate().plusDays(createHolidayRequest.getLeaveDays() - 1);
        holiday.setEndDate(endDate);
        holiday.setCreatedAt(LocalDateTime.now());
        holiday.setUser(user);
        holidayRepositoy.save(holiday);
        return true;
    }

    @Override
    public List<HolidayResponse> getAllHolidays() {
        return holidayRepositoy.findAll().stream()
                .map(holidayMapper::toHolidayResponse).toList();
    }
}
