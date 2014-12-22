/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.data.jpa;

import javax.persistence.EntityManagerFactory;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sample.data.jpa.domain.Foo;
import sample.data.jpa.service.FooService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleEnversHibernateApplication.class)
public class SampleDataJpaApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private EntityManagerFactory emf;

	@Test
	public void testEnvers() {
		FooService fooService = context.getBean(FooService.class);

		Foo foo = new Foo();
		foo.setDescription("Foo");

		Foo saved = fooService.save(foo);
		saved.setDescription("Foo1");

		saved = fooService.save(saved);

		AuditReader auditReader = AuditReaderFactory.get(emf
				.createEntityManager());

		Foo oldFoo = (Foo) auditReader.createQuery()
				.forEntitiesAtRevision(Foo.class, new Integer(1))
				.getSingleResult();

		Foo newFoo = fooService.load(1);

		Assert.assertEquals("Foo", oldFoo.getDescription());
		Assert.assertEquals("Foo1", newFoo.getDescription());

	}

	//
	// private MockMvc mvc;
	//
	//
	// @Test
	// public void testHome() throws Exception {
	//
	// this.mvc.perform(get("/")).andExpect(status().isOk())
	// .andExpect(content().string("Bath"));
	// }
	//
	// @Test
	// public void testJmx() throws Exception {
	// assertEquals(
	// 1,
	// ManagementFactory
	// .getPlatformMBeanServer()
	// .queryMBeans(new ObjectName("jpa.sample:type=ConnectionPool,*"),
	// null).size());
	// }

}
