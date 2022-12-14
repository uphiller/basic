package com.battercodelab.basic.controller.mapper;

import com.battercodelab.basic.controller.dto.GetMainCodeDto;
import com.battercodelab.basic.controller.dto.GetMainCodeHistoryDto;
import com.battercodelab.basic.controller.dto.GetMainCodesDto;
import com.battercodelab.basic.entity.MainCode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CodeMapper {

    @Mappings({
            @Mapping(source = "mcodes[].code", target = "code"),
            @Mapping(source = "mcodes[].name", target = "name"),
            @Mapping(source = "mcodes[].expl", target = "expl"),
    })
    List<GetMainCodesDto.Response> toGetMainCodesResponseDto(List<MainCode> mcodes);

    @Mappings({
            @Mapping(source = "mcode.code", target = "code"),
            @Mapping(source = "mcode.name", target = "name"),
            @Mapping(source = "mcode.expl", target = "expl"),
    })
    GetMainCodeDto.Response toGetMainCodeResponseDto(MainCode mcode);

    @Mappings({
            @Mapping(source = "resultList[].name", target = "name"),
            @Mapping(source = "resultList[].expl", target = "expl"),
            @Mapping(source = "resultList[].modifiedAt", target = "modified_at"),
            @Mapping(source = "resultList[].revType", target = "revType"),
            @Mapping(source = "resultList[].modified", target = "modified"),
    })
    List<GetMainCodeHistoryDto.Response> toMainCodeHistoryDto(List<MainCode> resultList);
}
