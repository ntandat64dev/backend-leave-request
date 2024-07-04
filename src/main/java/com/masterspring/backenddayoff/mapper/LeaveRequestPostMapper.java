package com.masterspring.backenddayoff.mapper;

import com.masterspring.backenddayoff.dto.request.LeaveRequestPost;
import com.masterspring.backenddayoff.dto.response.LeaveRequestPostResponse;
import com.masterspring.backenddayoff.entity.LeaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LeaveRequestPostMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "status", ignore = true)
    LeaveRequest toRequest(LeaveRequestPost leaveRequestPost);

    @Mapping(target = "createdBy", expression = "java(leaveRequest.getUser().getId())")
    LeaveRequestPostResponse toResponse(LeaveRequest leaveRequest);
}
