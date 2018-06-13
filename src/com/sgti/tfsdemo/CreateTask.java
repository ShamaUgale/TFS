package com.sgti.tfsdemo;

import com.microsoft.tfs.core.TFSTeamProjectCollection;
import com.microsoft.tfs.core.clients.workitem.CoreFieldReferenceNames;
import com.microsoft.tfs.core.clients.workitem.WorkItem;
import com.microsoft.tfs.core.clients.workitem.files.Attachment;
import com.microsoft.tfs.core.clients.workitem.project.Project;
import com.microsoft.tfs.core.clients.workitem.wittype.WorkItemType;

/**
 * Created by Navin.Theruvingal on 21-11-2015.
 */
public class CreateTask {
    public static void main(String[] args) {
        TFSTeamProjectCollection tpc = SnippetSettings.connectToTFS();

        Project project = tpc.getWorkItemClient().getProjects().get(SnippetSettings.PROJECT_NAME);

        WorkItemType workItemType = project.getWorkItemTypes().get(1);

        // Create a new work item of the specified type.
        WorkItem newWorkItem = project.getWorkItemClient().newWorkItem(workItemType);

        newWorkItem.setTitle("Develop a plugin to perform LDAP Query to lookup users");


        newWorkItem.getFields().getField(CoreFieldReferenceNames.CREATED_BY).setValue("Navin");
        newWorkItem.getFields().getField(CoreFieldReferenceNames.CREATED_DATE).setValue("11/21/2015");
        newWorkItem.getFields().getField(CoreFieldReferenceNames.DESCRIPTION).setValue("Acceptance Criteria: The plugin should accept " +
                "user credentials and return a String as \"Success\" or \"Failure: <Error Message>\". The error message should be " +
                "descriptive enough to explain what went wrong.");

        //add comment


        // Save the new work item to the server.
        newWorkItem.save();

        System.out.println("Work item " + newWorkItem.getID() + " successfully created");

    }
}
