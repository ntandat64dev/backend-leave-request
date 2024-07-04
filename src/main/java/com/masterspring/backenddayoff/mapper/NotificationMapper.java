package com.masterspring.backenddayoff.mapper;

import com.masterspring.backenddayoff.dto.NotificationDto;
import com.masterspring.backenddayoff.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(target = "userId", source = "user.id")
    NotificationDto toDto(Notification notification);
}
