/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.data.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import sample.data.jpa.domain.Foo;

@Component("fooService")
@Transactional
class FooServiceImpl implements FooService {

	private final FooRepository fooRepository;

	@Autowired
	public FooServiceImpl(FooRepository fooRepository) {
		this.fooRepository = fooRepository;
	}

	@Override
	public Foo load(Integer foo) {
		return fooRepository.findOne(foo);
	}

	@Override
	public Foo save(Foo foo) {
		return fooRepository.save(foo);
	}

}
