package workflow.validators;

import org.junit.jupiter.api.Test;
import workflow.annotations.WorkflowDefinition;
import workflow.dummies.DummyWorkflowDefinitionBeanWithNoId;
import workflow.dummies.DummyWorkflowDefinitionWithDuplicateTasks;
import workflow.dummies.DummyWorkflowTask;
import workflow.exceptions.DuplicateTaskClassFoundException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DuplicateTaskClassesValidatorTest {

    @Test
    void validateSuccess() {
        DuplicateTaskClassesValidator validator = new DuplicateTaskClassesValidator();
        assertDoesNotThrow(() -> validator.validate(
                DummyWorkflowDefinitionBeanWithNoId.class.getAnnotation(WorkflowDefinition.class).id(),
                DummyWorkflowDefinitionBeanWithNoId.class));
    }

    @Test
    void validateFailure() {
        DuplicateTaskClassesValidator validator = new DuplicateTaskClassesValidator();
        String workflowId = DummyWorkflowDefinitionWithDuplicateTasks.class.getAnnotation(WorkflowDefinition.class)
                .id();
        DuplicateTaskClassFoundException duplicateTaskClassFoundException = assertThrows(
                DuplicateTaskClassFoundException.class,
                () -> validator.validate(workflowId, DummyWorkflowDefinitionWithDuplicateTasks.class));
        assertEquals(
                "Duplicate task class definition found | " + "Task class: " + DummyWorkflowTask.class.getName() + " "
                        + "Workflow class: " + DummyWorkflowDefinitionWithDuplicateTasks.class.getName(),
                duplicateTaskClassFoundException.getMessage());
    }
}