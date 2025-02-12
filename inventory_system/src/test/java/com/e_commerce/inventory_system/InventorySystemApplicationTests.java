package com.e_commerce.inventory_system;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class InventorySystemApplicationTests {

	@Test
	void contextLoads() {
	}

}
