

package com.sgti.tfsdemo;

import com.microsoft.tfs.core.TFSTeamProjectCollection;
import com.microsoft.tfs.core.clients.workitem.WorkItem;
import com.microsoft.tfs.core.clients.workitem.WorkItemClient;
import com.microsoft.tfs.core.clients.workitem.files.Attachment;
import com.microsoft.tfs.core.clients.workitem.internal.files.AttachmentImpl;
import com.microsoft.tfs.core.clients.workitem.project.Project;
import com.microsoft.tfs.core.clients.workitem.wittype.WorkItemType;
import com.sgti.tfsdemo.SnippetSettings;

import java.io.File;
import java.net.URI;

public class AddAttachWI
{
    public static void main(final String[] args)
    {
        TFSTeamProjectCollection tpc = SnippetSettings.connectToTFS();

        Project project = tpc.getWorkItemClient().getProjects().get(SnippetSettings.PROJECT_NAME);
        WorkItemClient workItemClient = project.getWorkItemClient();

        // Open a new instance of the work item we just saved and change it.
        WorkItem workItem = workItemClient.getWorkItemByID(2);
        //add attachment
        Attachment attachment = new AttachmentImpl(new File("C:\\abc\\Agile change management.png"), "added by varun");
        workItem.getAttachments().add(attachment);
        workItem.save();
        System.out.println("Edited work item " + workItem.getID() + " and title '" + workItem.getTitle() + "'");
    }
}
