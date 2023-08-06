package com.sh.app.demo.service;

import java.util.List;

import com.sh.app.demo.dto.DevCreateDto;
import com.sh.app.demo.dto.DevUpdateDto;
import com.sh.app.demo.entity.Dev;

public interface DemoService {

	int insertDev(DevCreateDto devDto);

	List<Dev> findAllDev();

	Dev findDevById(int id);

	int updateDev(DevUpdateDto dev);

	int deleteDev(int id);

}
