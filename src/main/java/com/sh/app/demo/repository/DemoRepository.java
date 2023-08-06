package com.sh.app.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sh.app.demo.dto.DevCreateDto;
import com.sh.app.demo.dto.DevUpdateDto;
import com.sh.app.demo.entity.Dev;
/**
 * mybatis에 @Mapper 인터페이스의 구현체를 런타임에 생성해 빈으로 등록
 * 
 */
@Mapper
public interface DemoRepository {

	int insertDev(DevCreateDto devDto);

	List<Dev> findAllDev();

	Dev findDevById(int id);

	int updateDev(DevUpdateDto dev);

	int deleteDev(int id);

}
