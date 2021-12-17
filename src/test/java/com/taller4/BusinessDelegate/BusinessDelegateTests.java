package com.taller4.BusinessDelegate;

import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit.jupiter.*;
import org.springframework.test.web.client.*;

import com.taller4.*;
import com.taller4.backend.model.prod.*;
import com.taller4.backend.model.sales.*;
import com.taller4.frontend.businessdelegate.*;

@SpringBootTest
@ContextConfiguration(classes = Taller4MsApplication.class)
@ExtendWith(SpringExtension.class)
public class BusinessDelegateTests {

	private BusinessDelegate bDelegate;

	@Autowired
	public BusinessDelegateTests(BusinessDelegate bDelegate) {
		this.bDelegate = bDelegate;
	}
}
