package workflow.exceptions;

public class DuplicateWorkflowIdException extends WorkflowException {
    public DuplicateWorkflowIdException(String workflowId) {
        super(buildMessage(workflowId));
    }

    private static String buildMessage(String workflowId) {
        return "Duplicate workflow ID: " + workflowId;
    }
}
