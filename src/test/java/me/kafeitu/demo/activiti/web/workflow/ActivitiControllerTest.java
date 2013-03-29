package me.kafeitu.demo.activiti.web.workflow;

import me.kafeitu.demo.activiti.service.activiti.WorkflowProcessDefinitionService;
import me.kafeitu.demo.activiti.service.activiti.WorkflowTraceService;
import me.kafeitu.modules.test.spring.SpringTransactionalTestCase;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 工作流控制器测试
 *
 * @author HenryYan
 */
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class ActivitiControllerTest extends SpringTransactionalTestCase {

	@Autowired
	protected WorkflowProcessDefinitionService workflowProcessDefinitionService;

	@Autowired
	protected RepositoryService repositoryService;

	private ActivitiController activitiController;

	@Before
	public void setUp() throws Exception {
		activitiController = new ActivitiController();
		activitiController.setRepositoryService(repositoryService);
		activitiController.setWorkflowProcessDefinitionService(workflowProcessDefinitionService);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testProcessList() {
		ModelAndView mav = activitiController.processList();
		assertNotNull(mav);
		List<Object[]> objects = (List<Object[]>) mav.getModelMap().get("objects");
		assertNotNull(objects);
	}

	@Test
	public void testRedeployAll() throws Exception {
		String view = activitiController.redeployAll();
		assertEquals("redirect:/workflow/process-list", view);
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
		assertEquals(4, list.size());
	}

        @Test
	public void testLoadByDeployment() throws Exception {
		// 部署流程定义
		List<ProcessDefinition> list = deployAllProcess();

		MockHttpServletResponse response = new MockHttpServletResponse();
		System.out.println("------------>"+list);
		activitiController.loadByDeployment(list.get(1).getDeploymentId(), "leave.bpmn20.xml", response);
	}

	@Test
	public void testDeleteProcess() throws Exception {

		List<ProcessDefinition> list = deployAllProcess();
		for (ProcessDefinition processDefinition : list) {
			activitiController.delete(processDefinition.getDeploymentId());
		}
		// 删除流程定义
		list = repositoryService.createProcessDefinitionQuery().list();
		assertEquals(0, list.size());
	}

	private List<ProcessDefinition> deployAllProcess() throws Exception {
		activitiController.redeployAll();
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
		assertEquals(4, list.size());//assertEquals(2, list.size());
		return list;
	}
}
