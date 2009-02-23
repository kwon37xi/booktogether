package com.google.code.booktogether.web.domain.base;

import java.io.Serializable;

/**
 * 해당 도메인 클래스의 정보를 출력하기 기초적인 도메인 클래스를 관리하는 클래스
 */
public abstract class BaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	abstract public String toString();

	abstract public boolean equals(Object o);

	abstract public int hashCode();
}
