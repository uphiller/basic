package com.battercodelab.basic.controller;

import com.battercodelab.basic.controller.dto.PutMainCodeDto;
import com.battercodelab.basic.controller.dto.SetMainMenuDto;
import com.battercodelab.basic.controller.dto.SetSubMenuDto;
import com.battercodelab.basic.controller.mapper.CodeMapper;
import com.battercodelab.basic.service.CodeService;
import com.battercodelab.basic.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class CodeController {

    private final CodeService codeService;
    private final CodeMapper codeMapper;

    @PostMapping(value = "/main/codes")
    public ResponseEntity<?> setMainCode(@RequestBody SetMainMenuDto.Request request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        codeService.setMainCode(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/main/codes")
    public ResponseEntity<?> getMainCodes() {
        return new ResponseEntity<>(codeMapper.toGetMainCodesResponseDto(codeService.getMainCodes()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/main/codes/{code}")
    public ResponseEntity<?> getMainCode(@PathVariable("code") String code) {
        return new ResponseEntity<>(codeMapper.toGetMainCodeResponseDto(codeService.getMainCode(code)), HttpStatus.OK);
    }

    @GetMapping(value = "/main/codes/{code}/history")
    public ResponseEntity<?> getMainCodeHistory(@PathVariable("code") String code) {
        return new ResponseEntity<>(codeMapper.toMainCodeHistoryDto(codeService.getMainCodeHistory(code)), HttpStatus.OK);
    }

    @PutMapping(value = "/main/codes")
    public ResponseEntity<?> putMainCode(@RequestBody PutMainCodeDto.Request request) {
        codeService.putMainCode(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/sub/codes")
    public ResponseEntity<?> setSubCode(@RequestBody SetSubMenuDto.Request request) {
        codeService.setSubCode(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
