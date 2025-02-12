package org.pyenoma.workflow.validators.services;

import lombok.RequiredArgsConstructor;
import org.pyenoma.workflow.exceptions.WorkflowException;
import org.pyenoma.workflow.validators.workflowdefinitionvalidators.IWorkflowDefinitionValidator;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class WorkflowDefinitionValidationService {
    private final List<IWorkflowDefinitionValidator> validators;

    public void validate(String workflowId, Class<?> workflowDefinitionType) throws WorkflowException {
        for (IWorkflowDefinitionValidator validator : validators) {
            validator.validate(workflowId, workflowDefinitionType);
        }
    }
}
