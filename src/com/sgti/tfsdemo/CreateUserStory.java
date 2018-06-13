package com.sgti.tfsdemo;

import com.microsoft.tfs.core.TFSTeamProjectCollection;
import com.microsoft.tfs.core.clients.workitem.CoreFieldReferenceNames;
import com.microsoft.tfs.core.clients.workitem.WorkItem;
import com.microsoft.tfs.core.clients.workitem.fields.FieldDefinition;
import com.microsoft.tfs.core.clients.workitem.fields.FieldDefinitionCollection;
import com.microsoft.tfs.core.clients.workitem.files.Attachment;
import com.microsoft.tfs.core.clients.workitem.internal.files.AttachmentImpl;
import com.microsoft.tfs.core.clients.workitem.project.Project;
import com.microsoft.tfs.core.clients.workitem.wittype.WorkItemType;
import com.microsoft.tfs.core.clients.workitem.wittype.WorkItemTypeCollection;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Navin.Theruvingal on 21-11-2015.
 */
public class CreateUserStory {
    public static void main(String[] args) {
        TFSTeamProjectCollection tpc = SnippetSettings.connectToTFS();

        Project project = tpc.getWorkItemClient().getProjects().get(SnippetSettings.PROJECT_NAME);

        WorkItemType workItemType = project.getWorkItemTypes().get(10);

        // Create a new work item of the specified type.
        WorkItem newWorkItem = project.getWorkItemClient().newWorkItem(workItemType);

        newWorkItem.setTitle("As a Nemesis user, I want to have SSO login based on the corporate LDAP");


        newWorkItem.getFields().getField(CoreFieldReferenceNames.CREATED_BY).setValue("Navin");
        newWorkItem.getFields().getField(CoreFieldReferenceNames.CREATED_DATE).setValue("11/21/2015");
        newWorkItem.getFields().getField(CoreFieldReferenceNames.DESCRIPTION).setValue("It would be great to use the credentials of my corporate login to access all the portals using single sign on. This will prevent us from hassle of maintaining multiple user accounts.");

        //add attachment
        Attachment attachment = new AttachmentImpl(new File(URI.create("https://drive.google.com/uc?export=download&id=0B5Z57bfGVTBxN0c5V1Y4aTY3SkE")), "");
        newWorkItem.getAttachments().add(attachment);

        //add comment
//        List<String> commentList = new ArrayList<String>();
//        commentList.add("comment 1");
//        commentList.add("comment 2");
        //newWorkItem.getFields().getField(CoreFieldReferenceNames.CHANGED_BY).setValue();

        //newWorkItem.

                newWorkItem.save();

        System.out.println("Work item " + newWorkItem.getID() + " successfully created");

    }

    private static void displayData(Project project) {
        WorkItemTypeCollection wiCollection = project.getWorkItemTypes();


        for (WorkItemType wiType : wiCollection) {
            System.out.println(wiType.getID() + "\t" + wiType.getName());
            print(wiType.getFieldDefinitions());
        }
    }

    private static String print(FieldDefinitionCollection fieldDefinitions) {
        for (Iterator<FieldDefinition> iterator = fieldDefinitions.iterator(); iterator.hasNext(); ) {
            FieldDefinition fieldDefinition = iterator.next();
            System.out.println("\t"+fieldDefinition.getID() + "\t" + fieldDefinition.getName());
        }

        return "";
    }
}
