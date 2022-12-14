package com.battercodelab.basic.service;

import com.battercodelab.basic.controller.dto.PutMainCodeDto;
import com.battercodelab.basic.controller.dto.SetMainMenuDto;
import com.battercodelab.basic.controller.dto.SetSubMenuDto;
import com.battercodelab.basic.entity.MainCode;
import com.battercodelab.basic.entity.repository.MainCodeRepository;
import com.battercodelab.basic.exception.BusinessException;
import com.battercodelab.basic.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CodeService {

    private final MainCodeRepository mainCodeRepository;

    @CacheEvict(value="mCode", allEntries=true)
    @Transactional
    public void setMainCode(SetMainMenuDto.Request request) {
        int codeCnt = getMainCodes().size();
        String code = String.format("M%s", codeCnt + 1);
        mainCodeRepository.save(
                MainCode.builder()
                        .code(code)
                        .name(request.getName())
                        .expl(request.getExpl())
                        .build()
        );
    }

    public void setSubCode(SetSubMenuDto.Request request) {
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "mCode")
    public MainCode getMainCode(String code) {
        MainCode mainCode = mainCodeRepository.findByCode(code).orElseThrow(() -> new BusinessException(ErrorCode.data_is_empty));
        return mainCode;
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "mCode")
    public List<MainCode> getMainCodes() {
        return mainCodeRepository.findAll();
    }

    @Transactional
    public void putMainCode(PutMainCodeDto.Request request) {
        MainCode mainCode = mainCodeRepository.findByCode(request.getCode()).orElseThrow(() -> new BusinessException(ErrorCode.data_is_empty));
        mainCode.update(request.getName(), request.getExpl());
        mainCodeRepository.save(mainCode);
    }

    public List getMainCodeHistory(String code) {
        return mainCodeRepository.findMainCodeHistory(code);
    }
}
