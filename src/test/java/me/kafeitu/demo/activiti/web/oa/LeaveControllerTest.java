package me.kafeitu.demo.activiti.web.oa;


import me.kafeitu.demo.activiti.service.activiti.WorkflowTraceService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * 请假流程控制器测试
 *
 * @author HenryYan
 */
@ContextConfiguration(locations = {"/applicationContext-test.xml"})
public class LeaveControllerTest {
    @Autowired
    protected WorkflowTraceService traceService;

    @Test
    public void testWorkFlowTrace() throws Exception {
       // List<Map<String, Object>> activityInfos = traceService.traceProcess("2");
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        Execution execution = runtimeService.createExecutionQuery().executionId("22").singleResult();
        Object property = PropertyUtils.getProperty(execution, "activityId");
    }
}
