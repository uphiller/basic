package com.battercodelab.basic.entity.repository.custom;


import com.battercodelab.basic.entity.MainCode;

import java.util.List;


public interface CustomMainCodeRepository {
    List<MainCode> findMainCodeHistory(String code);
}
