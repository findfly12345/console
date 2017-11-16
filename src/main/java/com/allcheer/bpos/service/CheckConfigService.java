package com.allcheer.bpos.service;

import com.allcheer.bpos.form.CheckConfigForm;
import com.allcheer.bpos.util.BposException;

import java.util.List;

public interface CheckConfigService {

	int count(CheckConfigForm form);

	List<CheckConfigForm> select(CheckConfigForm form);

	int delete(CheckConfigForm form);

	int insert(CheckConfigForm form) throws BposException;

	int update(CheckConfigForm form)  throws BposException ;
}
