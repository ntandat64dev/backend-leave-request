package com.masterspring.backenddayoff.controller;

import com.masterspring.backenddayoff.dto.LeaveRequestStatusDto;
import com.masterspring.backenddayoff.dto.request.LeaveRequestPost;
import com.masterspring.backenddayoff.dto.response.LeaveRequestPaginationResponse;
import com.masterspring.backenddayoff.dto.response.LeaveRequestPostResponse;
import com.masterspring.backenddayoff.service.LeaveRequestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/leave_request")
@Tag(name = "Leave Request")
public class LeaveRequestController {
    private final LeaveRequestService leaveRequestService;

    @Autowired
    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @PostMapping
    public ResponseEntity<LeaveRequestPostResponse> postLeaveRequest(@Valid @RequestBody LeaveRequestPost leaveRequestPost) {
        return new ResponseEntity<>(leaveRequestService.postLeaveRequest(leaveRequestPost), HttpStatus.CREATED);
    }

    @PutMapping("/update_status/{id}")
    public ResponseEntity<LeaveRequestStatusDto> updateStatus(
            @PathVariable("id") long id,
            @RequestBody LeaveRequestStatusDto leaveRequestDto) {
        return ResponseEntity.ok(leaveRequestService.confirmLeaveRequest(id, leaveRequestDto));
    }

    @GetMapping("/pagination")
    public ResponseEntity<LeaveRequestPaginationResponse> getLeaveRequestsPage(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize) {
        return ResponseEntity.ok(leaveRequestService.getPageLeaveRequests(pageNo, pageSize));
    }

    @GetMapping("/pagination_with_userid/{id}")
    public ResponseEntity<LeaveRequestPaginationResponse> getLeaveRequestsPageWithUserId(
            @PathVariable("id") long userId,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize) {
        return ResponseEntity.ok(leaveRequestService.getPageLeaveRequestsWithUserId(userId, pageNo, pageSize));
    }

    @GetMapping("/pagination_by_search/{keyword}")
    public ResponseEntity<LeaveRequestPaginationResponse> getLeaveRequestsPageBySearch(
            @PathVariable("keyword") String keyword,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize) {
        return ResponseEntity.ok(leaveRequestService.getPageLeaveRequestsBySearch(keyword, pageNo, pageSize));
    }
}
