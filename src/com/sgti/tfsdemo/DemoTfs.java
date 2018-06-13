package com.sgti.tfsdemo;

import com.microsoft.tfs.core.clients.workitem.CoreFieldReferenceNames;
import com.microsoft.tfs.core.clients.workitem.WorkItem;
import com.microsoft.tfs.core.clients.workitem.files.Attachment;
import com.microsoft.tfs.core.clients.workitem.internal.files.AttachmentImpl;
import com.microsoft.tfs.core.clients.workitem.link.LinkFactory;
import com.microsoft.tfs.core.clients.workitem.link.RelatedLink;
import com.microsoft.tfs.core.clients.workitem.project.Project;
import com.microsoft.tfs.core.clients.workitem.wittype.WorkItemType;

import java.io.File;

/**
 * Created by Varun.Bhatia on 26-11-2015.
 */
public class DemoTfs {
    public int createTask(Project project) {
        WorkItemType workItemType = project.getWorkItemTypes().get("Task");

        // Create a new work item of the specified type.
        WorkItem newWorkItem = project.getWorkItemClient().newWorkItem(workItemType);

        newWorkItem.setTitle("Develop a plugin to perform LDAP Query to lookup users");


        newWorkItem.getFields().getField(CoreFieldReferenceNames.CREATED_BY).setValue("Navin");
        newWorkItem.getFields().getField(CoreFieldReferenceNames.CREATED_DATE).setValue("11/21/2015");
        newWorkItem.getFields().getField(CoreFieldReferenceNames.DESCRIPTION).setValue("Acceptance Criteria: The plugin should accept " +
                "user credentials and return a String as \"Success\" or \"Failure: <Error Message>\". The error message should be " +
                "descriptive enough to explain what went wrong.");
        // Save the new work item to the server.
        newWorkItem.save();

        System.out.println("Task  " + newWorkItem.getID() + " successfully created");

        return newWorkItem.getID();
    }

    public int createUserStory(Project project) {
        for(WorkItemType workItemType : project.getWorkItemTypes()){
            System.out.println("workItemType " + workItemType.getID() + "  :  " +workItemType.getName());
        }
        WorkItemType workItemType = project.getWorkItemTypes().get("Product Backlog Item");
        System.out.println("workItemType " + workItemType);
        // Create a new work item of the specified type.
        WorkItem newWorkItem = project.getWorkItemClient().newWorkItem(workItemType);

        newWorkItem.setTitle("As a Nemesis user, I want to have SSO login based on the corporate LDAP");


        newWorkItem.getFields().getField(CoreFieldReferenceNames.CREATED_BY).setValue("MVTL_Varun");
        newWorkItem.getFields().getField(CoreFieldReferenceNames.CREATED_DATE).setValue("11/21/2015");
        newWorkItem.getFields().getField(CoreFieldReferenceNames.DESCRIPTION).setValue("It would be great to use the credentials of my corporate login to access all the portals using single sign on. This will prevent us from hassle of maintaining multiple user accounts.");
        newWorkItem.save();

        System.out.println("User Story " + newWorkItem.getID() + " successfully created");
        return newWorkItem.getID();
    }

    public void attachTaskToStory(Project project, int storyItemId, int taskItemId){
        WorkItem storyItem = project.getWorkItemClient().getWorkItemByID(storyItemId);
        WorkItem taskItem = project.getWorkItemClient().getWorkItemByID(taskItemId);
        String linkComment = "Assign Task to Story";
        RelatedLink newRelatedLink =
                LinkFactory.newRelatedLink(storyItem, taskItem, 2, linkComment, false);

        // Add the link to the first new work item.
        storyItem.getLinks().add(newRelatedLink);
        storyItem.save();
        System.out.println("Added a link to work item.");

    }

    public void addAttachmentToWI(Project project, int workId, String attachmentPath) {
        WorkItem workItem = project.getWorkItemClient().getWorkItemByID(workId);

        //add attachment
        Attachment attachment = new AttachmentImpl(new File(attachmentPath), "added by varun");
        workItem.getAttachments().add(attachment);
        workItem.save();
        System.out.println("Attached attachment to work item " + workItem.getID() + " and title '" + workItem.getTitle() + "'");
    }

    public void addCommentToWI(Project project, int workId, String comment) {
        WorkItem workItem = project.getWorkItemClient().getWorkItemByID(workId);
        workItem.getFields().getField(CoreFieldReferenceNames.HISTORY).setValue(comment);
        workItem.getFields().getField(CoreFieldReferenceNames.CHANGED_BY).setValue("MVTL_Varun");
        workItem.getFields().getField(CoreFieldReferenceNames.CHANGED_DATE).setValue("11/26/2015");
        workItem.save();
        System.out.println("Added comments to WI");

    }
}
