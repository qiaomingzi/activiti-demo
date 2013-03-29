package me.kafeitu.demo.activiti.engine;

import me.kafeitu.modules.test.spring.SpringTransactionalTestCase;
import org.activiti.engine.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertNotNull;

/**
 * 测试流程引擎
 *
 * @author HenryYan
 */
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class ProcessEngineTest extends SpringTransactionalTestCase {

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private FormService formService;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private HistoryService historyService;

	@Autowired
	private ManagementService managementService;

	/**
	 * 检测引擎是否能正常工作
	 */
	@Test
	public void testProcessEngines() {
		assertNotNull(repositoryService);
		assertNotNull(runtimeService);
		assertNotNull(formService);
		assertNotNull(identityService);
		assertNotNull(taskService);
		assertNotNull(historyService);
		assertNotNull(managementService);
	}

}
